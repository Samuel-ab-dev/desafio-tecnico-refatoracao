package com.luizalebs.comunicacao_api.api.controller;

import com.luizalebs.comunicacao_api.api.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.business.service.ComunicacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comunicação", description = "Métodos dos Comunicação.")
@RestController
@RequestMapping("/comunicacao")
public class ComunicacaoController {

    private final ComunicacaoService service;

    public ComunicacaoController(ComunicacaoService service) {
        this.service = service;
    }

    @PostMapping("/agendar")
    @Operation(summary = "Agendar Comunicacao", description = "Agenda nova comunicação.")
    @ApiResponse(responseCode = "200", description = "Comunicação criada com sucesso.")
    @ApiResponse(responseCode = "400", description = "Comunicação já cadastrada.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<ComunicacaoOutDTO> agendar(@RequestBody ComunicacaoInDTO dto) {
        return ResponseEntity.ok(service.agendarComunicacao(dto));
    }

    @GetMapping()
    @Operation(summary = "Busca Status da Comunicação por Email",
            description = "Busca o status da Comunicação através do Email.")
    @ApiResponse(responseCode = "200", description = "Status encontrado.")
    @ApiResponse(responseCode = "403", description = "Erro ao buscar comunicação.")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<ComunicacaoOutDTO> buscarStatus(@RequestParam String emailDestinatario) {
        return ResponseEntity.ok(service.buscarStatusComunicacao(emailDestinatario));
    }

    @PatchMapping("/cancelar")
    @Operation(summary = "Cancelar Comunicacao", description = "Cancela comunicação requisitada através do email.")
    @ApiResponse(responseCode = "200", description = "Comunicação cancelada com sucesso.")
    @ApiResponse(responseCode = "404", description = "Comunicação não encontrada.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    @ApiResponse(responseCode = "403", description = "Email de usuário não encontrado.")
    public ResponseEntity<ComunicacaoOutDTO> cancelarStatus(@RequestParam String emailDestinatario) {
        return ResponseEntity.ok(service.alterarStatusComunicacao(emailDestinatario));
    }
}
