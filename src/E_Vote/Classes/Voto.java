/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E_Vote.Classes;

import java.io.Serializable;
import utils.Serializer;

/**
 *
 * @author Samuel Sampaio
 */
public class Voto implements Serializable{
    
    private int Eleitor;
    private String Candidato;

    //Contrutor
    public Voto(int Eleitor, String Candidato) {
        this.Eleitor = Eleitor;
        this.Candidato = Candidato;
    }
    
    @Override
    public String toString(){
        return Eleitor + " votou em " + Candidato;
    }
    
    public String getCandidate(){
        return this.Candidato;
    }
    
    public String toText() {
        return Serializer.objectToBase64(this);
    }
    
    public static Voto fromText(String obj) {
        return (Voto)Serializer.base64ToObject(obj);
    }
    
}
