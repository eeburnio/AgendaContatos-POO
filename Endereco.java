package agenda;

public class Endereco {

    private String cep;
    private String logradouro;
    private String cidade;
    private String uf;

    public Endereco(String cep, String logradouro, String cidade, String uf) {
        if (!cep.matches("\\d{2}\\.\\d{3}-\\d{3}")) {
            throw new IllegalArgumentException("CEP invalido! Use o formato: dd.ddd-ddd");
        }
        this.cep = cep;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public String getCep() { return cep; }
    public String getLogradouro() { return logradouro; }
    public String getCidade() { return cidade; }
    public String getUf() { return uf; }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Endereco)) return false;
        Endereco outro = (Endereco) obj;
        return this.cep.equals(outro.cep);
    }

    public String toString() {
        return logradouro + ", " + cidade + " - " + uf + " (CEP: " + cep + ")";
    }
}
