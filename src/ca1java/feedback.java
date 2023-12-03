/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca1java;

import java.io.Serializable;

/**
 *
 * @author ataturkoglu
 */
public class feedback extends inheritance implements Serializable {
    private String feedback;
    
    public feedback(String name, String email) {
        super(name, email);
        this.feedback = feedback;
    }
    
    public feedback() {
        super();
        feedback = " ";
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
