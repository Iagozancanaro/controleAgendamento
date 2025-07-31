package com.example.agendamentoapi.Agendamento;

public enum StatusAgendamento {
    PENDENTE,
    AGENDADO,
    CONCLUIDO,
    CANCELADO;

    public boolean podeMudarPara(StatusAgendamento novoStatus) {
        switch (this) {
            case PENDENTE:
                return novoStatus == AGENDADO || novoStatus == CANCELADO;
            case AGENDADO:
                return novoStatus == CONCLUIDO || novoStatus == CANCELADO;
            default:
                return false; // CONCLUIDO e CANCELADO nao mudam
        }
    }
}
