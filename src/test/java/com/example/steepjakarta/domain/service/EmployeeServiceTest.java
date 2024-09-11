package com.example.steepjakarta.domain.service;

import com.example.steepjakarta.domain.dataaccess.EmployeeRepository;
import com.example.steepjakarta.domain.datatransfer.CreateEmployeeDTO;
import com.example.steepjakarta.domain.datatransfer.EmployeeDTO;
import com.example.steepjakarta.domain.models.Employee;
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
class EmployeeServiceTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    private CreateEmployeeDTO employeeDTO;
    private Employee employee;

    @BeforeEach
    void setUp() {
        employeeDTO = new CreateEmployeeDTO();
        employeeDTO.setFirstName("John");
        employeeDTO.setLastName("Doe");
        employeeDTO.setEmail("john.doe@example.com");
        employeeDTO.setBirthday(LocalDate.now());
        employeeDTO.setPassword("password");

        employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("john.doe@example.com");
        employee.setBirthday(LocalDate.now());
        employee.setPassword("password");
    }

    @Test
    void delete() {
        employeeService.delete(1L);

        verify(employeeRepository, times(1)).delete(1L);
    }

    @Test
    void create() {
        employeeService.create(employeeDTO);

        verify(employeeRepository, times(1)).create(any(Employee.class));
    }

    @Test
    void update() {
        when(employeeRepository.findById(1L)).thenReturn(employee);

        employeeService.update(1L, employeeDTO);

        verify(employeeRepository, times(1)).update(any(Employee.class));
    }

    @Test
    void get() {
        when(employeeRepository.findById(1L)).thenReturn(employee);

        EmployeeDTO result = employeeService.get(1L);

        assertEquals("John", result.getFirstName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void getAll() {
        when(employeeRepository.getAll()).thenReturn(Arrays.asList(employee));

        List<EmployeeDTO> result = employeeService.getAll();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        verify(employeeRepository, times(1)).getAll();
    }
}