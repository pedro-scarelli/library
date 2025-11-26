package com.login.user.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import com.login.user.domain.dto.response.ViaCepResponseDTO;

@Service
public class ViaCepService {

    private static final String VIACEP_URL = "https://viacep.com.br/ws/{cep}/json/";
    private final RestTemplate restTemplate;

    public ViaCepService() {
        this.restTemplate = new RestTemplate();
    }

    public ViaCepResponseDTO buscarCep(String cep) {
        try {
            String cepLimpo = cep.replaceAll("[^0-9]", "");

            if (cepLimpo.length() != 8) {
                throw new IllegalArgumentException("CEP deve conter 8 dígitos");
            }

            System.out.println(cepLimpo);

            ViaCepResponseDTO response = restTemplate.getForObject(
                    VIACEP_URL,
                    ViaCepResponseDTO.class,
                    cepLimpo
            );

            System.out.println(response);

            if (response != null && Boolean.TRUE.equals(response.getErro())) {
                throw new IllegalArgumentException("CEP não encontrado");
            }

            return response;
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("Erro ao buscar CEP: " + e.getMessage());
        }
    }
}