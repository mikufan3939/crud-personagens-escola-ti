package com.example.demo.model;

import com.example.demo.enums.Classe;
import com.example.demo.enums.TipoItem;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    private String nomeAventureiro;

    private Classe classe;

    private int level;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "personagem_id")
    private List<ItemMagico> itemMagicoList;
    private int forca;

    private int defesa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<ItemMagico> getItemMagicoList() {
        return itemMagicoList;
    }

    public void setItemMagicoList(List<ItemMagico> itemMagicoList) {
        this.itemMagicoList = itemMagicoList;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    @Override
    public String toString() {
        return "Personagem{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nomeAventureiro='" + nomeAventureiro + '\'' +
                ", classe=" + classe +
                ", level=" + level +
                ", itemMagicoList=" + itemMagicoList +
                ", forca=" + forca +
                ", defesa=" + defesa +
                '}';
    }

    public Personagem somarAtributosItens(){
        for(ItemMagico itemMagico : this.itemMagicoList){
            this.forca=itemMagico.getForca()+this.forca;
            this.defesa=itemMagico.getDefesa()+this.defesa;
        }
        return this;
    }

    public Personagem removerAtributosItens(){
        for(ItemMagico itemMagico : this.itemMagicoList){
            this.forca=this.forca-itemMagico.getForca();
            this.defesa=this.defesa-itemMagico.getDefesa();
        }
        return this;
    }

    public Boolean temAmuleto(){
        for(ItemMagico itemMagico : this.itemMagicoList){
            if(itemMagico.getTipoItem()== TipoItem.AMULETO){
                return true;
            }
        }
        return false;
    }
}
