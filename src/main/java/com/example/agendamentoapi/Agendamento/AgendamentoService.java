package com.example.agendamentoapi.Agendamento;

import com.example.agendamentoapi.Cliente.ClienteModel;
import com.example.agendamentoapi.Cliente.ClienteRepository;
import com.example.agendamentoapi.Servico.ServicoModel;
import com.example.agendamentoapi.Servico.ServicoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final ServicoRepository servicoRepository;
    private final AgendamentoMapper agendamentoMapper;


    public AgendamentoService(AgendamentoRepository agendamentoRepository, ClienteRepository clienteRepository, ServicoRepository servicoRepository, AgendamentoMapper agendamentoMapper) {
        this.agendamentoRepository = agendamentoRepository;
        this.clienteRepository = clienteRepository;
        this.servicoRepository = servicoRepository;
        this.agendamentoMapper = agendamentoMapper;
    }

    public List<AgendamentoDTO> listarAgendamentos() {
        List<AgendamentoModel> agendamento = agendamentoRepository.findAll();
        return agendamento.stream()
                .map(agendamentoMapper::map)
                .collect(Collectors.toList());
    }

    public AgendamentoDTO buscarPorId(Long id) {
        Optional<AgendamentoModel> agendamentoPorId = agendamentoRepository.findById(id);
        return agendamentoPorId.map(agendamentoMapper::map).orElse(null);
    }

    public AgendamentoDTO criarAgendamento(AgendamentoDTO agendamentoDTO) {

        // valida se a data é futura
        if (agendamentoDTO.getDataHora().isBefore(LocalDateTime.now())) {
            System.out.println("Data do agendamento já passou.");
            return null;
        }

        // verifica se o cliente existe
        ClienteModel cliente = clienteRepository.findById(agendamentoDTO.getCliente().getId()).orElse(null);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return null;
        }

        // verifica se o serviço existe
        ServicoModel servico = servicoRepository.findById(agendamentoDTO.getServico().getId()).orElse(null);
        if (servico == null) {
            System.out.println("Serviço não encontrado.");
            return null;
        }

        AgendamentoModel agendamento = agendamentoMapper.map(agendamentoDTO);

        // garante que o cliente/serviço estão corretos (do banco)
        agendamento.setCliente(cliente);
        agendamento.setServico(servico);

        // se estiver vazio, define o status como pendente
        if (agendamento.getStatus() == null) {
            agendamento.setStatus(StatusAgendamento.PENDENTE);
        }

        agendamento = agendamentoRepository.save(agendamento);
        return agendamentoMapper.map(agendamento);
    }

    public AgendamentoDTO atualizarAgendamento(Long id, AgendamentoDTO agendamentoDTO) {
        Optional<AgendamentoModel> agendamentoExistente = agendamentoRepository.findById(id);
        if (!agendamentoExistente.isPresent()) {
            System.out.println("Agendamento não encontrado");
            return null;
        }

        AgendamentoModel agendamento = agendamentoExistente.get();

        if (agendamentoDTO.getDataHora().isBefore(LocalDateTime.now())) {
            System.out.println("Data inválida");
            return null;
        }

        Optional<ServicoModel> servicoExistente = servicoRepository.findById(agendamentoDTO.getServico().getId());
        if (!servicoExistente.isPresent()) {
            System.out.println("Serviço não encontrado");
            return null;
        }

        agendamento.setDataHora(agendamentoDTO.getDataHora());
        agendamento.setServico(servicoExistente.get());

        AgendamentoModel agendamentoSalvo = agendamentoRepository.save(agendamento);
        return agendamentoMapper.map(agendamentoSalvo);
    }

    public AgendamentoDTO alterarStatus(Long id, String novoStatus) {
        Optional<AgendamentoModel> agendamentoExistente = agendamentoRepository.findById(id);
        if (!agendamentoExistente.isPresent()) {
            System.out.println("Agendamento não encontrado");
            return null;
        }

        AgendamentoModel agendamento = agendamentoExistente.get();
        StatusAgendamento statusAtual = agendamento.getStatus();

        // valida o novo status
        StatusAgendamento novoStatusEnum = Arrays.stream(StatusAgendamento.values())
                .filter(status -> status.name().equalsIgnoreCase(novoStatus))
                .findFirst()
                .orElse(null);

        if (novoStatusEnum == null) {
            System.out.println("Status invalido: " + novoStatus);
            return null;
        }

        // valida se pode mudar de status
        if (!statusAtual.podeMudarPara(novoStatusEnum)) {
            System.out.println("Não se pode mudar esse status");
            return null;
        }

        agendamento.setStatus(novoStatusEnum);
        AgendamentoModel statusSalvo = agendamentoRepository.save(agendamento);
        return agendamentoMapper.map(statusSalvo);
    }

    public AgendamentoDTO cancelarAgendamento(Long id) {
        Optional<AgendamentoModel> agendamentoExistente = agendamentoRepository.findById(id);
        if (!agendamentoExistente.isPresent()) {
            System.out.println("Agendamento não encontrado");
            return null;
        }

        AgendamentoModel agendamento = agendamentoExistente.get();

        // verificar se ja esta cancelado ou concluido
        if (agendamento.getStatus() == StatusAgendamento.CANCELADO) {
            System.out.println("O agendamento ja está CANCELADO");
            return null;
        }

        if (agendamento.getStatus() == StatusAgendamento.CONCLUIDO) {
            System.out.println("Não é possivel cancelar um agendamento já CONCLUÍDO");
            return null;
        }

        agendamento.setStatus(StatusAgendamento.CANCELADO);
        AgendamentoModel agendamentoSalvo = agendamentoRepository.save(agendamento);
        return agendamentoMapper.map(agendamentoSalvo);

    }

    public void deletarAgendamento(Long id) {
        if (agendamentoRepository.existsById(id)) {
            agendamentoRepository.deleteById(id);
        }
    }
}
