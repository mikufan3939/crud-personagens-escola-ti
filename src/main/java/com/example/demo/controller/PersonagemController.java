package com.example.demo.controller;

import com.example.demo.model.ItemMagico;
import com.example.demo.model.Personagem;
import com.example.demo.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {
    @Autowired
    PersonagemService personagemService;

    @PostMapping("/")
    public Personagem criarPersonagem(@RequestBody Personagem personagem){
        return personagemService.cadastrarPersonagem(personagem);
    }

    @GetMapping("/")
    public List<Personagem> listarPersonagens(){
        return personagemService.listarPersonagens();
    }

    @GetMapping("/{id}")
    public Personagem buscarPersonagemPorId(@PathVariable int id){
        return personagemService.buscarPersonagemPorId(id);
    }

    @PatchMapping("/{id}")
    public Personagem atualizarNomeAventureiro(@PathVariable int id, @RequestBody String nome){
        return personagemService.atualizarNomeAventureiro(id, nome);
    }

    @DeleteMapping("/{id}")
    public void removerPersonagem(@PathVariable int id){
        personagemService.removerPersonagem(id);
    }

    @PatchMapping("/{id}/adicionarItemMagico/{idItemMagico}")
    public Personagem adicionarItemMagico(@PathVariable(value = "id") int id, @PathVariable(value = "idItemMagico") int idItemMagico){
        return personagemService.adicionarItemMagico(id, idItemMagico);
    }

    @GetMapping("/listarItensMagicos")
    public List<List<ItemMagico>> listarItensMagicos(){
        return personagemService.listarItensMagicos();
    }

    @PatchMapping("/{id}/deletarItemMagico/{idItemMagico}")
    public Personagem removerItemMagico(@PathVariable(value = "id")int id, @PathVariable(value = "idItemMagico") int idItemMagico){
        return personagemService.removerItemMagico(id, idItemMagico);
    }

    @GetMapping("/{id}/buscarAmuleto")
    public ItemMagico buscarAmuleto(@PathVariable int id){
        return personagemService.buscarAmuleto(id);
    }
}
