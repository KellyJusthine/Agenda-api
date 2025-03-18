package agenda.service;


import agenda.entity.Agenda;
import agenda.entity.Paciente;
import agenda.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AgendaService {

    @Autowired
    private AgendaRepository repository;

    @Autowired
    private PacienteService pacienteService;

    public List<Agenda> listarTodos() {
        return repository.findAll();
    }

    public Optional<Agenda> buscarPorId (Long id) {
        return repository.findById(id);
    }

    public Agenda salvar(Agenda agenda) {
        if (agenda.getPacienteId() == null) {
            throw new BusinessException("Paciente ID não pode ser nulo", HttpStatus.BAD_REQUEST);
        }

        Paciente paciente = pacienteService.buscarPorId(agenda.getPacienteId())
                .orElseThrow(() -> new BusinessException("Paciente não encontrado", HttpStatus.NOT_FOUND));

        repository.findByHorario(agenda.getHorario())
                .ifPresent(a -> {
                    throw new BusinessException("Já existe agendamento para este horário", HttpStatus.UNPROCESSABLE_ENTITY);
                });

        agenda.setPaciente(paciente);
        agenda.setDataCriacao(LocalDateTime.now());

        return repository.save(agenda);
    }
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

