package rs.raf.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.raf.demo.model.Machine;
import rs.raf.demo.model.Status;
import rs.raf.demo.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

    @Query("select n from Machine n where" +
            " ((n.name like %:name% or :name is null) and" +
            " ((n.date between :dateFrom and :dateTo) or (:dateFrom is null or :dateTo is null)) and" +
            " (n.status in (:status) or :status is null)) and n.user.email = :email")
    List<Machine> searchMachines(String email, List<Status> status, String name, LocalDate dateFrom, LocalDate dateTo);

    List<Machine> findMachinesByUserId(Long id);
}
