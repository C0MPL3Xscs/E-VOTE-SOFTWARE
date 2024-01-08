/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E_Vote.Classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Samuel Sampaio
 */
public class Candidatos implements Serializable{
    
    private String name;
    private int numeroVotos;
    private ArrayList<Eleitores> listaVotantes;

    //Contrutor
    public Candidatos(String name) {
        this.name = name;
        listaVotantes = new ArrayList<Eleitores>();
    }

    //Retorna o nome do candidato
    public String getName() {
        return name;
    }

    //Retorna o numero de votos
    public int getNumeroVotos() {
        return numeroVotos;
    }

    //Retorna a lista de eleitores votantes
    public ArrayList<Eleitores> getListaVotantes() {
        return listaVotantes;
    }
    
    //Adiciona um eleitor à lista de votantes
    public void addVoto(Eleitores eleitor) {
        this.numeroVotos++;
        this.listaVotantes.add(eleitor);
    }
}
