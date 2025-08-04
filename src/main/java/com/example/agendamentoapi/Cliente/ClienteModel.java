package com.example.agendamentoapi.Cliente;

import com.example.agendamentoapi.Agendamento.AgendamentoModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
@Table (name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString (exclude = "agendamentos") //evita o ciclo infinito por ter um relacionamento bidirecional
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Formato de telefone inválido")
    private String telefone;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<AgendamentoModel> agendamentos;

}
