package agenda;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=============================");
            System.out.println("      AGENDA DE CONTATOS     ");
            System.out.println("=============================");
            System.out.println("1. Cadastrar contato");
            System.out.println("2. Localizar contato (CPF ou e-mail)");
            System.out.println("3. Listar todos os contatos");
            System.out.println("4. Excluir contato");
            System.out.println("5. Excluir telefone de um contato");
            System.out.println("6. Excluir endereco de um contato");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida.");
                continue;
            }

            switch (opcao) {
                case 1: cadastrar(agenda); break;
                case 2: localizar(agenda); break;
                case 3: agenda.listarTodos(); break;
                case 4: excluirContato(agenda); break;
                case 5: excluirTelefone(agenda); break;
                case 6: excluirEndereco(agenda); break;
                case 0: System.out.println("Encerrando..."); break;
                default: System.out.println("Opcao invalida.");
            }
        }

        scanner.close();
    }

    static void cadastrar(Agenda agenda) {
        try {
            System.out.println("\n--- Dados do Contato ---");
            System.out.print("CPF (formato ddd.ddd.ddd-dd): ");
            String cpf = scanner.nextLine().trim();

            System.out.print("Nome: ");
            String nome = scanner.nextLine().trim();

            System.out.print("Sobrenome: ");
            String sobrenome = scanner.nextLine().trim();

            System.out.print("E-mail: ");
            String email = scanner.nextLine().trim();

            System.out.print("Data de nascimento (dd/mm/aaaa): ");
            String dataNasc = scanner.nextLine().trim();

            Contato contato = new Contato(cpf, nome, sobrenome, email, dataNasc);

            List<Telefone> tels = new ArrayList<>();
            System.out.print("\nQuantos telefones deseja cadastrar? ");
            int qtdTel = 0;
            try {
                qtdTel = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Quantidade invalida. Nenhum telefone cadastrado.");
            }

            for (int i = 0; i < qtdTel; i++) {
                System.out.print("Telefone " + (i + 1) + " (formato (dd) ddddd-dddd): ");
                String numero = scanner.nextLine().trim();
                tels.add(new Telefone(numero));
            }

            List<Endereco> ends = new ArrayList<>();
            System.out.print("\nQuantos enderecos deseja cadastrar? ");
            int qtdEnd = 0;
            try {
                qtdEnd = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Quantidade invalida. Nenhum endereco cadastrado.");
            }

            for (int i = 0; i < qtdEnd; i++) {
                System.out.println("Endereco " + (i + 1) + ":");
                System.out.print("  CEP (formato dd.ddd-ddd): ");
                String cep = scanner.nextLine().trim();
                System.out.print("  Logradouro: ");
                String logradouro = scanner.nextLine().trim();
                System.out.print("  Cidade: ");
                String cidade = scanner.nextLine().trim();
                System.out.print("  UF (ex: PR): ");
                String uf = scanner.nextLine().trim();
                ends.add(new Endereco(cep, logradouro, cidade, uf));
            }

            agenda.cadastrarContato(contato, tels, ends);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void localizar(Agenda agenda) {
        System.out.print("\nDigite o CPF ou e-mail do contato: ");
        String chave = scanner.nextLine().trim();

        Contato encontrado = agenda.localizar(chave);

        if (encontrado != null) {
            System.out.println("\nContato encontrado:");
            System.out.println(encontrado);
        } else {
            System.out.println("Nenhum contato encontrado com: " + chave);
        }
    }

    static void excluirContato(Agenda agenda) {
        System.out.print("\nDigite o CPF do contato a excluir: ");
        String cpf = scanner.nextLine().trim();
        agenda.excluirContato(cpf);
    }

    static void excluirTelefone(Agenda agenda) {
        System.out.print("\nDigite o CPF do contato: ");
        String cpf = scanner.nextLine().trim();
        System.out.print("Digite o numero do telefone a excluir (formato (dd) ddddd-dddd): ");
        String numero = scanner.nextLine().trim();
        agenda.excluirTelefone(cpf, numero);
    }

    static void excluirEndereco(Agenda agenda) {
        System.out.print("\nDigite o CPF do contato: ");
        String cpf = scanner.nextLine().trim();
        System.out.print("Digite o CEP do endereco a excluir (formato dd.ddd-ddd): ");
        String cep = scanner.nextLine().trim();
        agenda.excluirEndereco(cpf, cep);
    }
}
