package entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import model.exceptions.DomainException;

public class Alunos extends Pessoas {

    private String IRA;
    private String RA;

    public Alunos(String Nome, String Cpf, LocalDate DataNasc, String Telefone, String IRA, String RA) {
        super(Nome, Cpf, DataNasc, Telefone);
        this.IRA = IRA;
        this.RA = RA;
    }

    public String getIRA() {
        return IRA;
    }

    public void setIRA(String IRA) {
        this.IRA = IRA;
    }

    public String getRA() {
        return RA;
    }

    public void setRA(String RA) {
        this.RA = RA;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("IRA: [ ").append(IRA);
        sb.append("]\n");
        sb.append("RA:[ ").append(RA);
        sb.append(" ]");

        return super.toString() + sb.toString();
    }

    public static void checkUsuario(List<Alunos> ListAlunos, String verificar) {
    	boolean aux = false;
        for (Alunos x : ListAlunos) {
            if (x.getCpf().equals(verificar)) {
                JOptionPane.showMessageDialog(null,
                        x.toString(),
                        "USUARIO ENCONTRADO", JOptionPane.INFORMATION_MESSAGE);
                aux = true;
            }

        }
        if(!aux)
        	throw new DomainException("CPF NAO ENCONTRADO");
    }
    


}
