package agenda;

public class Endereco {

    private String cep;
    private String logradouro;
    private String cidade;
    private String estado;

    public Endereco(String cep, String logradouro, String cidade, String estado) {
        if (!cep.matches("\\d{2}\\.\\d{3}-\\d{3}")) {
            throw new IllegalArgumentException("CEP inválido! Use o formato: dd.ddd-ddd");
        }
        this.cep = cep;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCep() { return cep; }
    public String getLogradouro() { return logradouro; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Endereco)) return false;
        Endereco outro = (Endereco) obj;
        return this.cep.equals(outro.cep);
    }

    public String toString() {
        return logradouro + ", " + cidade + " - " + estado + " (CEP: " + cep + ")";
    }
}