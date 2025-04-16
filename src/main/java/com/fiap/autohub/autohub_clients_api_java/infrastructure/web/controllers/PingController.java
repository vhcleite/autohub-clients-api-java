package com.fiap.autohub.autohub_clients_api_java.infrastructure.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/ping")
    public String ping() {
        // Adiciona log para fácil verificação no CloudWatch
        System.out.println("Ping endpoint invoked!");
        return "pong-78";
    }
}
