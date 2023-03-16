/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import java.io.IOException;

/**
 *
 * @author Pasca
 */

@Route("")
public class LoginView extends VerticalLayout{
    public LoginView() throws NullPointerException, IOException{
        this.publicSession = new Session();
        TextField userNameField = new TextField();
        userNameField.setLabel("Username");
        userNameField.setPlaceholder("");

        PasswordField passwordField = new PasswordField();
        passwordField.setLabel("Password");
        passwordField.setValue("");
        add(passwordField);

        Button loginButton = new Button("Login");
        loginButton.setEnabled(true);

        this.debugLable = new Label();
        this.debugLable.setText("Log: ");

        loginButton.addClickListener(clickEvent -> {
            User newUser = publicSession.getUser();
            if(newUser.getUsername().equals(userNameField.getValue())){
                setDebugLable("Login Sucess");
                VaadinSession.getCurrent().setAttribute("SESSION", publicSession);
                UI.getCurrent().navigate(MainView.class);
            } else {
                setDebugLable("Login Failed");
                userNameField.setValue("");
                passwordField.setValue("");
            }
        });

        VerticalLayout completeLayout = new VerticalLayout();
        completeLayout.add(new H1("Welcome"),
                userNameField,
                passwordField,
                loginButton,
                debugLable);
        completeLayout.setAlignItems(Alignment.CENTER);

        add(completeLayout);

    }

    Label debugLable = null;
    Session publicSession = null;

    public void setDebugLable(String text){
        debugLable.setText("Log: " + text);
    }
}
