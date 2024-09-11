package com.example.steepjakarta.domain.datatransfer;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthday;
}
