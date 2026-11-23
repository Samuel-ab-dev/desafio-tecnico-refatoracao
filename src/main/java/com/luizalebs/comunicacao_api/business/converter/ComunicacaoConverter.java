package com.luizalebs.comunicacao_api.business.converter;

import com.luizalebs.comunicacao_api.api.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComunicacaoConverter {
    //Comunicação de entrada.
    ComunicacaoEntity paraComunicacaoEntity(ComunicacaoInDTO dto);

    ComunicacaoInDTO paraComunicacaoInDTO(ComunicacaoEntity comunicacaoEntity);

    List<ComunicacaoEntity> paraListEntity(List<ComunicacaoInDTO> dto);

    List<ComunicacaoInDTO> paraListDTO(List<ComunicacaoEntity> comunicacaoEntity);

    //Comunicação de saída.
    ComunicacaoEntity paraComunicacaoEntity(ComunicacaoOutDTO dto);

    ComunicacaoOutDTO paraComunicacaoOutDTO(ComunicacaoEntity comunicacaoEntity);

    List<ComunicacaoEntity> paraListEntityOut(List<ComunicacaoOutDTO> dto);

    List<ComunicacaoOutDTO> paraListOutDTO(List<ComunicacaoEntity> comunicacaoEntity);
}
