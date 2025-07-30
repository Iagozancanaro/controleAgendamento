package com.example.agendamentoapi.Servico;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "testando";
    }
}
