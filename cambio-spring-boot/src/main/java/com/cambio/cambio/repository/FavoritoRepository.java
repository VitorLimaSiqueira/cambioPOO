package com.cambio.cambio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cambio.cambio.model.Favorito;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
}
