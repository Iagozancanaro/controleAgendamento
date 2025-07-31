package com.example.agendamentoapi.Agendamento;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria um novo agendamento", description = "Rota cria um novo agendamento e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do agendamento")
    })
    public ResponseEntity<String> criarAgendamento(@RequestBody AgendamentoDTO agendamento) {
        AgendamentoDTO novoAgendamento = agendamentoService.criarAgendamento(agendamento);
    }
}
