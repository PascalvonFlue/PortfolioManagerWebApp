package com.example.demo;

/**
 *
 * @author Pasca
 */
public class User {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String profileImage = "https://cdn.pixabay.com/photo/2017/07/18/23/23/user-2517433_960_720.png";

    Holdings hld = null;

    public User(String _firstname, String _lastname, String _username, String _password){
        this.firstname = _firstname;
        this.lastname = _lastname;
        this.username = _username;
        this.password = _password;
        this.hld = new Holdings();
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String _username){
        this.username = _username;
    }

    public String getFistname(){
        return this.firstname;
    }

    public void setFirstname(String _firstname){
        this.firstname = _firstname;
    }

    public String getLastname(){
        return this.lastname;
    }

    public void setLastname(String _lastname){
        this.lastname = _lastname;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String _password){
        this.password = _password;
    }

    public String getProfilePicture(){
        return this.profileImage;
    }

    public void setProfilePicture(String _profileImage){
        this.profileImage = _profileImage;
    }

}
