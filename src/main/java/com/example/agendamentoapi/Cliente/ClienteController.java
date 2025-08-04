package com.example.agendamentoapi.Cliente;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria um novo cliente", description = "Rota cria um novo cliente e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do cliente")
    })
    public ResponseEntity<String> criarCliente(@RequestBody ClienteDTO cliente) {
        ClienteDTO novoCliente = clienteService.criarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Cliente criado com sucesso");
    }

    @GetMapping("/listar")
    public List<ClienteDTO> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista o Cliente por ID", description = "Rota para listar um cliente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<?> listarClientePorId(@PathVariable Long id) {

        ClienteDTO cliente = clienteService.buscarClientePorId(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente com o ID: " + id + " não existente");
        }
    }

    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera o cliente", description = "Rota para alterar um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado, não foi possível alterar")
    })
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteAtualizado) {
        ClienteDTO cliente = clienteService.atualizarCliente(id, clienteAtualizado);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O cliente que voce esta tentando atualizar não existe");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarClientePorId(@PathVariable Long id) {

        if (clienteService.buscarClientePorId(id) != null) {
            clienteService.deletarCliente(id);
            return ResponseEntity.ok("Cliente com o ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O cliente com o ID " + id + " não foi encontrado");
        }
    }

}
