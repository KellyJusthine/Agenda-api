package agenda.controller;

import agenda.entity.Agenda;
import agenda.exception.BusinessException;
import agenda.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService service;

    @GetMapping
    public ResponseEntity<List<Agenda>> buscarTodos() {
        List<Agenda> agenda = service.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(agenda);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agenda> buscarPorId(@PathVariable Long id){
        Optional<Agenda> agendaOpt = service.buscarPorId(id);

        if (agendaOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Agenda agenda = agendaOpt.get();

        return ResponseEntity.status(HttpStatus.OK).body(agenda);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Agenda agenda) {
        try {
            Agenda agendaSalva = service.salvar(agenda);
            return ResponseEntity.status(HttpStatus.CREATED).body(agendaSalva);
        } catch (BusinessException ex) {
            return ResponseEntity.status(ex.getStatus()).body(Map.of("error", ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
