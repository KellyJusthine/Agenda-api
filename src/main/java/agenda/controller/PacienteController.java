package agenda.controller;


import agenda.entity.Paciente;
import agenda.exception.BusinessException;
import agenda.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody Paciente paciente) {
        try {
            Paciente pctSalvo = service.salvar(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(pctSalvo);
        } catch (BusinessException ex) {
            return ResponseEntity.status(ex.getStatus()).body(Map.of("error", ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> procurarTodos() {
        List<Paciente> pacientes = service.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        Optional<Paciente> optPaciente = service.buscarPorId(id);

        if(optPaciente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Paciente paciente = optPaciente.get();

        return ResponseEntity.status(HttpStatus.OK).body(optPaciente.get());
    }

    @PutMapping
    public ResponseEntity<Paciente> alterar(@RequestBody Paciente paciente) {
        Paciente salvar = service.salvar(paciente);
        return ResponseEntity.status(HttpStatus.OK).body(salvar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}


