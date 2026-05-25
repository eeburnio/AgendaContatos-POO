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

    public Contato(String cpf, String nome, String sobrenome, String email, String dataNascimento) {
        if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            throw new IllegalArgumentException("CPF invalido! Use o formato: ddd.ddd.ddd-dd");
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

    public void removerTelefone(Telefone t) {
        telefones.remove(t);
    }

    public void adicionarEndereco(Endereco e) {
        enderecos.add(e);
    }

    public void removerEndereco(Endereco e) {
        enderecos.remove(e);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Contato)) return false;
        Contato outro = (Contato) obj;
        return this.cpf.equals(outro.cpf);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nome).append(" ").append(sobrenome);
        sb.append("\nCPF: ").append(cpf);
        sb.append("\nEmail: ").append(email);
        sb.append("\nNascimento: ").append(dataNascimento);
        sb.append("\nTelefones: ");
        if (telefones.isEmpty()) {
            sb.append("nenhum");
        } else {
            for (Telefone t : telefones) {
                sb.append("\n  - ").append(t);
            }
        }
        sb.append("\nEnderecos: ");
        if (enderecos.isEmpty()) {
            sb.append("nenhum");
        } else {
            for (Endereco e : enderecos) {
                sb.append("\n  - ").append(e);
            }
        }
        return sb.toString();
    }
}
