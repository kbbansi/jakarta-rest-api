package com.example.steepjakarta.domain.dataaccess;

import com.example.steepjakarta.domain.models.Employee;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Transactional
@Stateless
public class EmployeeRepository {

    private static final Logger log = Logger.getLogger(EmployeeRepository.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    public String create(Employee employee) {
      log.info("Creating employee: " + employee);
      entityManager.persist(employee);
      return employee.getDisplayID();
    }

    public void update(Employee employee) {
        log.info("Updating employee: " + employee);
        entityManager.merge(employee);
    }

    public Employee findById(Long id) {
        log.info("Getting employee with display ID: " + id);
        return Optional.ofNullable(entityManager.find(Employee.class, id))
                .orElseThrow(() -> new IllegalArgumentException("Employee with display ID " + id + " not found."));
    }

    public List<Employee> getAll() {
        log.info("Getting all employees");
        return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    public void delete(Long id) {
        log.info("Deleting employee with id: " + id);
        Employee employee = findById(id);
        entityManager.remove(employee);
    }

    // TODO: add paginated results
}
