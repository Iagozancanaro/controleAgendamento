package com.example.demo.Agendamento;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "mais um teste";
    }
}
