/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca1java;

import java.io.Serializable;

/**
 *
 * THIS FILE JUST SIMULATE MY GROUP FRIENDS FILES SO I CAN SIMULATE THE MY INHERITANCES WITH THIS FILE
 * THIS FILE NOT COUNT AS A CLASS FILE IN MY INDIVIDUAL SUBMISSIONS
 * 
 */
public class inheritance implements Serializable{

    private String name;
    private String email;
    
    public inheritance(){
        name = " ";
        email = " ";
    }
    
   public inheritance(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getDetails(){
        return name+","+email;
    }
}
