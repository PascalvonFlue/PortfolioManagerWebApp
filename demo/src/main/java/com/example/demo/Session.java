/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import java.io.IOException;

/**
 *
 * @author Pasca
 */
public class Session {
    User usr = null;
    Holdings hld = null;

    public Session() throws NullPointerException, IOException{
        String firstname = "Hugo";
        String lastname = "Fantastisch";
        String username = "Hugo123";
        String password = "123";

        this.usr = new User(firstname,lastname,username,password);
        this.hld = usr.hld;

        Stock NIO1 = new Stock("NIO", 11, 50);
        Stock INTC = new Stock("INTC", 10, 100);
        Stock TSLA = new Stock("TSLA",100,30);
        Stock BAC = new Stock("BAC", 50, 10);

        this.hld.add(NIO1);
        this.hld.add(INTC);
        this.hld.add(TSLA);
        this.hld.add(BAC);
    }

    public Holdings getHoldings(){
        return this.hld;
    }

    public User getUser(){
        return this.usr;
    }
}
