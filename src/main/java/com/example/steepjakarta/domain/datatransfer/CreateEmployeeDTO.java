package com.example.steepjakarta.domain.datatransfer;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

//@Getter
@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
public class CreateEmployeeDTO {
    @NotEmpty(message = "First name cannot be empty")
    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @Email(message = "Email is not valid")
    @NotNull(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Size(min = 8, max = 16, message = "Password must be at least 8 characters long")
    private String password;


    @Past(message = "Birthday cannot be in the future")
    private LocalDate birthday;

    public CreateEmployeeDTO() {
    }

    public CreateEmployeeDTO(String firstName, String lastName, String email, String password, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }



    @Override
    public String toString() {
        return "CreateEmployeeDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
