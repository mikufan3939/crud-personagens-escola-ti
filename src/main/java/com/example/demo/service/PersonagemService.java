package com.example.demo.service;

import com.example.demo.enums.TipoItem;
import com.example.demo.exception.Exceptions;
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

    public Personagem cadastrarPersonagem(Personagem personagem) throws Exceptions.AtributosMuitoGrandesException {
        if(personagem.getDefesa()+ personagem.getForca()>10){
            throw new Exceptions.AtributosMuitoGrandesException();
        }
        return personagemRepository.save(personagem);
    }

    public List<Personagem> listarPersonagens(){
        return personagemRepository.findAll().stream().map(personagem -> personagem.somarAtributosItens()).collect(Collectors.toList());
    }

    public Personagem buscarPersonagemPorId(int id){
        Personagem personagemSelecionado=personagemRepository.findById(id).orElseThrow();
        return personagemSelecionado.somarAtributosItens();
    }

    public Personagem atualizarNomeAventureiro(int id, String novoNome){
        Personagem personagemSelecionado=personagemRepository.findById(id).orElseThrow();
        personagemSelecionado.setNomeAventureiro(novoNome);
        return personagemRepository.save(personagemSelecionado);
    }

    public void removerPersonagem(int id){
        personagemRepository.deleteById(id);
    }

    public Personagem adicionarItemMagico(int id, int idItemMagico) throws Exceptions.PersonagemJaTemAmuletoException{
        Personagem personagemSelecionado=personagemRepository.findById(id).orElseThrow();
        ItemMagico itemMagico=itemMagicoService.buscarItemMagicoPorId(idItemMagico);

        if(itemMagico.getTipoItem()==TipoItem.AMULETO && personagemSelecionado.temAmuleto()){
            throw new Exceptions.PersonagemJaTemAmuletoException();
        }
        personagemSelecionado.getItemMagicoList().add(itemMagico);
        System.out.println(personagemSelecionado.toString());
        return personagemRepository.save(personagemSelecionado);
    }

    public List<ItemMagico> listarItensMagicos(int id){
        Personagem personagemSelecionado=personagemRepository.findById(id).orElseThrow();
        return personagemSelecionado.getItemMagicoList();
    }

    public Personagem removerItemMagico(int id, int idItemMagico){
        int index=0;
        Personagem personagemSelecionado=personagemRepository.findById(id).orElseThrow();
        for(ItemMagico itemMagico : personagemSelecionado.getItemMagicoList()){
            if(itemMagico.getId()==idItemMagico){
                    //personagemSelecionado.removerAtributosItens();
                    personagemSelecionado.getItemMagicoList().remove(index);

                    System.out.println(personagemSelecionado.toString());
                    return personagemRepository.save(personagemSelecionado);
            }
            index++;
        }
        return personagemSelecionado;
    }

    public ItemMagico buscarAmuleto(int id){
        Personagem personagemSelecionado=personagemRepository.findById(id).orElseThrow();
        for(ItemMagico itemMagico : personagemSelecionado.getItemMagicoList()){
            if(itemMagico.getTipoItem()== TipoItem.AMULETO){
                return itemMagico;
            }
        }
        return new ItemMagico();
    }

}
