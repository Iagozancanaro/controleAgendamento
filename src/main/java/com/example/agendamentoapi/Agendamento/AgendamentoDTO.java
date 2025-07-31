package com.example.agendamentoapi.Agendamento;


import com.example.agendamentoapi.Cliente.ClienteModel;
import com.example.agendamentoapi.Servico.ServicoModel;
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
    private StatusAgendamento status;

}
