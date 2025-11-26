package com.login.user.domain.dto.response;

import java.util.UUID;

public record AddressResponseDTO(
        UUID id,
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf,
        String numero
) {
}
