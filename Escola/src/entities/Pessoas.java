package entities;

import static java.lang.Character.digit;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.List;
import model.exceptions.DomainException;

public class Pessoas {

    private String Nome;
    private String Cpf;
    private LocalDate DataNasc;
    private String Telefone;

    public Pessoas(String Nome, String Cpf, LocalDate DataNasc, String Telefone) {

        this.Nome = Nome;
        this.Cpf = Cpf;
        this.DataNasc = DataNasc;
        this.Telefone = Telefone;
    }
    public static void validaTel(String telefone) {
        if (telefone.length()!=8) {
            throw new DomainException("Telefone invalido");
        }

    }

    public static void dataIsAfter(LocalDate DataNasc) {

        LocalDate dataAtual = LocalDate.now();

        if (dataAtual.isBefore(DataNasc)) {
            throw new DomainException("Data invalida");
        }

    }

    public static int calcularIdade(LocalDate dataNasc) {
        LocalDate dataAtual = LocalDate.now();

        Period periodo = Period.between(dataNasc, dataAtual);

        int idade = periodo.getYears();

        return idade;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Nome: [ ").append(Nome);
        sb.append(" ]\n");
        sb.append("Cpf: [ ").append(Cpf);
        sb.append(" ]\n");
        sb.append("DataNasc: [ ").append(DataNasc);
        sb.append(" ]\n");
        sb.append("Idade: [").append(calcularIdade(DataNasc));
        sb.append(" ]\n");
        sb.append("Telefone: [ ").append(Telefone);
        sb.append(" ]\n");

        return sb.toString();
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }

    public LocalDate getDataNasc() {
        return DataNasc;
    }

    public void setDataNasc(LocalDate DataNasc) {
        this.DataNasc = DataNasc;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public static void validaCpf(String cpf) {
        cpf = cpf.replaceAll("\\D+", ""); // Remove todos os caracteres não numéricos do CPF

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
        	throw new DomainException("CPF INVALIDO"); // Verifica se o CPF possui 11 dígitos e não é composto por dígitos repetidos
        }

        int[] numeros = new int[11];
        for (int i = 0; i < 11; i++) {
            numeros[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int digitoVerificador1 = calcularDigitoVerificador(numeros, 9);
        int digitoVerificador2 = calcularDigitoVerificador(numeros, 10);

        if( !(numeros[9] == digitoVerificador1) && !(numeros[10] == digitoVerificador2)) {
        	throw new DomainException("CPF INVALIDO");
        }
    }

    private static int calcularDigitoVerificador(int[] numeros, int pesoMaximo) {
        int soma = 0;
        int peso = pesoMaximo + 1;
        for (int i = 0; i < pesoMaximo; i++) {
            soma += numeros[i] * peso;
            peso--;
        }

        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }
    
    public static void verificarCpf(List<Alunos> lista1, List<Tecnico> lista2, List<Professor> lista3, String cpf) {
        for (Alunos objeto : lista1) {
            if (objeto.getCpf().equals(cpf)) {
            	throw new DomainException(""); // CPF encontrado em algum objeto da lista
            }
        }
        for (Tecnico objeto : lista2) {
            if (objeto.getCpf().equals(cpf)) {
            	throw new DomainException(""); // CPF encontrado em algum objeto da lista
            }
        }
        for (Professor objeto : lista3) {
            if (objeto.getCpf().equals(cpf)) {
            	throw new DomainException(""); // CPF encontrado em algum objeto da lista
            }
        }
         // CPF não encontrado na lista
    }

}
