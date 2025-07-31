package com.example.agendamentoapi.Servico;

import com.example.agendamentoapi.Agendamento.AgendamentoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private List<AgendamentoModel> agendamentos;

}
