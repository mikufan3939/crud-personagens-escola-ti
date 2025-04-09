package com.example.demo.controller;

import com.example.demo.model.ItemMagico;
import com.example.demo.service.ItemMagicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itensMagicos")
public class ItemMagicoController {
    @Autowired
    private ItemMagicoService itemMagicoService;

    @PostMapping("/")
    public ItemMagico cadastrarItemMagico(@RequestBody ItemMagico itemMagico){
        return itemMagicoService.cadastrarItemMagico(itemMagico);
    }

    @GetMapping("/")
    public List<ItemMagico> listarItensMagicos(){
        return itemMagicoService.listarItensMagicos();
    }

    @GetMapping("/{id}")
    public ItemMagico buscarItemMagicoPorId(@PathVariable int id){
        return itemMagicoService.buscarItemMagicoPorId(id);
    }
}
