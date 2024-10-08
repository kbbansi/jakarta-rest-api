package com.example.steepjakarta.domain.datatransfer;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
//    private Long id; commenting this out as this can potentially be used by malicious actors to brute force our system
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private String displayID;
}
