package stefanini.acelera.controlevendas.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3)
    @NotNull
    private String name;

    @NotNull
    private Date dateBirth;

    @CPF
    private String cpf;

    @NotNull
    private String cep;

    public User(String name, Date dateBirth, String cpf, String cep) {
        this.name = name;
        this.dateBirth = dateBirth;
        this.cpf = cpf;
        this.cep = cep;
    }

    public String getName() {
        return name;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
