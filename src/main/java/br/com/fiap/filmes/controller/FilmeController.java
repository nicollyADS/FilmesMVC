package br.com.fiap.filmes.controller;

import br.com.fiap.filmes.model.Filme;
import br.com.fiap.filmes.repository.FilmeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("filme")
public class FilmeController {

    @Autowired
    private FilmeRepository filmeRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Filme filme, Model model){
        return "filme/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Filme filme, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "filme/cadastrar";
        }
        filmeRepository.save(filme);
        redirectAttributes.addFlashAttribute("mensagem", "filme cadastrado com sucesso!");
        return "redirect:/filme/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("filmes", filmeRepository.findAll());
        return "filme/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesFilme(@PathVariable Long id, Model model) {
        Optional<Filme> optionalFilme = filmeRepository.findById(id);
        if (optionalFilme.isPresent()) {
            model.addAttribute("filme", optionalFilme.get());
        } else {
            model.addAttribute("erro", "Filme não encontrado");
            return "error";
        }
        return "filme/detalhes";
    }

    @GetMapping("pesquisar")
    public String pesquisarFilmes(@RequestParam String query, Model model) {
        List<Filme> filmes = filmeRepository.findByNomeContainingIgnoreCase(query);
        model.addAttribute("filmes", filmes);
        return "filme/pesquisar";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("filme", filmeRepository.findById(id));
        return "filme/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Filme filme, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "filme/editar";
        }
        filmeRepository.save(filme);
        redirectAttributes.addFlashAttribute("mensagem", "o filme foi atualizado!");
        return "redirect:/filme/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long codigo, RedirectAttributes redirectAttributes) {
        filmeRepository.deleteById(codigo);
        redirectAttributes.addFlashAttribute("mensagem", "filme removido com sucesso");
        return "redirect:/filme/listar";
    }

}
