package be.iccbxl.pid.controller.dto;
//data transfert object to validate the user registration form

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class UserRegistrationDto {

    @Size(min =4 , max = 60, message = " Username should have at least 4 characters and max 30 characters")
    @NotEmpty(message = "value is required and can t be empty")
    private String login;

    @Size(min = 4, max = 30, message = " Password should have at least 4 characters and max 30 characters" )
    @NotEmpty(message = "value is required and can t be empty")
    private String password;

    @Size(min = 2, max = 60 , message = "Firstname should have at least 2 characters and max 60 characters")
    @NotEmpty(message = "value is required and can t be empty")
    private String firstname;

    @Size(min = 2, max = 60 , message = "LastName should have at least 2 characters and max 60 characters")
    @NotEmpty(message = "Value is required and can t be empty")
    private String lastname;

    @Email
    @NotEmpty(message = "Value is required and can t be empty")
    private String email;

    @Size(min=2, max=2, message= "Only 2 character")
    @NotEmpty(message = "Value is required and can t be empty")
    private String langue;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String login, String password, String firstname, String lastname, String email, String langue) {
        super();
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.langue = langue;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }
}


