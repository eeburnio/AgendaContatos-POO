package agenda;

import java.util.ArrayList;
import java.util.List;

public class Agenda {

    private List<Contato> contatos;
    private List<Endereco> enderecos;

    public Agenda() {
        contatos = new ArrayList<>();
        enderecos = new ArrayList<>();
    }

    public void cadastrarContato(Contato novoContato, List<Telefone> tels, List<Endereco> ends) {
        if (contatos.contains(novoContato)) {
            System.out.println("Erro: contato ja cadastrado na agenda.");
            return;
        }

        for (Telefone t : tels) {
            novoContato.adicionarTelefone(t);
        }

        for (Endereco e : ends) {
            int idx = enderecos.indexOf(e);
            if (idx >= 0) {
                System.out.println("Aviso: endereco com CEP " + e.getCep() + " ja existe. Vinculando ao contato.");
                novoContato.adicionarEndereco(enderecos.get(idx));
            } else {
                enderecos.add(e);
                novoContato.adicionarEndereco(e);
            }
        }

        contatos.add(novoContato);
        System.out.println("Contato cadastrado com sucesso.");
    }

    public Contato localizar(String chave) {
        for (Contato c : contatos) {
            if (c.getCpf().equals(chave) || c.getEmail().equalsIgnoreCase(chave)) {
                return c;
            }
        }
        return null;
    }

    public void excluirContato(String cpf) {
        Contato alvo = null;
        for (Contato c : contatos) {
            if (c.getCpf().equals(cpf)) {
                alvo = c;
                break;
            }
        }

        if (alvo == null) {
            System.out.println("Contato nao encontrado com CPF: " + cpf);
            return;
        }

        alvo.getTelefones().clear();

        List<Endereco> enderecosDoContato = new ArrayList<>(alvo.getEnderecos());
        for (Endereco e : enderecosDoContato) {
            alvo.removerEndereco(e);
            boolean usadoPorOutro = false;
            for (Contato c : contatos) {
                if (!c.equals(alvo) && c.getEnderecos().contains(e)) {
                    usadoPorOutro = true;
                    break;
                }
            }
            if (!usadoPorOutro) {
                enderecos.remove(e);
            }
        }

        contatos.remove(alvo);
        System.out.println("Contato excluido com sucesso.");
    }

    public void excluirTelefone(String cpf, String numero) {
        Contato contato = null;
        for (Contato c : contatos) {
            if (c.getCpf().equals(cpf)) {
                contato = c;
                break;
            }
        }

        if (contato == null) {
            System.out.println("Contato nao encontrado com CPF: " + cpf);
            return;
        }

        Telefone alvo = null;
        for (Telefone t : contato.getTelefones()) {
            if (t.getNumero().equals(numero)) {
                alvo = t;
                break;
            }
        }

        if (alvo == null) {
            System.out.println("Telefone nao encontrado.");
            return;
        }

        contato.removerTelefone(alvo);
        System.out.println("Telefone excluido com sucesso.");
    }

    public void excluirEndereco(String cpf, String cep) {
        Contato contato = null;
        for (Contato c : contatos) {
            if (c.getCpf().equals(cpf)) {
                contato = c;
                break;
            }
        }

        if (contato == null) {
            System.out.println("Contato nao encontrado com CPF: " + cpf);
            return;
        }

        Endereco alvo = null;
        for (Endereco e : contato.getEnderecos()) {
            if (e.getCep().equals(cep)) {
                alvo = e;
                break;
            }
        }

        if (alvo == null) {
            System.out.println("Endereco nao encontrado para este contato.");
            return;
        }

        contato.removerEndereco(alvo);

        boolean usadoPorOutro = false;
        for (Contato c : contatos) {
            if (c.getEnderecos().contains(alvo)) {
                usadoPorOutro = true;
                break;
            }
        }

        if (!usadoPorOutro) {
            enderecos.remove(alvo);
            System.out.println("Endereco desvinculado e removido da agenda.");
        } else {
            System.out.println("Endereco desvinculado do contato (ainda usado por outros contatos).");
        }
    }

    public void listarTodos() {
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato cadastrado.");
            return;
        }
        System.out.println("=== CONTATOS CADASTRADOS ===");
        for (Contato c : contatos) {
            System.out.println("-------------------------------");
            System.out.println(c);
        }
        System.out.println("-------------------------------");
    }
}
