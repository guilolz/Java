package entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Funcionario extends Pessoas {

    private int cod;

    public Funcionario(String Nome, String Cpf, LocalDate DataNasc, String Telefone, int cod) {
        super(Nome, Cpf, DataNasc, Telefone);
        this.cod = cod;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("cod: [ ").append(cod);
        sb.append("] \n ");
        return super.toString() + sb.toString();

    }



}
