package com.cambio.cambio.model;


public class FavoritoComCotacao {

    private final Favorito favorito;
    private final Double cotacao; 
    
    public FavoritoComCotacao(Favorito favorito, Double cotacao) {
        this.favorito = favorito;
        this.cotacao = cotacao;
    }

    public Favorito getFavorito() {
        return favorito;
    }

    public Double getCotacao() {
        return cotacao;
    }
}
