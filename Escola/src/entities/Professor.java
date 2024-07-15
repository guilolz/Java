package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import model.exceptions.DomainException;

public class Professor extends Funcionario {

    private String materia;

    public Professor(String Nome, String Cpf, LocalDate DataNasc, String Telefone, int cod, String materia) {
        super(Nome, Cpf, DataNasc, Telefone, cod);
        this.materia = materia;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("materia: [ ").append(materia);
        sb.append('}');
        return super.toString() + sb.toString();
    }
    
   


}
