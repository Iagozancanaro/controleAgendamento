package com.example.agendamentoapi.Cliente;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteDTO> listarClientes() {
        List<ClienteModel> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(clienteMapper::map)
                .collect(Collectors.toList());
    }

    public ClienteDTO buscarClientePorId(Long id) {
        Optional<ClienteModel> clientePorId = clienteRepository.findById(id);
        return clientePorId.map(clienteMapper::map).orElse(null);
    }

    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {

        // essa verificação puxa o metodo criado no clienteRepository para validar aqui
        if (clienteRepository.existsByEmail(clienteDTO.getEmail())) {
            System.out.println("Email ja cadastrado");
            return null;
        }

        ClienteModel cliente = clienteMapper.map(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return clienteMapper.map(cliente);
    }

    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
        Optional<ClienteModel> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()) {
            ClienteModel clienteAtualizado = clienteMapper.map(clienteDTO);
            clienteAtualizado.setId(id);
            ClienteModel clienteSalvo = clienteRepository.save(clienteAtualizado);
            return clienteMapper.map(clienteSalvo);
        }
        return null;
    }

    public boolean deletarCliente(Long id) {
        Optional<ClienteModel> clienteExiste = clienteRepository.findById(id);
        if (clienteExiste.isEmpty()) {
            System.out.println("Cliente não encontrado");
            return false;
        }

        ClienteModel cliente = clienteExiste.get();

        // verifica se o cliente tem algum agendamento e não o exclui caso tenha
        if (cliente.getAgendamentos() != null && !cliente.getAgendamentos().isEmpty()) {
            System.out.println("O cliente possui um agendamento previsto");
            return false;
        }
        clienteRepository.delete(cliente);
        System.out.println("Cliente deletado com sucesso");
        return true;

    }
}
