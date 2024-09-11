package com.example.steepjakarta.domain.service;

import com.example.steepjakarta.domain.Util;
import com.example.steepjakarta.domain.dataaccess.EmployeeRepository;
import com.example.steepjakarta.domain.datatransfer.CreateEmployeeDTO;
import com.example.steepjakarta.domain.datatransfer.EmployeeDTO;
import com.example.steepjakarta.domain.models.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EmployeeService implements BasicCRUDService<CreateEmployeeDTO, EmployeeDTO> {

    @Inject
    EmployeeRepository employeeRepository;



    private ModelMapper mapper = new ModelMapper();

    @Override
    public void delete(Long id) {
        employeeRepository.delete(id);
    }

    @Override
    public String create(CreateEmployeeDTO employeeDto) {
        Util.validateEmployee(employeeDto);

        Employee employee = mapToEmployee(employeeDto);
        return employeeRepository.create(employee);
    }


    @Override
    public void update(Long id, CreateEmployeeDTO employeeDTO) {
        Util.validateEmployee(employeeDTO);

        Employee employee = employeeRepository.findById(id);
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setBirthday(employeeDTO.getBirthday());

        employeeRepository.update(employee);

    }

    @Override
    public EmployeeDTO get(Long id) {
        // TODO
        return mapper.map(employeeRepository.findById(id), EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getAll() {
        // TODO
        return employeeRepository.getAll()
                .stream()
                .map(this::mapToEmployeeDTO)
                .collect(Collectors.toList());
    }


    private Employee mapToEmployee(CreateEmployeeDTO employeeDto) {
        return Employee.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .email(employeeDto.getEmail())
                .displayID(Util.generateUUID())
                .birthday(employeeDto.getBirthday())
                .build();
    }

    private EmployeeDTO mapToEmployeeDTO(Employee employee) {
        return mapper.map(employee, EmployeeDTO.class);
    }
}
