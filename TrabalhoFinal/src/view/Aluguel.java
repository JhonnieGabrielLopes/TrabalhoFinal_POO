package view;

import control.*;
import dao.*;
import model.*;
import java.util.Scanner;


public class Aluguel {
    public static void main(String[] args) throws Exception {
        Aluguel aluguel = new Aluguel();

        Scanner scanner = new Scanner(System.in);
        ClienteController clienteController = new ClienteController();
        EquipamentoController equipamentoController = new EquipamentoController();
        
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
        }else{
            System.out.println(clienteController.verificarCPF(cpf)); 
        }
        
        aluguel.menu();
        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1:
                
                break;
            case 2:
                aluguel.cadastrarEquipamento(scanner, equipamentoController);
                break;
            case 3: 
                aluguel.alterarEquipamento(scanner, equipamentoController);
                break;
            case 4:
                equipamentoController.listarEquipamentos();
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
        System.out.println("2 - Cadastrar Equipamento");
        System.out.println("3 - Alterar Equipamento");
        System.out.println("4 - Equipamentos Registrados");
        System.out.println("5 - Contratos Ativos");
        System.out.println("6 - Contratos Encerrados");
        System.out.println("7 - Todos os Contratos");
        System.out.println("8 - Sair");
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
}
