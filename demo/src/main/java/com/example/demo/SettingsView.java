/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import java.io.IOException;

/**
 *
 * @author Pasca
 */
@Route("SettingsView")
public class SettingsView extends VerticalLayout{

    public SettingsView() throws NullPointerException, IOException {

        this.debugLable = new Label();
        setDebugLable("");

        this.publiSession = (Session)VaadinSession.getCurrent().getAttribute("SESSION");
        if(this.publiSession == null){
            this.publiSession = new Session();
            setDebugLable("No User in Sessionmemmory");
        }
        this.usr = this.publiSession.getUser();
        this.hld = this.publiSession.getHoldings();

        TabSheet tabSheet = new TabSheet();

        VerticalLayout UsernameSettings = new VerticalLayout();

        TextField usernameSettingsTextField = new TextField();
        usernameSettingsTextField.setLabel("New Username");
        usernameSettingsTextField.setPlaceholder(usr.getUsername());

        TextField usernameRepeatSettingsTextField = new TextField();
        usernameRepeatSettingsTextField.setLabel("Repeat New Username");
        usernameRepeatSettingsTextField.setPlaceholder(usr.getUsername());

        UsernameSettings.add(usernameSettingsTextField,usernameRepeatSettingsTextField);

        tabSheet.add("Username Settings", UsernameSettings);


        VerticalLayout PersonalSettings = new VerticalLayout();

        TextField firstNameSettingsTextField = new TextField();
        firstNameSettingsTextField.setLabel("New Firstname");
        firstNameSettingsTextField.setPlaceholder(usr.getFistname());

        TextField lastNameSettingsTextField = new TextField();
        lastNameSettingsTextField.setLabel("New Lastname");
        lastNameSettingsTextField.setPlaceholder(usr.getLastname());

        PersonalSettings.add(firstNameSettingsTextField,lastNameSettingsTextField);

        tabSheet.add("Personal Settings", PersonalSettings);


        VerticalLayout PasswordSettings = new VerticalLayout();

        TextField passwordSettingsTextField = new TextField();
        passwordSettingsTextField.setLabel("New Password");

        TextField passwordRepeatSettingsTextField = new TextField();
        passwordRepeatSettingsTextField.setLabel("Repeat New Password");

        PasswordSettings.add(passwordSettingsTextField,passwordRepeatSettingsTextField);

        tabSheet.add("Password Settings", PasswordSettings);


        HorizontalLayout ProfileImageSettings = new HorizontalLayout();

        Label imageMaleLable = new Label();
        imageMaleLable.setText("Male Profile Image.");
        Image imageMale = new Image("https://cdn.pixabay.com/photo/2017/07/18/23/23/user-2517433_960_720.png","ImageMale");
        imageMale.setWidth("40%");
        Checkbox imageMalecheckbox = new Checkbox();
        imageMalecheckbox.setLabel("Profileimage Male");

        Label imageFemaleLable = new Label();
        imageFemaleLable.setText("Female Profile Image.");
        Image imageFemale = new Image("https://cdn.pixabay.com/photo/2018/11/13/22/01/avatar-3814081_960_720.png","ImageFemale");
        imageFemale.setWidth("40%");
        Checkbox imageFemalecheckbox = new Checkbox();
        imageFemalecheckbox.setLabel("Profileimage Female");

        if(usr.getProfilePicture().equals(imageMale.getSrc())){
            imageMalecheckbox.setValue(true);
            imageFemalecheckbox.setValue(false);
        } else {
           imageMalecheckbox.setValue(false);
           imageFemalecheckbox.setValue(true);
        }

        ProfileImageSettings.add(new VerticalLayout(imageMaleLable,imageMale,imageMalecheckbox),
                                new VerticalLayout(imageFemaleLable,imageFemale,imageFemalecheckbox));

        tabSheet.add("Image Settings",ProfileImageSettings);

        Button applyButton = new Button("Apply Settings");
        Button backButton = new Button("Back Portfolio");

        imageFemalecheckbox.addValueChangeListener(event ->{
            if(imageFemalecheckbox.getValue()){
                imageMalecheckbox.setValue(Boolean.FALSE);
            } else {
                imageMalecheckbox.setValue(Boolean.TRUE);
            }
        });

        imageMalecheckbox.addValueChangeListener(event ->{
            if(imageMalecheckbox.getValue()){
                imageFemalecheckbox.setValue(Boolean.FALSE);
            } else {
                imageFemalecheckbox.setValue(Boolean.TRUE);
            }
        });

        applyButton.addClickListener(clickEvent -> {
            if(!usernameSettingsTextField.getValue().isBlank()){
                if(usernameSettingsTextField.getValue().equals(usernameRepeatSettingsTextField.getValue())){
                    this.usr.setUsername(usernameSettingsTextField.getValue());
                }
            }
            if(!firstNameSettingsTextField.getValue().isBlank()){
                this.usr.setFirstname(firstNameSettingsTextField.getValue());
            }
            if(!lastNameSettingsTextField.getValue().isBlank()){
                this.usr.setLastname(lastNameSettingsTextField.getValue());
            }
            if(!passwordSettingsTextField.getValue().isBlank()){
                if(passwordSettingsTextField.getValue().equals(passwordRepeatSettingsTextField.getValue())){
                    this.usr.setPassword(passwordSettingsTextField.getValue());
                }
            }
            if(imageMalecheckbox.getValue()){
                this.usr.setProfilePicture(imageMale.getSrc());
            } else {
                this.usr.setProfilePicture(imageFemale.getSrc());
            }

            setDebugLable("Applied settings");
        });

        backButton.addClickListener(clickEvent -> {
                VaadinSession.getCurrent().setAttribute("SESSION", publiSession);
                UI.getCurrent().navigate(MainView.class);
        });

        tabSheet.setSizeFull();

        add(tabSheet,new HorizontalLayout(backButton,applyButton),debugLable);
    }



    Session publiSession = null;
    User usr = null;
    Holdings hld = null;

    Label debugLable = null;

    public void setDebugLable(String text){
        this.debugLable.setText("Log: " + text);
    }
}
