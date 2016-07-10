/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aiop.util;

/**
 *
 * @author David .V
 */
public class Courier {
    private int code;
    private String message;
    
    public Courier(){
        this.code = 0;
        this.message = "Nenhuma mensagem foi retornada";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
