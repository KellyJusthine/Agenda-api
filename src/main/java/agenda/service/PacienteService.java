package agenda.service;


import agenda.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public Paciente salvar(Paciente paciente) {
        repository.findByCpfOrEmail(paciente.getCpf(), paciente.getEmail())
                .filter(p -> !p.getId().equals(paciente.getId()))
                .ifPresent(p -> {
                    if (p.getCpf().equals(paciente.getCpf())) {
                        throw new BusinessException("CPF já cadastrado: " + paciente.getCpf(), HttpStatus.UNPROCESSABLE_ENTITY);
                    }
                    if (p.getEmail().equals(paciente.getEmail())) {
                        throw new BusinessException("E-mail já cadastrado: " + paciente.getEmail(), HttpStatus.UNPROCESSABLE_ENTITY);
                    }
                });

        return repository.save(paciente);
    }

    public List<Paciente> listarTodos() {
        return repository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

