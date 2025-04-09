package com.example.demo.service;

import com.example.demo.model.ItemMagico;
import com.example.demo.repository.ItemMagicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemMagicoService {
    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    public ItemMagico cadastrarItemMagico(ItemMagico itemMagico){
        return itemMagicoRepository.save(itemMagico);
    }

    public List<ItemMagico> listarItensMagicos(){
        return itemMagicoRepository.findAll();
    }

    public ItemMagico buscarItemMagicoPorId(int id){
        return itemMagicoRepository.findById(id).orElseThrow();
    }
}
