package com.example.agendamentoapi.Agendamento;

import com.example.agendamentoapi.Cliente.ClienteModel;
import com.example.agendamentoapi.Servico.ServicoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table (name = "agendamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"cliente", "servico"}) //evita o ciclo infinito por ter um relacionamento bidirecional
public class AgendamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel cliente;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private ServicoModel servico;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm") // formata o campo
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING) // faz o JPA salvar no banco o nome do enum
    private StatusAgendamento status;
}
