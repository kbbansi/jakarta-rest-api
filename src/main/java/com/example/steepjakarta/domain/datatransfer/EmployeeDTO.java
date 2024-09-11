package com.example.steepjakarta.domain.datatransfer;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private String displayID;
    private String email;
    private String password;
    private LocalDate birthday;
}
