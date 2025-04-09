package com.example.demo.service;

import com.example.demo.enums.TipoItem;
import com.example.demo.model.ItemMagico;
import com.example.demo.model.Personagem;
import com.example.demo.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonagemService {
    @Autowired
    private PersonagemRepository personagemRepository;
    @Autowired
    private ItemMagicoService itemMagicoService;

    public Personagem cadastrarPersonagem(Personagem personagem){
        if(personagem.getDefesa()+ personagem.getForca()>10){
            return new Personagem();
        }
        return personagemRepository.save(personagem);
    }

    public List<Personagem> listarPersonagens(){
        return personagemRepository.findAll();
    }

    public Personagem buscarPersonagemPorId(int id){
        return personagemRepository.findById(id).orElseThrow();
    }

    public Personagem atualizarNomeAventureiro(int id, String novoNome){
        Personagem personagemSelecionado=personagemRepository.findById(id).orElseThrow();
        personagemSelecionado.setNomeAventureiro(novoNome);
        return personagemRepository.save(personagemSelecionado);
    }

    public void removerPersonagem(int id){
        personagemRepository.deleteById(id);
    }

    public Personagem adicionarItemMagico(int id, int idItemMagico){
        Personagem personagemSelecionado=buscarPersonagemPorId(id);
        ItemMagico itemMagico=itemMagicoService.buscarItemMagicoPorId(idItemMagico);

        personagemSelecionado.getItemMagicoList().add(itemMagico);
        System.out.println(personagemSelecionado.toString());
        return personagemRepository.save(personagemSelecionado);
    }

    public List<List<ItemMagico>> listarItensMagicos(){
        return listarPersonagens().stream().map(x->x.getItemMagicoList()).collect(Collectors.toList());
    }

    public Personagem removerItemMagico(int id, int idItemMagico){
        int index=0;
        Personagem personagemSelecionado=buscarPersonagemPorId(id);
        for(ItemMagico itemMagico : personagemSelecionado.getItemMagicoList()){
            if(itemMagico.getId()==idItemMagico){
                    personagemSelecionado.getItemMagicoList().remove(index);
                    return personagemRepository.save(personagemSelecionado);
            }
            index++;
        }
        return personagemSelecionado;
    }

    public ItemMagico buscarAmuleto(int id){
        Personagem personagem=buscarPersonagemPorId(id);
        for(ItemMagico itemMagico : personagem.getItemMagicoList()){
            if(itemMagico.getTipoItem()== TipoItem.AMULETO){
                return itemMagico;
            }
        }
        return new ItemMagico();
    }

}
