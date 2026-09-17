package com.cambio.cambio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cambio.cambio.api.Moedas;
import com.cambio.cambio.model.Favorito;
import com.cambio.cambio.repository.FavoritoRepository;


@Controller
public class FavoritoController {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @GetMapping("/favoritos")
    public String listar(Model model) {
        model.addAttribute("favoritos", favoritoRepository.findAll());
        return "favoritos/lista";
    }

    @GetMapping("/favoritos/novo")
    public String novoFormulario(Model model) {
        model.addAttribute("favorito", new Favorito());
        model.addAttribute("moedas", Moedas.CODIGOS);
        return "favoritos/form";
    }

    @GetMapping("/favoritos/editar/{id}")
    public String editarFormulario(@PathVariable Long id, Model model) {
        Favorito favorito = favoritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Favorito nao encontrado: " + id));

        model.addAttribute("favorito", favorito);
        model.addAttribute("moedas", Moedas.CODIGOS);
        return "favoritos/form";
    }


    @PostMapping("/favoritos/salvar")
    public String salvar(@ModelAttribute Favorito favorito) {
        favoritoRepository.save(favorito);
        return "redirect:/favoritos";
    }

    @GetMapping("/favoritos/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        favoritoRepository.deleteById(id);
        return "redirect:/favoritos";
    }
}
