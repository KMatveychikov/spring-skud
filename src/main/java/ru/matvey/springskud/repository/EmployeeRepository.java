package ru.matvey.springskud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.matvey.springskud.model.Employee;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByName(String name);
    List<Employee> findByNameContaining(String name);
    Employee findByCardId(String cardId);
    Employee findById(long id);
}
