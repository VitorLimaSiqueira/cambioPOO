package com.cambio.cambio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class CotacaoClient {

    private static final String URL_BASE = "https://api.frankfurter.app/latest";

    @Autowired
    private RestTemplate restTemplate;


    public CotacaoResposta consultar(double valor, String moedaOrigem, String moedaDestino) {
        String url = URL_BASE + "?amount=" + valor + "&from=" + moedaOrigem + "&to=" + moedaDestino;
        return restTemplate.getForObject(url, CotacaoResposta.class);
    }


    public Double consultarCotacaoUnitaria(String moedaOrigem, String moedaDestino) {
        CotacaoResposta resposta = consultar(1, moedaOrigem, moedaDestino);
        if (resposta == null || resposta.getRates() == null) {
            return null;
        }
        return resposta.getRates().get(moedaDestino);
    }
}
