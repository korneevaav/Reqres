package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class User {
    @Expose
    int id;
    @Expose
    String username;
    @Expose
    String firstName;
    @Expose
    String lastName;
    @Expose
    String email;
    @Expose
    String password;
    @Expose
    String phone;
    @Expose
    int userStatus;

    public User(String username, String firstName, String lastName, String email, String password, String phone) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}
