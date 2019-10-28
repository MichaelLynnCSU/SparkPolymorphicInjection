package edu.csu2017fa314.T30.Model.Users.User;


public class User {

    public String id;
    public String firstName;
    public String lastName;
    public String email;

    public User(){};

    public User(String id, String firstName, String lastName, String email)
    {
        this.id =  id;
        this.firstName =  firstName;
        this.lastName =  lastName;
        this.email =  email;
    }

// getter/setter
}