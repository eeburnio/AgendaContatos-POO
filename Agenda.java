package agenda;

import java.util.ArrayList;
import java.util.List;

public class Agenda {

    private List<Contato> contatos;

    private List<Telefone> telefones;
    private List<Endereco> enderecos;

    public Agenda() {
        contatos  = new ArrayList<>();
        telefones = new ArrayList<>();
        enderecos = new ArrayList<>();
    }

    public void cadastrarContato(Contato novoContato,
                                  List<Telefone> tels,
                                  List<Endereco> ends) {

        if (contatos.contains(novoContato)) {
            System.out.println("❌ Contato já cadastrado na agenda!");
            return;
        }

        for (Telefone t : tels) {
            int idx = telefones.indexOf(t); // Procura se já existe
            if (idx >= 0) {

            	System.out.println("⚠️ Telefone " + t + " já existe. Vinculando ao contato.");
                novoContato.adicionarTelefone(telefones.get(idx));
            } else {

            	telefones.add(t);
                novoContato.adicionarTelefone(t);
            }
        }

        for (Endereco e : ends) {
            int idx = enderecos.indexOf(e); // Procura se já existe
            if (idx >= 0) {

            	System.out.println("⚠️ Endereço com CEP " + e.getCep() + " já existe. Vinculando ao contato.");
                novoContato.adicionarEndereco(enderecos.get(idx));
            } else {
                enderecos.add(e);
                novoContato.adicionarEndereco(e);
            }
        }

        contatos.add(novoContato);
        System.out.println("✅ Contato cadastrado com sucesso!");
    }

    public Contato localizar(String chave) {
        for (Contato c : contatos) {
            if (c.getCpf().equals(chave) ||
                c.getNome().equalsIgnoreCase(chave) ||
                c.getSobrenome().equalsIgnoreCase(chave)) {
                return c;
            }
        }
        return null;
    }
    public void listarTodos() {
        if (contatos.isEmpty()) {
            System.out.println("📭 Nenhum contato cadastrado.");
            return;
        }
        System.out.println("📋 === CONTATOS CADASTRADOS ===");
        for (Contato c : contatos) {
            System.out.println("-------------------------------");
            System.out.println(c);
        }
        System.out.println("-------------------------------");
    }
}