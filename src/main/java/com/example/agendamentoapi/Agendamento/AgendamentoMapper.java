package com.example.agendamentoapi.Agendamento;

import org.springframework.stereotype.Component;

@Component
public class AgendamentoMapper {

    public AgendamentoModel map(AgendamentoDTO agendamentoDTO) {

        AgendamentoModel agendamentoModel = new AgendamentoModel();
        agendamentoModel.setId(agendamentoDTO.getId());
        agendamentoModel.setCliente(agendamentoDTO.getCliente());
        agendamentoModel.setServico(agendamentoDTO.getServico());
        agendamentoModel.setDataHora(agendamentoDTO.getDataHora());
        agendamentoModel.setStatus(agendamentoDTO.getStatus());

        return  agendamentoModel;
    }

    public AgendamentoDTO map(AgendamentoModel agendamentoModel) {

        AgendamentoDTO agendamentoDTO = new AgendamentoDTO();
        agendamentoDTO.setId(agendamentoModel.getId());
        agendamentoDTO.setCliente(agendamentoModel.getCliente());
        agendamentoDTO.setServico(agendamentoModel.getServico());
        agendamentoDTO.setDataHora(agendamentoModel.getDataHora());
        agendamentoDTO.setStatus(agendamentoModel.getStatus());

        return agendamentoDTO;
    }
}
