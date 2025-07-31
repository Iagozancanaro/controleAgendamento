package com.example.agendamentoapi.Cliente;

import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteModel map(ClienteDTO clienteDTO) {

        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(clienteDTO.getId());
        clienteModel.setNome(clienteDTO.getNome());
        clienteModel.setEmail(clienteDTO.getEmail());
        clienteModel.setTelefone(clienteDTO.getTelefone());
        clienteModel.setAgendamentos(clienteDTO.getAgendamentos());

        return clienteModel;

    }

    public ClienteDTO map(ClienteModel clienteModel) {

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId((clienteModel.getId()));
        clienteDTO.setNome(clienteModel.getNome());
        clienteDTO.setEmail(clienteModel.getEmail());
        clienteDTO.setTelefone(clienteModel.getTelefone());
        clienteDTO.setAgendamentos(clienteModel.getAgendamentos());

        return clienteDTO;
    }
}


