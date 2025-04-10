package com.example.demo.controller;

import com.example.demo.exception.Exceptions;
import com.example.demo.model.ItemMagico;
import com.example.demo.service.ItemMagicoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/itensMagicos")
public class ItemMagicoController {
    @Autowired
    private ItemMagicoService itemMagicoService;

    @PostMapping("")
    public ResponseEntity<ItemMagico> cadastrarItemMagico(@RequestBody ItemMagico itemMagico){
        try{
            return new ResponseEntity<>(itemMagicoService.cadastrarItemMagico(itemMagico), HttpStatus.CREATED);
        }
        catch(Exceptions.AtributosMuitoGrandesException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Atributos acima do valor permitido!");
        }

    }

    @GetMapping("")
    public List<ItemMagico> listarItensMagicos(){
        return itemMagicoService.listarItensMagicos();
    }

    @GetMapping("/{id}")
    public ItemMagico buscarItemMagicoPorId(@PathVariable int id){
        try{
            return itemMagicoService.buscarItemMagicoPorId(id);
        }
        catch(NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nao encontrado!");
        }
    }
}
