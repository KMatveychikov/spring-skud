package ru.matvey.springskud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matvey.springskud.model.WorkDay;

import java.util.Date;
import java.util.List;

public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {
    List<WorkDay> findWorkDaysByDateAndEmployeeId(Date date, long employeeId);
    List<WorkDay> findWorkDaysByDateBetweenAndEmployeeId(Date beginDate, Date endDate, long employeeId);
    List<WorkDay> findWorkDaysByDateBetween(Date beginDate, Date endDate);
}
