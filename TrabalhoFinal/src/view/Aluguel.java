package view;

import control.*;
import dao.*;
import model.*;

import java.nio.channels.Pipe.SourceChannel;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Aluguel {
    public static void main(String[] args) throws Exception {
        Aluguel aluguel = new Aluguel();

        Scanner scanner = new Scanner(System.in);
        ClienteController clienteController = new ClienteController();
        EquipamentoController equipamentoController = new EquipamentoController();
        
        
        aluguel.menu();
        int opcao = scanner.nextInt();
        scanner.nextLine();
        

        switch (opcao) {
            case 1:
                aluguel.realizarAluguel(scanner, null, equipamentoController);
                break;
            case 2:
                aluguel.cadastrarEquipamento(scanner, equipamentoController);
                break;
            case 3: 
                aluguel.alterarEquipamento(scanner, equipamentoController);
                break;
            case 4:
                System.out.println(equipamentoController.listarEquipamentos());
                break;
            case 5:
                
                break;
            case 6: 
                
                break;  
            case 7: 
                
                break;
            case 8:
                System.out.println("Saindo...");
                break;
        
            default:
                break;
        }

    }

    public void menu(){
        System.out.println("1 - Registrar Contrato");
        System.out.println("2 - Contratos Ativos");
        System.out.println("3 - Contratos Encerrados");
        System.out.println("4 - Todos os Contratos");
        System.out.println("5 - Cadastrar Equipamento");
        System.out.println("6 - Alterar Equipamento");
        System.out.println("7 - Equipamentos Registrados");
        System.out.println("8 - Deletar Equipamento");
        System.out.println("0 - Sair");
    }

    public void cliente(Scanner scanner, ClienteController clienteController) {
        System.out.println("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        if (clienteController.verificarCPF(cpf) == null) {
            System.out.println("Digite o nome do cliente: ");
            String nome = scanner.nextLine();
            System.out.println("Digite o telefone do cliente: ");
            String telefone = scanner.nextLine();
            System.out.println("Digite o endereço do cliente: ");
            String endereco = scanner.nextLine();
            System.out.println(clienteController.CadastrarCliente(nome, cpf, telefone, endereco));
        }
    }

    public void cadastrarEquipamento(Scanner scanner, EquipamentoController equipamentoController){
        System.out.println("Digite a descrição do equipamento: ");
        String descricao = scanner.nextLine();
        System.out.println("Digite o valor diário do equipamento: ");
        double vlr_diaria = scanner.nextDouble();
        System.out.println("Digite o valor mensal do equipamento: ");
        double vlr_mensal = scanner.nextDouble();
        System.out.println("Digite a quantidade total do equipamento: ");
        int qtd_total = scanner.nextInt();
        System.out.println(equipamentoController.cadastrarEquipamento(descricao, vlr_diaria, vlr_mensal, qtd_total));
    }

    public void alterarEquipamento(Scanner scanner, EquipamentoController equipamentoController){
        equipamentoController.listarEquipamentos();
        System.out.print("Digite o ID do equipamento que deseja alterar: ");
        int id = scanner.nextInt();

        System.out.println("\nCampos disponíveis para alterar: ");
        System.out.println("1 - Descrição");
        System.out.println("2 - Valor Diário");
        System.out.println("3 - Valor Mensal");
        System.out.println("4 - Quantidade Total");
        System.out.print("Escolha o campo que deseja alterar: ");
        int campo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("\nDigite o novo valor para: ");
        String valor = scanner.nextLine();
        System.out.println(equipamentoController.alterarEquipamento(id, campo, valor));
    }

    public void deletarEquipamento(Scanner scanner, EquipamentoController equipamentoController) {
        equipamentoController.listarEquipamentos();
        System.out.print("Digite o ID do equipamento que deseja deletar: ");
        int id = scanner.nextInt();
        System.out.println(equipamentoController.deletarEquipamento(id));
    }

    public void realizarAluguel(Scanner scanner, ContratoController contratoController, EquipamentoController equipamentoController){
        boolean control= true;
        
        System.out.println("...........:::::: REALIZAR ALUGUEL ::::::...........\n");

        int tipo= 0;
        do {
            System.out.print("Qual o Tipo do Contrato? 1- Mensal 2- Diário ");
            try {
                tipo= scanner.nextInt();
                if (tipo == 1 || tipo == 2) {
                    control= true;
                } else {
                    System.out.println("\nInforme 1 ou 2!\n");
                    control= false;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInforme um valor válido!\n");
                scanner.next();
                control= false;
            }
        } while (!control);

        //cliente(scanner, null);

        equipamentoController.listarEquipamentos();
        System.out.print("Digite o ID do equipamento que deseja alugar: ");
        int idEquip = scanner.nextInt();
        
        int qtdEquip= 0;
        do {
            System.out.print("Informe a Quantidade do equipamento que será alugada: ");
            try {
                qtdEquip= scanner.nextInt();
                control= true;
            } catch (InputMismatchException e) {
                System.out.println("\nInforme um valor válido!\n");
                scanner.next();
                control= false;
            }
        } while (!control);

        String dataConvert= "";
        do {
            System.out.println("Informe a Data de finalização do contrato(DIA-MêS-ANO): ");
            scanner.nextLine();
            dataConvert = scanner.nextLine().trim(); 
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate dataFim = LocalDate.parse(dataConvert, formatter);
                control= true;
            } catch (InputMismatchException e) {
                System.out.println("\nInforme uma data válida!\n");
                scanner.next();
                control= false;
            }
        } while (!control);

        LocalDate dataAtual = LocalDate.now();

        //System.out.println(contratoController.cadastrarContrato(tipo, idEquip, qtdEquip, dataAtual, dataConvert, dataConvert));

    }

}