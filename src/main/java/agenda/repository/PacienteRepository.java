package agenda.repository;

import agenda.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("SELECT p FROM Paciente p WHERE p.cpf = :cpf OR p.email = :email")
    Optional<Paciente> findByCpfOrEmail(@Param("cpf") String cpf, @Param("email") String email);

}
