package com.example.agendamentoapi.Servico;

import com.example.agendamentoapi.Agendamento.AgendamentoModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "servico")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "agendamentos") //evita o ciclo infinito por ter um relacionamento bidirecional
public class ServicoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    @OneToMany(mappedBy = "servico")
    @JsonIgnore
    private List<AgendamentoModel> agendamentos;

}
