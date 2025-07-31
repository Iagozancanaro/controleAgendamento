package com.example.agendamentoapi.Cliente;

import com.example.agendamentoapi.Agendamento.AgendamentoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private List<AgendamentoModel> agendamentos;
}
