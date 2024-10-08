package com.example.steepjakarta.api;

import com.example.steepjakarta.domain.datatransfer.CreateEmployeeDTO;
import com.example.steepjakarta.domain.datatransfer.EmployeeDTO;
import com.example.steepjakarta.domain.service.EmployeeService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeResourceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeResource employeeResource;

    private CreateEmployeeDTO createEmployeeDTO;
    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp() {
        createEmployeeDTO = new CreateEmployeeDTO();
        createEmployeeDTO.setFirstName("John");
        createEmployeeDTO.setLastName("Doe");
        createEmployeeDTO.setEmail("john.doe@example.com");
        createEmployeeDTO.setPassword("password123");
        createEmployeeDTO.setBirthday(LocalDate.now());

        employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("John");
        employeeDTO.setLastName("Doe");
        employeeDTO.setEmail("john.doe@example.com");
        employeeDTO.setBirthday(LocalDate.now());
    }

    @Test
    void testGetAllEmployees() {
        List<EmployeeDTO> employeeList = Arrays.asList(employeeDTO);
        when(employeeService.getAll()).thenReturn(employeeList);

        List<EmployeeDTO> response = employeeResource.getAllEmployees();

        assertEquals(1, response.size());
        verify(employeeService, times(1)).getAll();
    }

    @Test
    void testGetEmployeeById() {
        when(employeeService.get("1L")).thenReturn(employeeDTO);

        Response response = employeeResource.getEmployeeById("1L");

        assertEquals(200, response.getStatus());
        verify(employeeService, times(1)).get("1L");
    }


    @Test
    void testCreateEmployee() {
        String expectedId = "123e4567e89b12d3a456426614174000";
        when(employeeService.create(any(CreateEmployeeDTO.class))).thenReturn(expectedId);


        Response response = employeeResource.createEmployee(createEmployeeDTO);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(employeeService, times(1)).create(any(CreateEmployeeDTO.class));
    }

    @Test
    void testUpdateEmployee() {
        doNothing().when(employeeService).update(eq("1L"), any(CreateEmployeeDTO.class));

        Response response = employeeResource.updateEmployee("1L", createEmployeeDTO);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(employeeService, times(1)).update(eq("1L"), any(CreateEmployeeDTO.class));
    }

    @Test
    void testDeleteEmployee() {
        doNothing().when(employeeService).delete("1L");

        Response response = employeeResource.deleteEmployee("1L");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(employeeService, times(1)).delete("1L");
    }

}
