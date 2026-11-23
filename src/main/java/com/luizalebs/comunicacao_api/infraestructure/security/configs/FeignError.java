package com.luizalebs.comunicacao_api.infraestructure.security.configs;

import com.luizalebs.comunicacao_api.infraestructure.exceptions.BusinessException;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.ConflictException;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.IllegalArgumentException;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.ResourceNotFoundException;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {

        String mensagemErr = mensagemErr(response);
        String err = "Error: ";

        switch(response.status()) {
            case 400:
                return new BusinessException(err + mensagemErr);

            case 401:
               return new IllegalArgumentException(err  + mensagemErr);
            case 403:
                return new UnauthorizedException(err + mensagemErr);
            case 404:
                return new ResourceNotFoundException(err + mensagemErr);
            case 409:
                return new ConflictException(err + mensagemErr);
            default:
                return new RuntimeException(
                        "HTTP " + response.status() + ": " + mensagemErr
                );
        }
    }
    private String mensagemErr(Response res) {
        try {
            if(Objects.isNull(res.body())) {
                return "";
            }
            return new String(res.body()
                    .asInputStream()
                    .readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
