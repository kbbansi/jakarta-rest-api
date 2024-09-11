package com.example.steepjakarta.domain;
import com.example.steepjakarta.domain.datatransfer.CreateEmployeeDTO;
import com.example.steepjakarta.domain.datatransfer.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UtilTest {

    @Test
    void testGenerateUUID() {
        String uuid = Util.generateUUID();

        assertNotNull(uuid);
        assertEquals(32, uuid.length());
    }

    @Test
    void testValidateEmployee() {
        CreateEmployeeDTO employeeDTO = new CreateEmployeeDTO();
        employeeDTO.setFirstName("John");
        employeeDTO.setLastName("Doe");
        employeeDTO.setEmail("john.doe@example.com");
        employeeDTO.setBirthday(LocalDate.now());
        employeeDTO.setPassword("password123");

        assertDoesNotThrow(() -> Util.validateEmployee(employeeDTO));
    }

    @Test
    void testValidateEmployeeInvalidEmail() {
        CreateEmployeeDTO employeeDTO = new CreateEmployeeDTO();
        employeeDTO.setFirstName("John");
        employeeDTO.setLastName("Doe");
        employeeDTO.setEmail("invalid-email");
        employeeDTO.setBirthday(LocalDate.now());
        employeeDTO.setPassword("password123");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> Util.validateEmployee(employeeDTO));
        assertEquals("Email is not valid", exception.getMessage());
    }
}
