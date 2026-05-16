package agenda;

import java.util.ArrayList;
import java.util.List;

public class Contato {

    private String cpf;
    private String nome;
    private String sobrenome;
    private String email;
    private String dataNascimento;

    private List<Telefone> telefones;
    private List<Endereco> enderecos;

    public Contato(String cpf, String nome, String sobrenome,
                   String email, String dataNascimento) {

        if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            throw new IllegalArgumentException("CPF inválido! Use o formato: ddd.ddd.ddd-dd");
        }

        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.telefones = new ArrayList<>();
        this.enderecos = new ArrayList<>();
    }

    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public String getSobrenome() { return sobrenome; }
    public String getEmail() { return email; }
    public String getDataNascimento() { return dataNascimento; }
    public List<Telefone> getTelefones() { return telefones; }
    public List<Endereco> getEnderecos() { return enderecos; }

    public void adicionarTelefone(Telefone t) {
        telefones.add(t);
    }

    public void adicionarEndereco(Endereco e) {
        enderecos.add(e);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Contato)) return false;
        Contato outro = (Contato) obj;
        return this.cpf.equals(outro.cpf) &&
               this.nome.equalsIgnoreCase(outro.nome) &&
               this.sobrenome.equalsIgnoreCase(outro.sobrenome);
    }

    public String toString() {
        return "Nome: " + nome + " " + sobrenome +
               "\nCPF: " + cpf +
               "\nEmail: " + email +
               "\nNascimento: " + dataNascimento +
               "\nTelefones: " + telefones +
               "\nEndereços: " + enderecos;
    }
}