package com.example.demo.model;

import com.example.demo.enums.Classe;
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

    @OneToMany(mappedBy = "personagem")
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
}
