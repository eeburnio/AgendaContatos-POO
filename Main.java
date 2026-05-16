package agenda;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Agenda agenda = new Agenda();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=============================");
            System.out.println("     📒 AGENDA DE CONTATOS   ");
            System.out.println("=============================");
            System.out.println("1. Cadastrar contato");
            System.out.println("2. Localizar contato");
            System.out.println("3. Listar todos os contatos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrar(agenda, scanner);
                    break;
                case 2:
                    localizar(agenda, scanner);
                    break;
                case 3:
                    agenda.listarTodos();
                    break;
                case 0:
                    System.out.println("👋 Encerrando...");
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        }

        scanner.close();
    }

    static void cadastrar(Agenda agenda, Scanner scanner) {
        try {
            System.out.println("\n--- Dados do Contato ---");
            System.out.print("CPF (formato 000.000.000-00): ");
            String cpf = scanner.nextLine();

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Sobrenome: ");
            String sobrenome = scanner.nextLine();

            System.out.print("E-mail: ");
            String email = scanner.nextLine();

            System.out.print("Data de nascimento (dd/mm/aaaa): ");
            String dataNasc = scanner.nextLine();

            Contato contato = new Contato(cpf, nome, sobrenome, email, dataNasc);

            List<Telefone> tels = new ArrayList<>();
            System.out.print("\nQuantos telefones deseja cadastrar? ");
            int qtdTel = 0;
            try {
            	qtdTel = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
            	System.out.println("Digite apenas um numero!");
            	return;
            }
            scanner.nextLine();

            for (int i = 0; i < qtdTel; i++) {
                System.out.print("Telefone " + (i+1) + " (formato (dd) ddddd-dddd): ");
                String numero = scanner.nextLine();
                tels.add(new Telefone(numero));             }

            List<Endereco> ends = new ArrayList<>();
            System.out.print("\nQuantos endereços deseja cadastrar? ");
            int qtdEnd = 0;
            try {
            	qtdEnd = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
            	System.out.println("Digite apenas um Numero!111");
            return;
            }
            scanner.nextLine();

            for (int i = 0; i < qtdEnd; i++) {
                System.out.println("Endereço " + (i+1) + ":");
                System.out.print("  CEP (formato dd.ddd-ddd): ");
                String cep = scanner.nextLine();
                System.out.print("  Logradouro: ");
                String logradouro = scanner.nextLine();
                System.out.print("  Cidade: ");
                String cidade = scanner.nextLine();
                System.out.print("  Estado (ex: PR): ");
                String estado = scanner.nextLine();
                ends.add(new Endereco(cep, logradouro, cidade, estado));
            }

            agenda.cadastrarContato(contato, tels, ends);

        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    static void localizar(Agenda agenda, Scanner scanner) {
        System.out.print("\nDigite o CPF, nome ou sobrenome: ");
        String chave = scanner.nextLine();

        Contato encontrado = agenda.localizar(chave);

        if (encontrado != null) {
            System.out.println("\n✅ Contato encontrado:");
            System.out.println(encontrado);
        } else {
            System.out.println("❌ Nenhum contato encontrado com: " + chave);
        }
    }
}