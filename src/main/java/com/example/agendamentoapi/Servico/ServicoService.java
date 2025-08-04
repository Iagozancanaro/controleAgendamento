package com.example.agendamentoapi.Servico;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final ServicoMapper servicoMapper;

    public ServicoService(ServicoRepository servicoRepository, ServicoMapper servicoMapper) {
        this.servicoRepository = servicoRepository;
        this.servicoMapper = servicoMapper;
    }

    public List<ServicoDTO> listarServicos() {
        List<ServicoModel> servicos = servicoRepository.findAll();
        return servicos.stream()
                .map(servicoMapper::map)
                .collect(Collectors.toList());
    }

    public ServicoDTO buscarServicoPorId(Long id) {
        Optional<ServicoModel> servicoPorId = servicoRepository.findById(id);
        return servicoPorId.map(servicoMapper::map).orElse(null);
    }

    public ServicoDTO criarServico(ServicoDTO servicoDTO) {
        ServicoModel servico = servicoMapper.map(servicoDTO);
        servico = servicoRepository.save(servico);
        return servicoMapper.map(servico);
    }

    public ServicoDTO atualizarServico(Long id, ServicoDTO servicoDTO) {
        Optional<ServicoModel> servicoExistente = servicoRepository.findById(id);
        if (servicoExistente.isPresent()) {
            ServicoModel servicoAtualizado = servicoMapper.map(servicoDTO);
            servicoAtualizado.setId(id);
            ServicoModel servicoSalvo = servicoRepository.save(servicoAtualizado);
            return servicoMapper.map(servicoSalvo);
        }
        return null;
    }

    public boolean deletarServico(Long id) {
        Optional<ServicoModel> servicoExiste = servicoRepository.findById(id);
        if (servicoExiste.isEmpty()) {
            System.out.println("Serviço não encontrado");
            return false;
        }

        ServicoModel servico = servicoExiste.get();

        if (servico.getAgendamentos() != null && !servico.getAgendamentos().isEmpty()) {
            System.out.println("O serviço ja tem um agendamento previsto");
            return false;
        }
        servicoRepository.delete(servico);
        System.out.println("Serviço deletado com sucesso");
        return true;
    }

}
