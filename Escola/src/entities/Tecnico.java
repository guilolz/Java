package entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Tecnico extends Funcionario {

    private String departamento;

    public Tecnico(String Nome, String Cpf, LocalDate DataNasc, String Telefone, int cod, String departamento) {
        super(Nome, Cpf, DataNasc, Telefone, cod);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("departamento: [ ").append(departamento);
        sb.append('}');
        return super.toString() + sb.toString();
    }



}
