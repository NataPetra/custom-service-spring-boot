package it.academy.dto;

import it.academy.enam.Role;
import it.academy.validator.ValueOfRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewAppUserDTO implements Serializable {

    @Pattern(regexp = "^[a-zA-Z]{1,40}$", message = "Invalid Last Name: 40 characters, latin letters only")
    @NotEmpty(message = "Invalid Last Name: Empty last name")
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z]{1,20}$", message = "Invalid First Name: 20 characters, latin letters only")
    @NotEmpty(message = "Invalid First Name: Empty first name")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]{1,40}$", message = "Invalid Surname: 40 characters, latin letters only")
    @NotEmpty(message = "Invalid Surname: Empty surname")
    private String surname;

    @Email(message = "Invalid Email")
    @Pattern(regexp = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")
    @NotEmpty(message = "Invalid Email: Empty email")
    @Size(max = 50, message = "Invalid size: Must be of 50 characters")
    private String email;

    @NotEmpty(message = "Invalid Role: Empty role")
    @ValueOfRoleEnum(enumClass = Role.class) //version 1
    //@Pattern(regexp = "\\b(Administrator|Sale User|Customer User|Secure API User)\\b", message = "Invalid Role") version 2
    private String roleName;

    @Override
    public String toString() {
        return "NewAppUserDTO{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
