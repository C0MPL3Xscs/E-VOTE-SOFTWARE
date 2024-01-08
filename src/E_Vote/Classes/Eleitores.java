/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E_Vote.Classes;

import java.io.Serializable;

/**
 *
 * @author Samuel Sampaio
 */
public class Eleitores implements Serializable{
    
    private String password;
    private String name;
    private Boolean voted;

    //Contrutor
    public Eleitores(String password, String name) {
        this.password = password;
        this.name = name;
        this.voted = false;
    }

    //Retorna o NIF do eleitor
    public String getNIF() {
        return name;
    }
    
    //Retorna o NIF do eleitor
    public String getPass() {
        return name;
    }

    //Retorna o "true" ou "false" tendo em conta se o eleitor já votou ou não
    public Boolean getVoted() {
        return voted;
    }

    //Confirma que o eleitor votou
    public void setVoted() {
        this.voted = true;
    }
    
    
}
