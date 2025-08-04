package com.example.agendamentoapi.Servico;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria um novo serviço", description = "Rota cria um novo serviço e inserir no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Serviço criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do serviço")
    })
    public ResponseEntity<String> criarServico(@RequestBody ServicoDTO servico) {
        ServicoDTO novoServico = servicoService.criarServico(servico);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Serviço criado com sucesso");
    }

    @GetMapping("/listar")
    public List<ServicoDTO> listarServiços() {
        return servicoService.listarServicos();
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista o serviço por ID", description = "Rota para listar um serviço pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    public ResponseEntity<?> listarServicoPorId(@PathVariable Long id) {

        ServicoDTO servico = servicoService.buscarServicoPorId(id);
        if (servico != null) {
            return ResponseEntity.ok(servico);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Serviço com o ID: " + id + " não existente");
        }
    }

    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera o serviço", description = "Rota para alterar um serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado, não foi possível alterar")
    })
    public ResponseEntity<?> atualizarServico(@PathVariable Long id, @RequestBody ServicoDTO servicoAtualizado) {
        ServicoDTO servico = servicoService.atualizarServico(id, servicoAtualizado);
        if (servico != null) {
            return ResponseEntity.ok(servico);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O serviço que voce esta tentando atualizar não existe");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarServico(@PathVariable Long id) {

        if (servicoService.buscarServicoPorId(id) != null) {
            servicoService.deletarServico(id);
            return ResponseEntity.ok("Serviço com o ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O serviço com o ID " + id + " não foi encontrado");
        }
    }
}
