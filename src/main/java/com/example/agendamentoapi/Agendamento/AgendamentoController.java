package com.example.agendamentoapi.Agendamento;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Agendamento criado com sucesso");
    }

    @GetMapping("/listar")
    public List<AgendamentoDTO> listarAgendamentos() {
        return agendamentoService.listarAgendamentos();
    }

    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera o agendamento", description = "Rota para alterar um agendamento pela data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado, não foi possível alterar")
    })
    public ResponseEntity<?> atualizarDataServico(@PathVariable Long id, @RequestBody AgendamentoDTO dataServicoAtualizado) {
        AgendamentoDTO agendamento = agendamentoService.atualizarAgendamento(id, dataServicoAtualizado);
        if (agendamento != null) {
            return ResponseEntity.ok(agendamento);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O agendamento que voce esta tentando atualizar não existe");
        }
    }

    @PutMapping("/alterar/{id}/status")
    @Operation(summary = "Altera o status do agendamento", description = "Rota para mudar o status de um agendamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado ou status inválido")
    })
    public ResponseEntity<?> alterarStatus (@PathVariable Long id, @RequestBody String novoStatus) {
        AgendamentoDTO atualizado = agendamentoService.alterarStatus(id, novoStatus);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Agendamento não encontrado ou status inválido");
        }
    }

    @PutMapping("/alterar/{id}/cancelar")
    @Operation(summary = "Cancela um agendamento", description = "Rota que cancela um agendamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento cancelado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    public ResponseEntity<?> cancelarAgendamento (@PathVariable Long id) {
        AgendamentoDTO cancelado = agendamentoService.cancelarAgendamento(id);
        if (cancelado !=null) {
            return ResponseEntity.ok(cancelado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Agendamento não encontrado ou ja cancelado/concluído");
        }
    }
}
