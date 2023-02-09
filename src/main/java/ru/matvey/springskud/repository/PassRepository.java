package ru.matvey.springskud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.matvey.springskud.model.Pass;

import java.util.Date;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface PassRepository extends JpaRepository<Pass, Long> {
    List<Pass> findPassesByDateTimeIsBetween(Date begin, Date end);
    List<Pass> findPassesByDateTimeIsAfter(Date begin);
    List<Pass> findPassesByEmployee_NameContaining(String name);
    List<Pass> findPassesByEmployee_NameAndDateTimeIsBetween(String name, Date begin, Date end);
    List<Pass> findPassesByEmployee_NameContainingAndDateTimeIsBetween(String name, Date begin, Date end);


}
