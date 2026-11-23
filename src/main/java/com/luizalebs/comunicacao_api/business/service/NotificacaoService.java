package com.luizalebs.comunicacao_api.business.service;

import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.business.client.NotificacaoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificacaoService {
    private final NotificacaoClient client;

    public void enviaEmail(ComunicacaoOutDTO dto) {
        client.enviarEmail(dto);
    }
}
