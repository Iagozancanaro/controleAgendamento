package com.example.agendamentoapi.Agendamento;


import com.example.agendamentoapi.Cliente.ClienteModel;
import com.example.agendamentoapi.Servico.ServicoModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoDTO {

    private Long id;
    private ClienteModel cliente;
    private ServicoModel servico;
    private LocalDateTime dataHora;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StatusAgendamento status;

}
