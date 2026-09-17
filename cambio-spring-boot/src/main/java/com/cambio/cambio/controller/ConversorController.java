package com.cambio.cambio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cambio.cambio.api.CotacaoClient;
import com.cambio.cambio.api.CotacaoResposta;
import com.cambio.cambio.api.Moedas;
import com.cambio.cambio.model.Favorito;
import com.cambio.cambio.model.FavoritoComCotacao;
import com.cambio.cambio.repository.FavoritoRepository;


@Controller
public class ConversorController {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private CotacaoClient cotacaoClient;


    @GetMapping("/")
    public String paginaInicial(Model model) {
        model.addAttribute("moedas", Moedas.CODIGOS);
        model.addAttribute("favoritos", buscarFavoritosComCotacao());

        model.addAttribute("moedaOrigemSelecionada", "USD");
        model.addAttribute("moedaDestinoSelecionada", "BRL");

        return "index";
    }


    @PostMapping("/converter")
    public String converter(@RequestParam double valor,
                             @RequestParam String moedaOrigem,
                             @RequestParam String moedaDestino,
                             Model model) {

        model.addAttribute("moedas", Moedas.CODIGOS);
        model.addAttribute("favoritos", buscarFavoritosComCotacao());

        model.addAttribute("valorInformado", valor);
        model.addAttribute("moedaOrigemSelecionada", moedaOrigem);
        model.addAttribute("moedaDestinoSelecionada", moedaDestino);

        CotacaoResposta resposta = cotacaoClient.consultar(valor, moedaOrigem, moedaDestino);

        if (resposta != null && resposta.getRates() != null && resposta.getRates().get(moedaDestino) != null) {
            double resultado = resposta.getRates().get(moedaDestino);
            double cotacaoUnitaria = valor != 0 ? resultado / valor : 0;

            model.addAttribute("resultado", resultado);
            model.addAttribute("cotacaoUnitaria", cotacaoUnitaria);
        } else {
            model.addAttribute("erroConversao", "Nao foi possivel obter a cotacao no momento. Tente novamente.");
        }

        return "index";
    }


    private List<FavoritoComCotacao> buscarFavoritosComCotacao() {
        List<Favorito> favoritos = favoritoRepository.findAll();
        List<FavoritoComCotacao> favoritosComCotacao = new ArrayList<>();

        for (Favorito favorito : favoritos) {
            Double cotacao = cotacaoClient.consultarCotacaoUnitaria(
                    favorito.getMoedaOrigem(), favorito.getMoedaDestino());
            favoritosComCotacao.add(new FavoritoComCotacao(favorito, cotacao));
        }

        return favoritosComCotacao;
    }
}
