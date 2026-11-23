package com.luizalebs.comunicacao_api.api.controller;

import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.business.service.NotificacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class NotificacaoController {
    private NotificacaoService service;
    @PostMapping
    @Operation(summary = "Enviar Email", description = "Envia email ao usuário.")
    @ApiResponse(responseCode = "200", description = "Email enviado com sucesso.")
    @ApiResponse(responseCode = "403", description = "Falha ao enviar email.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<Void> enviarEmail(@RequestBody ComunicacaoOutDTO dto) {
        service.enviaEmail(dto);
        return ResponseEntity.ok().build();
    }
}
