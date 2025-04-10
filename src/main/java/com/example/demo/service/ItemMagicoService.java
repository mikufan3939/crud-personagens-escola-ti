package com.example.demo.service;

import com.example.demo.enums.TipoItem;
import com.example.demo.exception.Exceptions;
import com.example.demo.model.ItemMagico;
import com.example.demo.repository.ItemMagicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemMagicoService {
    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    public ItemMagico cadastrarItemMagico(ItemMagico itemMagico) throws Exceptions.AtributosMuitoGrandesException {
        if(itemMagico.getDefesa()+itemMagico.getForca()>10){
            throw new Exceptions.AtributosMuitoGrandesException();
        }
        else if(itemMagico.getTipoItem()==TipoItem.ARMA && itemMagico.getDefesa()>0){
            throw new Exceptions.AtributosMuitoGrandesException();
        }
        else if(itemMagico.getTipoItem()==TipoItem.ARMADURA && itemMagico.getForca()>0){
            throw new Exceptions.AtributosMuitoGrandesException();
        }
        return itemMagicoRepository.save(itemMagico);
    }

    public List<ItemMagico> listarItensMagicos(){
        return itemMagicoRepository.findAll();
    }

    public ItemMagico buscarItemMagicoPorId(int id){
        return itemMagicoRepository.findById(id).orElseThrow();
    }
}
