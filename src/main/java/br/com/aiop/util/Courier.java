/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aiop.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David .V
 */
public class Courier {
    private int code;
    private String message;
    private List<String> messages = new ArrayList();
    private List<Object> retorno = new ArrayList();
    
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

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
    
    public void insertMessages(String message){
        this.messages.add(message);
    }

    public List<Object> getRetorno() {
        return retorno;
    }

    public void setRetorno(List<Object> retorno) {
        this.retorno = retorno;
    }
    
}
