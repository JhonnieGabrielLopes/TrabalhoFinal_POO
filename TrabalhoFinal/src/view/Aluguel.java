package view;

import control.*;
import dao.*;
import model.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
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
        ContratoController contratoController = new ContratoController();
        
        
        aluguel.menu();
        int opcao = scanner.nextInt();
        scanner.nextLine();
        

        switch (opcao) {
            case 1:
                aluguel.realizarAluguel(aluguel, scanner, contratoController, equipamentoController, clienteController);
                break;
            case 2:
                
                break;
            case 3: 
                aluguel.cadastrarEquipamento(scanner, equipamentoController);
                break;
            case 4:
                aluguel.alterarEquipamento(scanner, equipamentoController);
                break;
            case 5:
                System.out.println(equipamentoController.listarEquipamentos());
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
        System.out.println("2 - Encerrar Contrato");
        System.out.println("3 - Contratos Ativos");
        System.out.println("4 - Contratos Encerrados");
        System.out.println("5 - Todos os Contratos");
        System.out.println("6 - Cadastrar Equipamento");
        System.out.println("7 - Alterar Equipamento");
        System.out.println("8 - Equipamentos Registrados");
        System.out.println("9 - Deletar Equipamento");
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

    public void listarEquipamentos(EquipamentoController equipamentoController) {
        ArrayList<Equipamento> equipamentos = equipamentoController.listarEquipamentos();
        if (equipamentos.isEmpty()) {
            System.out.println("Nenhum equipamento cadastrado!");
        } else {
            Iterator<Equipamento> iterator = equipamentos.iterator();
            while (iterator.hasNext()) {
                Equipamento equipamento = iterator.next();
                if (equipamento.getQtd_disponivel() != 0) {
                    System.out.println("ID: "+equipamento.getId_equip());
                    System.out.println("ID: "+equipamento.getDescricao());
                    System.out.println("-------------------------------------------------");
                }
            }
        }
    }
    public boolean verificarQuantidadeEquipamento(EquipamentoController equipamentoController, int idEquip, int qtdEquip) {
        ArrayList<Equipamento> equipamentos = equipamentoController.listarEquipamentos();
        Iterator<Equipamento> iterator = equipamentos.iterator();
        while (iterator.hasNext()) {
            Equipamento equipamento = iterator.next();
            if (equipamento.getId_equip() == idEquip) {
                if (equipamento.getQtd_disponivel() < qtdEquip) {
                    System.out.println("Quantidade de equipamentos indisponível! Informe uma quantidade menor.");
                    return false;
                }
            }
        }
        return true;
    }
    public void realizarAluguel(Aluguel aluguel, Scanner scanner, ContratoController contratoController, EquipamentoController equipamentoController, ClienteController clienteController) {
        boolean control= true;
        boolean control2= true;
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
        scanner.nextLine();
        System.out.println("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        if (clienteController.verificarCPF(cpf) == null) {
            System.out.print("Digite o nome do cliente: ");
            String nome = scanner.nextLine();
            System.out.print("Digite o telefone do cliente: ");
            String telefone = scanner.nextLine();
            System.out.print("Digite o endereço do cliente: ");
            String endereco = scanner.nextLine();
            System.out.println(clienteController.CadastrarCliente(nome, cpf, telefone, endereco));
        }

        aluguel.listarEquipamentos(equipamentoController);
        System.out.print("Digite o ID do equipamento que deseja alugar: ");
        int idEquip = scanner.nextInt();
        
        int qtdEquip= 0;
        do {
            do {
                System.out.print("Informe a Quantidade do equipamento que será alugada: ");
                try {
                    qtdEquip= scanner.nextInt();
                    control= true;
                } catch (InputMismatchException e) {
                    System.out.println("\nInforme um valor válido!\n");
                    scanner.nextLine();
                    control= false;
                }
            } while (!control);
            control2= aluguel.verificarQuantidadeEquipamento(equipamentoController, idEquip, qtdEquip);
        }while(!control2);
        scanner.nextLine();
        String dataConvert= "";
        LocalDate dataFim= null; 
        do {
            System.out.println("Informe a Data de finalização do contrato(DIA-MêS-ANO): ");
            dataConvert = scanner.nextLine().trim();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                dataFim = LocalDate.parse(dataConvert, formatter);
                control= true;
            } catch (InputMismatchException e) {
                System.out.println("\nInforme uma data válida!\n");
                scanner.next();
                control= false;
            }
        } while (!control);

        LocalDate dataAtual = LocalDate.now();
        Cliente cliente = clienteController.verificarCPF(cpf);
        int idCliente = cliente.getId();
        System.out.println(contratoController.cadastrarContrato(tipo, idCliente, idEquip, qtdEquip, dataAtual, dataFim));

    }

    public void encerrarContrato(Aluguel aluguel, Scanner scanner, ContratoController contratoController) {
        boolean control= true;
        int opc= 0;
        String forma= "";
        do {
            System.out.print("Como deseja encerrar o contrato? 1- Finalizar 2- Cancelar ");
            try {
                opc= scanner.nextInt();
                if (opc == 1 || opc == 2) {
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

        if (opc==1) {
            forma= "F";
            //listar todos os contratos ativos que possuem a data_fim igual à hoje
        } else {
            forma= "C";
            //listar todos os contratos ativos
        }

        System.out.print("Informe o ID do Contrato que deseja encerrar: ");
        int id= scanner.nextInt();

        LocalDate dataAtual = LocalDate.now();
        System.out.println(contratoController.encerrarContrato(id, forma, dataAtual));

        //consultar o contrato selecionado e capturar o seu tipo
            //se tipo contrato 1: consultar o equipamento do contrato selecionado e capturar o campo vlr_mensal
            //se tipo contrato 2: consultar o equipamento do contrato selecionado e capturar o campo vlr_diaria

        //consultar o contrato selecionado e capturar a data_fim
            //se tipo contrato 1: calcular os meses contratados (dataAtual-dataFim) e vlrTotal = vlrMensal*qtdMeses
            //se tipo contrato 2: calcular os dias contratados (dataAtual-dataFim) e vlrTotal = vlrDiaria*qtdDias
            System.out.println(totalizacaoController.calcularTotal());
            //se data_fim < dataAtual
                //se tipo contrato 1: multa = vlrTotal*0.20 / vlrTotal = vlrTotal+multa
                //se tipo contrato 2: multa = vlrTotal*0.50 / vlrTotal = vlrTotal+multa
                System.out.println(totalizacaoController.calcularMulta());
            //se data_fim > dataAtual
                //calular os diasAtrasados (dataAtual-dataFim)
                //se tipo contrato 1: multa = vlrTotal*0.50 / vlrTotal = vlrTotal+multa / juros = (vlrTotal+(diasAtrasados*0.10))
                //se tipo contrato 2: multa = vlrTotal*0.70 / vlrTotal = vlrTotal+multa / juros = (vlrTotal+(diasAtrasados*0.20))
                System.out.println(totalizacaoController.calcularJuros());


        System.out.println(totalizacaoController.realizarTotalizacao());
    }

}