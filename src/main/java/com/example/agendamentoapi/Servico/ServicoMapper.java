package com.example.agendamentoapi.Servico;

import org.springframework.stereotype.Component;

@Component
public class ServicoMapper {

    public ServicoModel map(ServicoDTO servicoDTO) {

        ServicoModel servicoModel = new ServicoModel();
        servicoModel.setId(servicoDTO.getId());
        servicoModel.setNome(servicoDTO.getNome());
        servicoModel.setDescricao(servicoDTO.getDescricao());
        servicoModel.setPreco(servicoDTO.getPreco());
        servicoModel.setAgendamentos(servicoDTO.getAgendamentos());

        return servicoModel;
    }

    public ServicoDTO map(ServicoModel servicoModel) {

        ServicoDTO servicoDTO = new ServicoDTO();
        servicoDTO.setId(servicoModel.getId());
        servicoDTO.setNome(servicoModel.getNome());
        servicoDTO.setDescricao(servicoModel.getDescricao());
        servicoDTO.setPreco(servicoModel.getPreco());
        servicoDTO.setAgendamentos(servicoModel.getAgendamentos());

        return servicoDTO;
    }



}
