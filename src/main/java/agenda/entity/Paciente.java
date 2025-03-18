package agenda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do paciente é obrigatório")
    private String nome;

    @NotBlank(message = "Sobrenome do paciente é obrigatório")
    private String sobrenome;

    @NotBlank(message = "CPF do paciente é obrigatório")
    private String cpf;

    @NotBlank(message = "Email do paciente é obrigatório")
    private String email;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agenda> agendas;

    public Paciente() {
    }

    public Paciente(Long id, String nome, String sobrenome, String cpf, String email, List<Agenda> agendas) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.agendas = agendas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Nome do paciente é obrigatório") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "Nome do paciente é obrigatório") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "Sobrenome do paciente é obrigatório") String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(@NotBlank(message = "Sobrenome do paciente é obrigatório") String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public @NotBlank(message = "CPF do paciente é obrigatório") String getCpf() {
        return cpf;
    }

    public void setCpf(@NotBlank(message = "CPF do paciente é obrigatório") String cpf) {
        this.cpf = cpf;
    }

    public @NotBlank(message = "Email do paciente é obrigatório") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email do paciente é obrigatório") String email) {
        this.email = email;
    }

    public List<Agenda> getAgendas() {
        return agendas;
    }

    public void setAgendas(List<Agenda> agendas) {
        this.agendas = agendas;
    }
}
