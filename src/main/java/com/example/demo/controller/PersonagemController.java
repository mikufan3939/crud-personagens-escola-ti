package com.example.demo.controller;

import com.example.demo.exception.Exceptions;
import com.example.demo.model.ItemMagico;
import com.example.demo.model.Personagem;
import com.example.demo.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {
    @Autowired
    PersonagemService personagemService;

    @PostMapping("")
    public ResponseEntity<Personagem> criarPersonagem(@RequestBody Personagem personagem){
        try{
            return new ResponseEntity<>(personagemService.cadastrarPersonagem(personagem), HttpStatus.CREATED);
        }
        catch(Exceptions.AtributosMuitoGrandesException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Atributos acima do valor permitido!");
        }
    }

    @GetMapping("")
    public List<Personagem> listarPersonagens(){
        return personagemService.listarPersonagens();
    }

    @GetMapping("/{id}")
    public Personagem buscarPersonagemPorId(@PathVariable int id){
        try{
            return personagemService.buscarPersonagemPorId(id);
        }
        catch(NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nao encontrado!");
        }
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
    public ResponseEntity<Personagem> adicionarItemMagico(@PathVariable(value = "id") int id, @PathVariable(value = "idItemMagico") int idItemMagico){
        try{
            return new ResponseEntity<>(personagemService.adicionarItemMagico(id, idItemMagico), HttpStatus.OK);
        }
        catch(Exceptions.PersonagemJaTemAmuletoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Personagem Ja Tem Atributo!");
        }
    }

    @GetMapping("/{id}/listarItensMagicos")
    public List<ItemMagico> listarItensMagicos(@PathVariable int id){
        return personagemService.listarItensMagicos(id);
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
