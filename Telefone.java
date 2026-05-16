package agenda;

public class Telefone {

    private String numero;

    public Telefone(String numero) {

        if (!numero.matches("\\(\\d{2}\\) \\d{5}-\\d{4}")) {
            throw new IllegalArgumentException("Telefone inválido! Use o formato: (dd) ddddd-dddd");
        }
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Telefone)) return false;
        Telefone outro = (Telefone) obj;
        return this.numero.equals(outro.numero);
    }

    public String toString() {
        return numero;
    }
}
