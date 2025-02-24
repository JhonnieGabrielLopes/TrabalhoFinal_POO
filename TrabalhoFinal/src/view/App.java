package view;

import control.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        MetodosView metodo = new MetodosView();
        Scanner scanner = new Scanner(System.in);
        ClienteController clienteController = new ClienteController();
        EquipamentoController equipamentoController = new EquipamentoController();
        ContratoController contratoController = new ContratoController();
        TotalizacaoController totalizacaoController = new TotalizacaoController();

        int opcao=0;
        boolean control= true, control2= false, control3= false;

        do {
            metodo.limparTela();

            do {
                metodo.menuPrincipal();
                try {
                    System.out.print(" > ");
                    opcao = scanner.nextInt();
                    scanner.nextLine();
                    control= true;
                } catch (InputMismatchException e) {
                    scanner.nextLine();
                    System.out.println("\nInforme um valor válido!\n");
                    System.out.print("ENTER...");
                    scanner.nextLine();
                    metodo.limparTela();
                    control= false;
                }
            } while (!control);

            switch (opcao) {
                case 1:  //Contratos
                    control3= false;
                    do {
                        metodo.limparTela();
                        
                        do {
                            metodo.menuAluguel();
                            try {
                                System.out.print("   > ");
                                opcao = scanner.nextInt();
                                scanner.nextLine();
                                control= true;
                            } catch (InputMismatchException e) {
                                scanner.nextLine();
                                System.out.println("\nInforme um valor válido!\n");
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                metodo.limparTela();
                                control= false;
                            }
                        } while (!control);

                        switch (opcao) {
                            case 1: //Gerar novo Contrato
                                metodo.realizarAluguel(scanner, contratoController, equipamentoController, clienteController, totalizacaoController);
                                break;

                            case 2: //Encerrar Contrato
                                metodo.encerrarContrato(scanner, contratoController, equipamentoController, totalizacaoController);
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                break;

                            case 0:
                                System.out.println("\nSaindo...");
                                control3=true;
                                break;
                                
                            default:
                                System.out.println("\nOpção inválida!\n");
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                metodo.limparTela();
                                control3 = false;
                                break;
                        }
                    } while (!control3);

                break;

                case 2: //Equipamentos
                    control3= false;
                    do {
                        metodo.limparTela();
                        
                        do {
                            metodo.menuEquipamentos();
                            try {
                                System.out.print("   > ");
                                opcao = scanner.nextInt();
                                scanner.nextLine();
                                control= true;
                            
                            } catch (InputMismatchException e) {
                                scanner.nextLine();
                                System.out.println("\nInforme um valor válido!\n");
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                metodo.limparTela();
                                control= false;
                            }
                        } while (!control);
                    
                        switch (opcao) {
                            case 1: //Cadastrar novo Equipamento
                                metodo.cadastrarEquipamento(scanner, equipamentoController);
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                break;
                        
                            case 2: //Alterar Equipamento
                                metodo.alterarEquipamento(scanner, equipamentoController, contratoController);
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                break;
                        
                            case 3: //Deletar Equipamento
                                metodo.deletarEquipamento(scanner, equipamentoController, contratoController);
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                break;
                        
                            case 0:
                                System.out.println("\nSaindo...");
                                control3=true;
                                break;
                        
                            default:
                                System.out.println("\nOpção inválida!\n");
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                metodo.limparTela();
                                control3= false;
                                break;
                        }
                    } while (!control3);

                break;

                case 3: //Clientes
                    control3= false;
                    do {
                        metodo.limparTela();
                        
                        do {
                            metodo.menuClientes();
                            try {
                                System.out.print("   > ");
                                opcao = scanner.nextInt();
                                scanner.nextLine();
                                control= true;
                            
                            } catch (InputMismatchException e) {
                                scanner.nextLine();
                                System.out.println("\nInforme um valor válido!\n");
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                metodo.limparTela();
                                control= false;
                            }
                        } while (!control);
                    
                        switch (opcao) {
                            case 1: //Alterar Cliente
                                metodo.alterarCliente(scanner, clienteController, contratoController);
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                break;
                        
                            case 2: //Deletar Cliente
                                metodo.deletarCliente(contratoController, clienteController, scanner);
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                break;
                        
                            case 0:
                                System.out.println("\nSaindo...");
                                control3=true;
                                break;
                        
                            default:
                                System.out.println("\nOpção inválida!\n");
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                metodo.limparTela();
                                control3= false;
                                break;
                        }
                    } while (!control3);

                break;

                case 4: //Relatorios
                    control3= false;
                    do {
                        metodo.limparTela();
                        
                        do {
                            metodo.menuRelatorios();
                            try {
                                System.out.print("   > ");
                                opcao = scanner.nextInt();
                                scanner.nextLine();
                                control= true;
                            
                            } catch (InputMismatchException e) {
                                scanner.nextLine();
                                System.out.println("\nInforme um valor válido!\n");
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                metodo.limparTela();
                                control= false;
                            }
                        } while (!control);
                    
                        switch (opcao) {
                            case 1: //TODOS os Contratos
                                metodo.listarContratos(contratoController);
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                break;
                        
                            case 2: //Contratos ATIVOS
                                metodo.listarContratosAtivos(0, scanner, contratoController, null);
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                break;
                        
                            case 3: //Contratos ENCERRADOS
                                metodo.listarContratosEncerrados(contratoController);
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                break;

                            case 4: //Equipamentos GERAL
                                metodo.listarEquipamentos(equipamentoController);
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                break;

                            case 5: //Clientes GERAL
                                metodo.listarClientes(clienteController);
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                break;

                            case 0:
                                System.out.println("\nSaindo...");
                                control3=true;
                                break;
                        
                            default:
                                System.out.println("\nOpção inválida!\n");
                                System.out.print("ENTER...");
                                scanner.nextLine();
                                metodo.limparTela();
                                control3= false;
                                break;
                        }
                    } while (!control3);

                break;

                case 0:
                    System.out.println("\nSaindo...");
                    control2= true;
                break;

                default:
                    System.out.println("\nOpção inválida!\n");
                    System.out.print("ENTER...");
                    scanner.nextLine();
                    metodo.limparTela();
                    control2= false;
                break;
            } 
        } while (!control2);
    }
}