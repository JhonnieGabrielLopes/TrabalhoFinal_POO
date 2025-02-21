package view;

import control.*;
import model.*; 
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Aluguel {
    public static void main(String[] args) throws Exception {
        Aluguel aluguel = new Aluguel();
        Scanner scanner = new Scanner(System.in);
        ClienteController clienteController = new ClienteController();
        EquipamentoController equipamentoController = new EquipamentoController();
        ContratoController contratoController = new ContratoController();
        TotalizacaoController totalizacaoController = new TotalizacaoController();
        int opcao=0;
        boolean control= true;
        boolean control2= false;
        do {
            LimpaTela.limpar();
            aluguel.menu();
            do {
                try {
                    System.out.print(" > ");
                    opcao = scanner.nextInt();
                    control= true;
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("\nInforme um valor válido!\n");
                    System.out.print("ENTER...");
                    scanner.nextLine();
                    LimpaTela.limpar();
                    control= false;
                }
            } while (!control);
            switch (opcao) {
                case 1:
                    aluguel.realizarAluguel(aluguel, scanner, contratoController, equipamentoController, clienteController);
                    break;
                case 2:
                    aluguel.encerrarContrato(aluguel, scanner, contratoController, equipamentoController, totalizacaoController);
                    System.out.print("ENTER...");
                    scanner.nextLine();
                    break;
                case 3: 
                    aluguel.listarContratosAtivos(scanner, contratoController, false, null);
                    System.out.print("ENTER...");
                    scanner.nextLine();
                    break;
                case 4:
                    aluguel.listarContratosEncerrados(contratoController);
                    System.out.print("ENTER...");
                    scanner.nextLine();
                    break;
                case 5:
                    aluguel.listarContratos(contratoController);
                    System.out.print("ENTER...");
                    scanner.nextLine();
                    break;
                case 6: 
                    aluguel.cadastrarEquipamento(scanner, equipamentoController);
                    break;  
                case 7: 
                    aluguel.alterarEquipamento(aluguel, scanner, equipamentoController, contratoController);
                    System.out.print("ENTER...");
                    scanner.nextLine();
                    break;
                case 8:
                    aluguel.listarEquipamentos(equipamentoController);
                    System.out.print("ENTER...");
                    scanner.nextLine();
                    break;
                case 9:
                    aluguel.deletarEquipamento(aluguel, scanner, equipamentoController, contratoController); // erro ao deletar qualquer equipamento com registro em um contrato independente do STATUS
                    break;
                case 10:
                    aluguel.deletarCliente(contratoController, clienteController, scanner); // erro ao deletar qualquer cliente com registro em um contrato independente do STATUS
                    break;
                case 0:
                    System.out.println("\nSaindo...");
                    control2= true;
                    break;
                default:
                    System.out.println("\nOpção inválida!\n");
                    System.out.print("ENTER...");
                    scanner.nextLine();
                    LimpaTela.limpar();
                    control2= true;
                    break;
            }
        } while (!control2);
        
    }
    //--------------------------------metodos---------------------------------------------------------------------
    public void menu(){
        //criar menu apenas para contrato
        System.out.println("==================HOME==================");
        System.out.println("     [1] - Registrar Contrato");
        System.out.println("     [2] - Encerrar Contrato");
        System.out.println("     [3] - Contratos Ativos");
        System.out.println("     [4] - Contratos Encerrados");
        System.out.println("     [5] - Todos os Contratos");
        System.out.println("     [6] - Cadastrar Equipamento");
        System.out.println("     [7] - Alterar Equipamento");
        System.out.println("     [8] - Equipamentos Registrados");
        System.out.println("     [9] - Deletar Equipamento");
        System.out.println("     [10] - Deletar Cliente");
        //colocar um listar clientes
        System.out.println("     [0] - Sair");
        System.out.println("========================================");
    }

    public String cliente(Scanner scanner, ClienteController clienteController) {
        boolean control= true;
        String cpf;
        do {
            System.out.println("\nDigite o CPF do cliente(SEM ESPAÇOS, TRAÇOS OU PONTOS): ");
            System.out.print(" > ");
            cpf = scanner.nextLine();
            if (cpf.length()<11 || cpf.length()>11) {
                System.out.println("\nInforme um CPF válido!\n");
                System.out.print("ENTER...");
                scanner.nextLine();
                LimpaTela.limpar();
                control= false;
            }
        } while (!control);
        if (clienteController.verificarCPF(cpf) == null) {
            System.out.println("CPF não cadastrado! Vamos fazer um novo cadastro");
            System.out.println("\nDigite o nome do cliente: ");
            System.out.print(" > ");
            String nome = scanner.nextLine();
            System.out.println("Digite o telefone do cliente: ");
            System.out.print(" > ");
            String telefone = scanner.nextLine();
            System.out.println("Digite o endereço do cliente: ");
            System.out.print(" > ");
            String endereco = scanner.nextLine();
            System.out.println("\n"+clienteController.CadastrarCliente(nome, cpf, telefone, endereco));
        } else {
            System.out.println("\nCPF já cadastrado!\n");
        }
        System.out.print("Enter para prosseguir...");
        scanner.nextLine();
        LimpaTela.limpar();
        return cpf;
    }

    public void cadastrarEquipamento(Scanner scanner, EquipamentoController equipamentoController){
        System.out.println("Digite a descrição do equipamento: ");
        System.out.print(" > ");
        String descricao = scanner.nextLine();
        System.out.println("Digite o valor diário do equipamento: ");
        System.out.print(" > R$");
        double vlrDiaria = scanner.nextDouble();
        System.out.println("Digite o valor mensal do equipamento: ");
        System.out.print(" > R$");
        double vlrMensal = scanner.nextDouble();
        System.out.println("Digite a quantidade total do equipamento: ");
        System.out.print(" > ");
        //colocar um aivso de que foi cadastrado com sucesso
        int qtdTotal = scanner.nextInt();
        System.out.println(equipamentoController.cadastrarEquipamento(descricao, vlrDiaria, vlrMensal, qtdTotal));
    }

    public void alterarEquipamento(Aluguel aluguel, Scanner scanner, EquipamentoController equipamentoController, ContratoController contratoController){
        LimpaTela.limpar();
        aluguel.listarEquipamentos(equipamentoController);
        System.out.print("Digite o ID do equipamento que deseja alterar: ");
        int id = scanner.nextInt();
        boolean control = true;
        String coluna= "", desc= "";
        int campo;
        if (contratoController.verificarEquipamentoEmContratoAtivo(id)) {
            System.out.println("Equipamento em contrato ativo! Não é possível realizar alteração.");   
        }else{
            System.out.println("\nCampos disponíveis para alterar: ");
            System.out.println("1 - Descrição");
            System.out.println("2 - Valor Diário");
            System.out.println("3 - Valor Mensal");
            System.out.println("4 - Quantidade Total");
            do {
                System.out.print("Escolha o campo que deseja alterar: ");
                campo = scanner.nextInt();
                scanner.nextLine();
                
                switch (campo) {
                    case 1:
                        coluna= "descricao";
                        desc= "Descrição";
                        break;
                    case 2:
                        coluna= "vlr_diaria"; 
                        desc="Diária";
                        break;
                    case 3:
                        coluna= "vlr_mensal"; 
                        desc="Mensalidade";
                        break;
                    case 4:
                        coluna= "qtd_total"; 
                        desc="Quantidade";
                        break;
                    default:
                        System.out.println("Campo inválido!");
                        control = false;
                        break;
                }
            } while (!control);
            System.out.print("\nDigite o novo valor de "+desc+": ");
            String valor = scanner.nextLine();
            System.out.println(equipamentoController.alterarEquipamento(id, campo, coluna, valor));
        }
    }

    public void deletarEquipamento(Aluguel aluguel, Scanner scanner, EquipamentoController equipamentoController, ContratoController contratoController) {
        LimpaTela.limpar();
        aluguel.listarEquipamentos(equipamentoController);
        System.out.print("Digite o ID do equipamento que deseja deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (contratoController.verificarEquipamentoEmContratoAtivo(id)) {
            System.out.println("\nEquipamento em contrato ativo! Não é possível realizar a remoção.");   
            System.out.print("ENTER...");
            scanner.nextLine();
        }else {
            System.out.println(equipamentoController.deletarEquipamento(id));
            System.out.print("ENTER...");
            scanner.nextLine();
        }
    }

    public void listarEquipamentosEscolha(EquipamentoController equipamentoController) {
        ArrayList<Equipamento> equipamentos = equipamentoController.listarEquipamentos();
        if (equipamentos.isEmpty()) {
            System.out.println("\nNenhum equipamento cadastrado!");
        } else {
            Iterator<Equipamento> iterator = equipamentos.iterator();
            while (iterator.hasNext()) {
                Equipamento equipamento = iterator.next();
                if (equipamento.getQtdDisponivel() != 0) {
                    System.out.println("ID: "+equipamento.getIdEquip());
                    System.out.println("DESCRIÇÃO: "+equipamento.getDescricao());
                    System.out.println("-------------------------------------------------");
                }
            }
        }
    }

    public void listarEquipamentos(EquipamentoController equipamentoController) {
        LimpaTela.limpar();
        ArrayList<Equipamento> equipamentos = equipamentoController.listarEquipamentos();
        if (equipamentos.isEmpty()) {
            System.out.println("\nNenhum equipamento cadastrado!");
        } else {
            Iterator<Equipamento> iterator = equipamentos.iterator();
            while (iterator.hasNext()) {
                Equipamento equipamento = iterator.next();
                System.out.println(equipamento.exibirDetalhes());
                System.out.println("-------------------------------------------------");
            }
        }
    }

    public boolean verificarQuantidadeEquipamento(EquipamentoController equipamentoController, int idEquip, int qtdEquip) {
        ArrayList<Equipamento> equipamentos = equipamentoController.listarEquipamentos();
        Iterator<Equipamento> iterator = equipamentos.iterator();
        while (iterator.hasNext()) {
            Equipamento equipamento = iterator.next();
            if (equipamento.getIdEquip() == idEquip) {
                if (equipamento.getQtdDisponivel() < qtdEquip) {
                    System.out.println("Quantidade de equipamentos indisponível! Informe uma quantidade menor.");
                    return false;
                }
            }
        }
        return true;
    }

    public void realizarAluguel(Aluguel aluguel, Scanner scanner, ContratoController contratoController, EquipamentoController equipamentoController, ClienteController clienteController) {
        LimpaTela.limpar();
        boolean control= true;
        boolean control2= true;
        int tipo= 0;
        do {
            System.out.println("...........:::::: REALIZAR ALUGUEL ::::::...........\n");
            System.out.println("Qual o Tipo do Contrato? 1- Mensal 2- Diário ");
            try {
                System.out.print(" > ");
                tipo= scanner.nextInt();
                scanner.nextLine();
                if (tipo == 1 || tipo == 2) {
                    control= true;
                } else {
                    System.out.println("\nInforme 1 ou 2!\n");
                    System.out.print("ENTER...");
                    scanner.nextLine();
                    LimpaTela.limpar();
                    control= false;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInforme um valor válido!\n");
                System.out.print("ENTER...");
                scanner.nextLine();
                LimpaTela.limpar();
                control= false;
            }
        } while (!control);
        String cpf = aluguel.cliente(scanner, clienteController);
        aluguel.listarEquipamentosEscolha(equipamentoController);
        int idEquip= 0;
        int qtdEquip= 0;
        do {
            System.out.print("\nDigite o ID do equipamento que deseja alugar: ");
            try {
                idEquip = scanner.nextInt();
                control= true;
            } catch (InputMismatchException e) {
                System.out.println("\nInforme um valor válido!\n");
                System.out.print("ENTER...");
                scanner.nextLine();
                LimpaTela.limpar();
                aluguel.listarEquipamentos(equipamentoController);
                control= false;
            }
        } while (!control);
        do {
            do {
                System.out.print("\nInforme a Quantidade do equipamento que será alugada: ");
                try {
                    qtdEquip= scanner.nextInt();
                    control= true;
                } catch (InputMismatchException e) {
                    System.out.println("\nInforme um valor válido!\n");
                    System.out.print("ENTER...");
                    scanner.nextLine();
                    LimpaTela.limpar();
                    aluguel.listarEquipamentos(equipamentoController);
                    control= false;
                }
            } while (!control);
            control2= aluguel.verificarQuantidadeEquipamento(equipamentoController, idEquip, qtdEquip);
        }while(!control2);
        scanner.nextLine();
        String dataConvert= "";
        LocalDate dataFim= null; 
        do {
            System.out.print("\nInforme a Data de finalização do contrato(DIA-MêS-ANO): ");
            dataConvert = scanner.nextLine().trim();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                dataFim = LocalDate.parse(dataConvert, formatter);
                control= true;
            } catch (InputMismatchException e) {
                System.out.println("\nInforme uma data válida!\n");
                System.out.print("ENTER...");
                scanner.nextLine();
                LimpaTela.limpar();
                control= false;
            }
        } while (!control);
        LocalDate dataAtual = LocalDate.now();
        Cliente cliente = clienteController.verificarCPF(cpf);
        int idCliente = cliente.getId();
        System.out.println(contratoController.cadastrarContrato(tipo, idCliente, idEquip, qtdEquip, dataAtual, dataFim));
        System.out.print("Enter para prosseguir...");
        scanner.nextLine();
    }

    public void encerrarContrato(Aluguel aluguel, Scanner scanner, ContratoController contratoController, EquipamentoController equipamentoController, TotalizacaoController totalizacaoController) {
        LimpaTela.limpar();
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
        LocalDate dataAtual = LocalDate.now();
        if (opc==1) {
            forma= "F";
            if (aluguel.listarContratosAtivos(scanner, contratoController, true, dataAtual)) {
                scanner.nextLine();
                return;
            }
        } else {
            forma= "C";
            if (aluguel.listarContratosAtivos(scanner, contratoController, false, dataAtual)) {
                scanner.nextLine();
                return;
            }
        }
        System.out.print("Informe o ID do Contrato que deseja encerrar: ");
        int id= scanner.nextInt();
        System.out.println("\n"+contratoController.encerrarContrato(id, forma, dataAtual));
        Contrato contrato = contratoController.buscarContrato(id);
        Equipamento equipamento = equipamentoController.buscarEquipamento(contrato.getIdEquip());
        LocalDate dataFim = LocalDate.parse(contrato.getDataFim().substring(0,10));
        double valor = totalizacaoController.calcularValor(equipamento, contrato, dataAtual, dataFim);
        double multa= 0;
        double juros= 0;
        double total= 0;
        total= totalizacaoController.calcularTotal(valor, multa, juros); //-------erro para calcular total, mes de encerramento = mes de data atual não computa valores
        if (dataFim.isBefore(dataAtual) ) {                              //-------verifiicar totalização dos demais casos
            long diasAtrasados = ChronoUnit.DAYS.between(dataFim, dataAtual);
            if (contrato.getTipo() == 1) {
                multa= totalizacaoController.calcularMulta(valor, 0.20);
                juros= totalizacaoController.calcularJuros(diasAtrasados, 0.10);
            } else {
                multa= totalizacaoController.calcularMulta(valor, 0.50);
                juros= totalizacaoController.calcularJuros(diasAtrasados, 0.20);
            }
            total= totalizacaoController.calcularTotal(valor, multa, juros);
        }else if (dataFim.isAfter(dataAtual)) {
            if (contrato.getTipo() == 1) {
                multa= totalizacaoController.calcularMulta(valor, 0.50);
            } else {
                multa= totalizacaoController.calcularMulta(valor, 0.70);
            }
            total= totalizacaoController.calcularTotal(valor, multa, juros);
        }
        aluguel.exibirValores(scanner, valor, multa, juros, total);
        System.out.println(totalizacaoController.realizarTotalizacao(id, valor, multa, juros, total));
        //Totalizacao totalizacao = totalizacaoController.buscarTotalizacao(id);
        //System.out.println(totalizacao.exibirDetalhes());
        scanner.nextLine();
        System.out.print("Enter para prosseguir");
        scanner.nextLine();
    }

    public void exibirValores(Scanner scanner, double valor, double multa, double juros, double total) {
        //DESCOBRIR POR QUE ESTA FICANDO NEGATIVO
        System.out.println( "   Valor: R$" + valor + "\n" +
                            "   Multa: R$" + multa + "\n" +
                            "   Juros: R$" + juros + "\n" +
                            "----------------------" + "\n" +
                            "   Total: R$" + total);
        System.out.print("\nEnter para prosseguir");
        scanner.nextLine();
    }

    public boolean listarContratosAtivos(Scanner scanner, ContratoController contratoController, boolean dataHoje, LocalDate dataAtual) {
        LimpaTela.limpar();
        ArrayList<Contrato> contratos = contratoController.listarContratoAtivo();
        if (contratos.isEmpty()) {
            System.out.println("Nenhum contrato Ativo!");
            return true;
        }
        boolean control3= false;
        Iterator<Contrato> iterator = contratos.iterator();
        while (iterator.hasNext()) {
            Contrato contrato = iterator.next();
            if (dataHoje && !contrato.getDataFim().substring(0,10).equals(dataAtual.toString())) {
                continue;
            }
            System.out.println(contrato.exibirDetalhes());
            System.out.println("-------------------------------------------------");
            control3= true;
        }
        if (dataHoje && !control3) {
            System.out.println("Nenhum contrato com data final para o dia de hoje!");
            return true;
        }
        return false;
    }

    public void listarContratosEncerrados(ContratoController contratoController) {
        LimpaTela.limpar();
        ArrayList<Contrato> contratos = contratoController.listarContratoEncerrado();
        if (contratos.isEmpty()) {
            System.out.println("Nenhum contrato encerrado!");
        } else {
            Iterator<Contrato> iterator = contratos.iterator();
            while (iterator.hasNext()) {
                Contrato contrato = iterator.next();
                    System.out.println(contrato.exibirDetalhes());
                    System.out.println("-------------------------------------------------");
            }
        }
    }

    public void listarContratos(ContratoController contratoController) {
        LimpaTela.limpar();
        ArrayList<Contrato> contratos = contratoController.listarContrato();
        if (contratos.isEmpty()) {
            System.out.println("Nenhum contrato cadastrado!");
        } else {
            Iterator<Contrato> iterator = contratos.iterator();
            while (iterator.hasNext()) {
                Contrato contrato = iterator.next();
                    System.out.println(contrato.exibirDetalhes());
                    System.out.println("-------------------------------------------------");
            }
        }
    }

    public void deletarCliente (ContratoController contratoController, ClienteController clienteController, Scanner scanner) {
        LimpaTela.limpar();
        boolean control= true;
        String cpf;
        do {
            System.out.println("\nDigite o CPF do cliente para remoção (SEM ESPAÇOS, TRAÇOS OU PONTOS): ");
            System.out.print(" > ");
            cpf = scanner.nextLine();
            if (cpf.length()<11 || cpf.length()>11) {
                System.out.println("\nInforme um CPF válido!\n");
                System.out.print("ENTER...");
                scanner.nextLine();
                LimpaTela.limpar();
                control= false;
            }
        } while (!control);
        Cliente cliente = clienteController.verificarCPF(cpf);
        if (contratoController.verificarClienteEmContratoAtivo(cliente.getId())) {
            System.out.println("Cliente com contrato ativo! Não é possível remové-lo.");
        }else{
            System.out.println("\n"+clienteController.deletarCliente(cpf));        
        }
        System.out.print("Enter para prosseguir");
        scanner.nextLine();
    }
}