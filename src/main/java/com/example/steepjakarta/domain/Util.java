package com.example.steepjakarta.domain;

import com.example.steepjakarta.domain.datatransfer.EmployeeDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.lang3.Validate;

import java.util.UUID;

@ApplicationScoped
public class Util {
    // util methods

    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void validateEmployee(EmployeeDTO employee) {
        Validate.notNull(employee, "Employee cannot be null");
        Validate.notEmpty(employee.getFirstName(), "First name cannot be empty");
        Validate.notEmpty(employee.getLastName(), "Last name cannot be empty");
        Validate.notEmpty(employee.getEmail(), "Email cannot be empty");
        Validate.notNull(employee.getBirthday(), "Birthday cannot be null");
        Validate.isTrue(employee.getPassword().length() >= 8, "Password must be at least 8 characters long");
        Validate.isTrue(isValidEmail(employee.getEmail()), "Email is not valid");
    }

    private static boolean isValidEmail(String email) {
        return email.matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
    }
}
