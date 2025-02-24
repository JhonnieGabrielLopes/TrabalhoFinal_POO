package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import control.ClienteController;
import control.ContratoController;
import control.EquipamentoController;
import control.TotalizacaoController;
import model.Cliente;
import model.Contrato;
import model.Equipamento;

public class MetodosView {

    //------------VIEW
    public void menuPrincipal(){
        System.out.println("=========== ALUGUEL DE EQUIPAMENTOS ===========");
        System.out.println("==================== HOME =====================");
        System.out.println("    [1] - Controle de Aluguel");
        System.out.println("    [2] - Controle de Equipamentos");
        System.out.println("    [3] - Controle de Clientes");
        System.out.println("    [4] - Relatorios\n");
        System.out.println("    [0] - SAIR");
        System.out.println("===============================================");
    }

    public void menuAluguel() {
        System.out.println("=========== ALUGUEL DE EQUIPAMENTOS ===========");
        System.out.println("================= CONTRATOS ===================");
        System.out.println("    [1] - Gerar novo Contrato");
        System.out.println("    [2] - Encerrar Contrato\n");
        System.out.println("    [0] - VOLTAR");
        System.out.println("===============================================");
    }

    public void menuEquipamentos() {
        System.out.println("=========== ALUGUEL DE EQUIPAMENTOS ===========");
        System.out.println("================ EQUIPAMENTOS =================");
        System.out.println("    [1] - Cadastrar novo Equipamento");
        System.out.println("    [2] - Alterar Equipamento");
        System.out.println("    [3] - Deletar Equipamento\n");
        System.out.println("    [0] - VOLTAR");
        System.out.println("===============================================");
    }

    public void menuClientes() {
        System.out.println("=========== ALUGUEL DE EQUIPAMENTOS ===========");
        System.out.println("================== CLIENTES ===================");
        System.out.println("    [1] - Alterar Cliente");
        System.out.println("    [2] - Deletar Cliente\n");
        System.out.println("    [!] - Clientes são cadastrados no Contrato!");
        System.out.println("    [0] - VOLTAR");
        System.out.println("===============================================");
    }

    public void menuRelatorios() {
        System.out.println("=========== ALUGUEL DE EQUIPAMENTOS ===========");
        System.out.println("================= RELATORIOS ==================");
        System.out.println("    [1] - TODOS os Contratos");
        System.out.println("    [2] - Contratos ATIVOS");
        System.out.println("    [3] - Contratos ENCERRADOS");
        System.out.println("    [4] - Equipamentos GERAL");
        System.out.println("    [5] - Clientes GERAL\n");
        System.out.println("    [0] - VOLTAR");
        System.out.println("===============================================");
    }

    public void limparTela() {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                try {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
    }

    //------------CONTRATO
    public void realizarAluguel(Scanner scanner, ContratoController contratoController, EquipamentoController equipamentoController, ClienteController clienteController, TotalizacaoController totalizacaoController) {
        this.limparTela();
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
                    this.limparTela();
                    control= false;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInforme um valor válido!\n");
                System.out.print("ENTER...");
                scanner.nextLine();
                this.limparTela();
                control= false;
            }
        } while (!control);

        String cpf = this.cliente(scanner, clienteController);
        this.listarEquipamentosEscolha(equipamentoController, tipo);
        int idEquip= 0;
        int qtdEquip= 0;

        do {
            System.out.print("Digite o ID do equipamento que deseja alugar: ");
            try {
                idEquip = scanner.nextInt();
                scanner.nextLine();
                control= true;
            } catch (InputMismatchException e) {
                System.out.println("\nInforme um valor válido!\n");
                scanner.nextLine();
                control= false;
            }
        } while (!control);

        do {
            do {
                System.out.print("Informe a Quantidade do equipamento que será alugado: ");
                try {
                    qtdEquip= scanner.nextInt();
                    scanner.nextLine();
                    control= true;
                } catch (InputMismatchException e) {
                    System.out.println("\nInforme um valor válido!\n");
                    scanner.nextLine();
                    control= false;
                }
                
            } while (!control);

            control2= this.verificarQuantidadeEquipamento(equipamentoController, idEquip, qtdEquip);
        }while(!control2);

        int tempo= 0;
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataFim= null;

        do {
            if (tipo == 1) {
                System.out.println("\nTempo de permanência (Quantidade de Meses) ");
            } else {
                System.out.println("\nTempo de permanência (Quantidade de Dias) ");
            }

            do {
                System.out.print(" > ");
                try {
                    tempo = scanner.nextInt();
                    scanner.nextLine();
                    control = true;
                } catch (InputMismatchException e) {
                    System.out.println("\nInforme um valor válido!");
                    scanner.next();
                    control= false;
                }
            } while (!control);

            try {
                if (tipo == 1) {
                    if (tempo <= 0) {
                        System.out.println("Permanência mínima de 1 mês!");
                        control= false;
                    } else {
                        int temp= tempo*30;
                        dataFim = dataAtual.plusDays(temp);
                        control= true;
                    }

                } else {
                    if (tempo <= 0) {
                        System.out.println("Permanencia mínima de 1 dia!");
                        control= false;
                    } else {
                        dataFim = dataAtual.plusDays(tempo);
                        control= true;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInforme uma quantidade válida!\n");
                System.out.print("ENTER...");
                scanner.nextLine();
                this.limparTela();
                control= false;
            }

        } while (!control);
        
        Cliente cliente = clienteController.verificarCPF(cpf);
        int idCliente = cliente.getId();
        System.out.println(contratoController.cadastrarContrato(tipo, idCliente, idEquip, qtdEquip, dataAtual, dataFim));
        Contrato contrato = contratoController.ultimoContrato();
        double total= obterValor(0, tipo, idEquip, contrato, contratoController, equipamentoController, totalizacaoController);
        DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataFim.format(formatacao);
        System.out.println("----------------------\n" + 
                           "Total: R$" + total + " a serem pagos em " + dataFormatada + "\n" +
                           "----------------------");
        System.out.print("\nENTER...");
        scanner.nextLine();
    }

    public Double obterValor(int forma, int tipo, int idEquip, Contrato contrato, ContratoController contratoController, EquipamentoController equipamentoController, TotalizacaoController totalizacaoController) {
        Equipamento equipamento = equipamentoController.buscarEquipamento(idEquip);
        LocalDate dataAtual = LocalDate.now();
        return this.calcularValor(forma, tipo, equipamento, contrato, dataAtual);
    }

    public void encerrarContrato(Scanner scanner, ContratoController contratoController, EquipamentoController equipamentoController, TotalizacaoController totalizacaoController) {
        this.limparTela();

        LocalDate dataAtual = LocalDate.now();
        boolean control= true, control1= true;
        int forma= 0;
        String formaS= "";

        do {
            System.out.println("Como deseja encerrar o contrato?\n :: [1] - Finalizar (Vence hoje ou Já venceu)\n :: [2] - Cancelar (À vencer)\n :: [3] - VOLTAR");
            System.out.print(" > ");
            try {
                forma= scanner.nextInt();

                if (forma == 1 || forma == 2) {
                    control= true;
                } else if (forma == 3) {
                    control= true;
                    control1= false;
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
        
        if (control1) {
            if (forma == 1) {
                formaS= "F";
    
                if (this.listarContratosAtivos(forma, scanner, contratoController, dataAtual)) {
                    scanner.nextLine();
                    return;
                }
            } else if (forma == 2) {
                formaS= "C";
                
                if (this.listarContratosAtivos(forma, scanner, contratoController, dataAtual)) {
                    scanner.nextLine();
                    return;
                }
            }
    
            System.out.print("Informe o ID do Contrato que deseja encerrar: ");
            int id= scanner.nextInt();
    
            System.out.println("\n"+contratoController.encerrarContrato(id, formaS, dataAtual));
            Contrato contrato = contratoController.buscarContrato(id);
            LocalDate dataFim = LocalDate.parse(contrato.getDataFim().substring(0,10));
            
            double valor = obterValor(forma, 0, contrato.getIdEquip(), contrato, contratoController, equipamentoController, totalizacaoController);
            double multa = 0, juros = 0, total = 0;
    
            if (dataFim.isBefore(dataAtual) ) { // Calcula Multa e Juros para contratos vencidos                         
                multa= this.calcularMulta(forma, contrato.getTipo(), valor);
                juros= this.calcularJuros(contrato.getTipo(), valor, dataAtual, dataFim);
            } else if (dataFim.isAfter(dataAtual)) { // Calcula Multa para cancelamento do contrato
                multa= this.calcularMulta(forma, contrato.getTipo(), valor);
            }

            total= this.calcularTotal(valor, multa, juros);
            System.out.println( "   Valor: R$" + valor + "\n" +
                                "   Multa: R$" + multa + "\n" +
                                "   Juros: R$" + juros + "\n" +
                                "----------------------" + "\n" +
                                "   Total: R$" + total);
            scanner.nextLine();
            System.out.println(totalizacaoController.realizarTotalizacao(id, valor, multa, juros, total)); 
        }
        
    }

    public void listarContratos(ContratoController contratoController) {
        this.limparTela();
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

    public boolean listarContratosAtivos(int forma, Scanner scanner, ContratoController contratoController, LocalDate dataAtual) {
        this.limparTela();

        ArrayList<Contrato> contratos = contratoController.listarContratoAtivo();
        Iterator<Contrato> iterator = contratos.iterator();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        if (contratos.isEmpty()) {
            System.out.println("Não existem contratos Ativos!");
            return true;
        }
        
        int qtdFin = 0, qtdCan = 0;

        while (iterator.hasNext()) {
            Contrato contrato = iterator.next();

            if (dataAtual != null) {
                LocalDate dataContrato = LocalDate.parse(contrato.getDataFim(), formato);
                boolean finaliza = dataAtual.isAfter(dataContrato) || dataAtual.equals(dataContrato);
                boolean cancela = dataAtual.isBefore(dataContrato) && !dataAtual.equals(dataContrato);
            
                if (forma == 1 && finaliza) {
                    System.out.println(contrato.exibirDetalhes()+"\n--------------------------------------------------------------------");
                    qtdFin++;
                } else if (forma == 2 && cancela) {
                    System.out.println(contrato.exibirDetalhes()+"\n--------------------------------------------------------------------");
                    qtdCan++;
                }
                
            } else {
                System.out.println(contrato.exibirDetalhes()+"\n--------------------------------------------------------------------");
            }

        }

        if (dataAtual != null && qtdFin == 0 && qtdCan == 0) {
            System.out.println("Não encontrado contratos para Finalização/Cancelamento");
            return true;
        }

        return false;
    }

    public void listarContratosEncerrados(ContratoController contratoController) {
        this.limparTela();
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

    //------------TOTALIZAÇÃO
    public Double calcularValor (int forma, int tipo, Equipamento equipamento, Contrato contrato, LocalDate dataAtual) {
        double total = 0;
        LocalDate dataFim = LocalDate.parse(contrato.getDataFim().substring(0,10));

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataContratoFim = LocalDate.parse(contrato.getDataFim(), formato);
        LocalDate dataContratoInicio = LocalDate.parse(contrato.getDataInicio(), formato);

        if (contrato.getTipo() == 1) { //Calcular o valor do contrato mensal
            long mesesContratados = ChronoUnit.MONTHS.between(dataContratoInicio, dataContratoFim);
            total= (equipamento.getVlrMensal()*mesesContratados)*contrato.getQtdEquip();
        } else if (contrato.getTipo() == 2) { //Calcular o valor do contrato diario
            long diasContratados = ChronoUnit.DAYS.between(dataContratoInicio, dataContratoFim);
            total= (equipamento.getVlrDiaria()*diasContratados)*contrato.getQtdEquip();
        }

        if (forma == 1) { //Calcular o valor na finalização do contrato
            if (dataAtual.isAfter(dataContratoFim)) {
                calcularMulta(forma, contrato.getTipo(), total);
                calcularJuros(contrato.getTipo(), total, dataAtual, dataFim);
            }
        } else if (forma == 2) { //Calcular o valor no cancelamento do contrato
            calcularMulta(forma, contrato.getTipo(), total);
        }

        return total;
    }

    public Double calcularMulta (int forma, int tipo, double valor) {
        double total = 0;
        if (forma == 1) {
            if (tipo == 1) { // 20% de multa para finalização mensal e 50% para finalização diario
                total = valor*0.20;
            } else {
                total = valor*0.50;
            }
        } else {
            if (tipo == 1) { // 50% de multa para cancelamento mensal e 70% para cancelamento diario
                total = valor*0.50;
            } else {
                total = valor*0.70;
            }
        }
        return total;
    }
                
    public Double calcularJuros (int tipo, double valor, LocalDate dataAtual, LocalDate dataFim) {
        long diasAtrasados = ChronoUnit.DAYS.between(dataFim, dataAtual);
        double total = 0;
        if (tipo==1) {
            total = ((diasAtrasados*0.01)*valor);
        } else {
            total = ((diasAtrasados*0.02)*valor);
        }
        return total;
    }

    public Double calcularTotal (double total, double multa, double juros) {
        double totalFinal = total + multa + juros;
        return totalFinal;
    }

    //------------EQUIPAMENTO
    public void cadastrarEquipamento(Scanner scanner, EquipamentoController equipamentoController){
        String descricao= " ";
        double vlrDiaria= 0, vlrMensal= 0;
        int qtdTotal= 0;
        boolean control = false;
        
        do {
            System.out.println("\nInforme a Descrição do Equipamento: ");
            System.out.print(" > ");
            descricao = scanner.nextLine(); 
            if (descricao.isEmpty()) {
                System.out.println("Preenchimento da Descrição é Obrigatorio!\n");
                control =false;
            } else {
                control= true;
            }
        } while (!control);

        do {
            System.out.println("Informe o Valor da Diária do Equipamento: ");
            System.out.print(" > R$");
            vlrDiaria = scanner.nextDouble();
            scanner.nextLine(); 
            if (vlrDiaria == 0) {
                System.out.println("Preenchimento do Valor da Diária é Obrigatorio!\n");
                control =false;
            } else {
                control= true;
            }
        } while (!control);

        do {
            System.out.println("Informe o Valor Mensal do Equipamento: ");
            System.out.print(" > R$");
            vlrMensal = scanner.nextDouble();
            scanner.nextLine(); 
            if (vlrMensal == 0) {
                System.out.println("Preenchimento do Valor Mensal é Obrigatorio!\n");
                control =false;
            } else {
                control= true;
            }
        } while (!control);

        do {
            System.out.println("Informe a Quantidade Total do Equipamento: ");
            System.out.print(" > ");
            qtdTotal = scanner.nextInt();
            scanner.nextLine(); 
            if (qtdTotal == 0) {
                System.out.println("Preenchimento da Quantidade Total é Obrigatorio!\n");
                control =false;
            } else {
                control= true;
            }
        } while (!control);

        System.out.println(equipamentoController.cadastrarEquipamento(descricao, vlrDiaria, vlrMensal, qtdTotal));
    }

    public void alterarEquipamento(Scanner scanner, EquipamentoController equipamentoController, ContratoController contratoController){
        boolean control = true;
        String coluna= "", desc= "";
        int campo= 0, id= 0;
        
        this.limparTela();

        this.listarEquipamentos(equipamentoController);

        do {
            System.out.print("Digite o ID do equipamento que deseja alterar: ");
            try {
                id = scanner.nextInt();
                scanner.nextLine();
                control = true;
            } catch (InputMismatchException e) {
                System.out.println("\nInforme um valor válido!");
                scanner.next();
                control= false;
            }
        } while (!control);
        
        if (contratoController.verificarEquipamentoEmContratoAtivo(id)) {
            System.out.println("Equipamento em contrato ativo! Não é possível realizar alteração.");

        }else{
            System.out.println("\nCampos disponíveis para alteração: ");
            System.out.println("1 - Descrição");
            System.out.println("2 - Valor Diário");
            System.out.println("3 - Valor Mensal");
            System.out.println("4 - Quantidade Total");
            do {
                do {
                    System.out.print("Escolha o campo que deseja alterar: ");
                    try {
                        campo = scanner.nextInt();
                        scanner.nextLine();
                        control = true;
                    } catch (InputMismatchException e) {
                        System.out.println("\nInforme um valor válido!");
                        scanner.next();
                        control= false;
                    }
                } while (!control);

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

            String valor= null;
            int valorTemp = 0;

            if (!desc.equals("Descrição")) {
                do {
                    System.out.print("\nDigite o novo valor de "+desc+": ");
                    try {
                        valorTemp = scanner.nextInt();
                        scanner.nextLine();
                        control = true;
                    } catch (InputMismatchException e) {
                        System.out.println("\nInforme um valor válido!");
                        scanner.next();
                        control= false;
                    }
                } while (!control);
                valor = String.valueOf(valorTemp);
            } else {
                System.out.print("\nDigite o novo valor de "+desc+": ");
                valor = scanner.nextLine();
            }
            
        System.out.println(equipamentoController.alterarEquipamento(id, campo, coluna, valor));
        }
    }

    public void deletarEquipamento(Scanner scanner, EquipamentoController equipamentoController, ContratoController contratoController) {
        boolean control = false;
        int id= 0, opc= 0;
        this.limparTela();

        this.listarEquipamentos(equipamentoController);

        do {
            System.out.print("Digite o ID do equipamento que deseja deletar: ");
            try {
                id = scanner.nextInt();
                scanner.nextLine();
                control = true;
            } catch (InputMismatchException e) {
                System.out.println("\nInforme um valor válido!");
                scanner.next();
                control= false;
            }
        } while (!control);

        if (contratoController.verificarEquipamentoEmContratoAtivo(id)) {
            System.out.println("\nEquipamento em contrato ativo! Não é possível realizar a remoção.");   
            System.out.print("ENTER...");
            scanner.nextLine();
        } else {
            System.out.println("\nCRÍTICO - Tem certeza que deseja Excluir o equipamento? 1- Sim 2- Não\nProsseguindo com a exclusão não será possivel consultar os Contratos do equipamento posteriormente!!");
            do {
                System.out.print(" > ");
                try {
                    opc = scanner.nextInt();
                    scanner.nextLine();
                    control = true;
                } catch (InputMismatchException e) {
                    System.out.println("\nInforme um valor válido!");
                    scanner.next();
                    control= false;
                }
            } while (!control);

            if (opc == 2) {
                System.out.println("\nExclusão cancelada!!");
            } else {
                System.out.println(equipamentoController.deletarEquipamento(id));
            }
        }
    }

    public void listarEquipamentos(EquipamentoController equipamentoController) {
        this.limparTela();
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

    public void listarEquipamentosEscolha(EquipamentoController equipamentoController, int tipo) {
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

                    if (tipo==1) {
                        System.out.print("VALOR: R$" + equipamento.getVlrMensal() + "/m");
                    } else {
                        System.out.print("VALOR: R$" + equipamento.getVlrDiaria() + "/d");
                    }

                    System.out.println("      QUANTIDADE DISPONIVEL: " + equipamento.getQtdDisponivel());
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

            if (equipamento.getIdEquip() == idEquip) {

                if (equipamento.getQtdDisponivel() < qtdEquip) {
                    System.out.println("\nQuantidade de equipamentos indisponível! Informe uma quantidade menor.");
                    return false;
                }
            }
        }

        return true;
    }

    //------------CLIENTE
    public String cliente(Scanner scanner, ClienteController clienteController) {
        boolean control= true;
        String cpf;

        do {
            System.out.println("\nDigite o CPF do cliente (APENAS NÚMEROS, SEM TRAÇOS ou PONTOS): ");
            System.out.print(" > ");
            cpf = scanner.nextLine();
            control= true;

            if (cpf.length()<11 || cpf.length()>11) {
                System.out.println("\nInforme um CPF válido!\n");
                System.out.print("ENTER...");
                scanner.nextLine();
                control= false;
            }
        } while (!control);

        if (clienteController.verificarCPF(cpf) == null) { //Cadastro do cliente
            String nome = null;
            System.out.println("CPF não cadastrado! Iniciando Cadastro de Cliente...");
            do {
                System.out.println("\nNome do cliente: ");
                System.out.print(" > ");
                nome = scanner.nextLine();
                if (nome.isEmpty()) {
                    System.out.println("Preenchimento do Nome é Obrigatorio!");
                    control =false;
                } else {
                    control= true;
                }
            } while (!control);

            String telefone= null;
            do {
                do {
                    control= true;
                    System.out.println("\nTelefone do cliente: ");
                    System.out.print(" > ");
                    telefone = scanner.nextLine();

                    if (telefone.length()<11 || telefone.length()>11) {
                        System.out.println("Informe um Telefone válido!");
                        control= false;
                    }

                } while (!control);

                if (telefone.isEmpty()) {
                    System.out.println("Preenchimento do Telefone é Obrigatorio!");
                    control =false;
                } else {
                    control= true;
                }
            } while (!control);

            System.out.println("Endereço do Cliente: ");
            System.out.print(" > ");
            String endereco = scanner.nextLine();

            System.out.println("\n"+clienteController.CadastrarCliente(nome, cpf, telefone, endereco));
        } else {
            System.out.println("\nCPF já cadastrado!\n");
        }

        System.out.print("Enter para prosseguir...");
        scanner.nextLine();
        this.limparTela();

        return cpf;
    }

    public void listarClientes(ClienteController clienteController) {
        this.limparTela();
        ArrayList<Cliente> clientes = clienteController.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("\nNenhum Cliente Cadastrado!");
        } else {
            Iterator<Cliente> iterator = clientes.iterator();

            while (iterator.hasNext()) {
                Cliente cliente = iterator.next();
                System.out.println(cliente.exibirDetalhes());
                System.out.println("-------------------------------------------------");
            }
        }
    }

    public void alterarCliente(Scanner scanner, ClienteController clienteController, ContratoController contratoController){
        boolean control = true;
        String coluna= "", desc= "";
        int campo= 0, id= 0;
        
        this.limparTela();

        this.listarClientes(clienteController);

        do {
            System.out.print("Digite o ID do Cliente que deseja Alterar: ");
            try {
                id = scanner.nextInt();
                scanner.nextLine();
                control = true;
            } catch (InputMismatchException e) {
                System.out.println("\nInforme um valor válido!");
                scanner.next();
                control= false;
            }
        } while (!control);
        
        if (contratoController.verificarClienteEmContratoAtivo(id)) {
            System.out.println("\nCliente em contrato ativo! Não é possível realizar alteração.");

        }else{
            System.out.println("\nCampos disponíveis para alteração: ");
            System.out.println("1 - Nome");
            System.out.println("2 - Telefone");
            System.out.println("3 - Endereco");
            do {
                do {
                    System.out.print("Escolha o campo que deseja alterar: ");
                    try {
                        campo = scanner.nextInt();
                        scanner.nextLine();
                        control = true;
                    } catch (InputMismatchException e) {
                        System.out.println("\nInforme um valor válido!");
                        scanner.next();
                        control= false;
                    }
                } while (!control);

                switch (campo) {
                    case 1:
                        coluna= "nome";
                        desc= "Nome";
                        break;
                    case 2:
                        coluna= "telefone"; 
                        desc="Telefone";
                        break;
                    case 3:
                        coluna= "endereco"; 
                        desc="Endereco";
                        break;
                    default:
                        System.out.println("Campo inválido!");
                        control = false;
                        break;
                }
            } while (!control);

            String valor= null;

            if (desc.equals("Telefone")) {
                do {
                    do {
                        control= true;
                        System.out.println("\nDigite o novo valor de "+desc+": ");
                        System.out.print(" > ");
                        valor = scanner.nextLine();
    
                        if (valor.length()<11 || valor.length()>11) {
                            System.out.println("Informe um Telefone válido!");
                            control= false;
                        }
    
                    } while (!control);
    
                    if (valor.isEmpty()) {
                        System.out.println("Preenchimento do Telefone é Obrigatorio!");
                        control =false;
                    } else {
                        control= true;
                    }
                } while (!control);
            } else {
                System.out.print("\nDigite o novo valor de "+desc+": ");
                valor = scanner.nextLine();
            }
            
        System.out.println(clienteController.alterarCliente(id, coluna, valor));
        }
    }

    public void deletarCliente (ContratoController contratoController, ClienteController clienteController, Scanner scanner) {
        boolean control= true;
        String cpf;
        int opc= 0;

        this.limparTela();
        
        do {
            System.out.println("\nDigite o CPF do cliente para remoção (SEM ESPAÇOS, TRAÇOS OU PONTOS): ");
            System.out.print(" > ");
            cpf = scanner.nextLine();
            control= true;

            if (cpf.length()<11 || cpf.length()>11) {
                System.out.println("\nInforme um CPF válido!\n");
                System.out.print("ENTER...");
                scanner.nextLine();
                control= false;
            }
        } while (!control);

        Cliente cliente = clienteController.verificarCPF(cpf);

        if (contratoController.verificarClienteEmContratoAtivo(cliente.getId())) {
            System.out.println("\nCliente com contrato ativo! Não é possível remové-lo.");
        }else{
            System.out.println("\nCRÍTICO - Tem certeza que deseja Excluir o cliente? 1- Sim 2- Não\nProsseguindo com a exclusão não será possivel consultar os Contratos do cliente posteriormente!!");
            do {
                System.out.print(" > ");
                try {
                    opc = scanner.nextInt();
                    scanner.nextLine();
                    control = true;
                } catch (InputMismatchException e) {
                    System.out.println("\nInforme um valor válido!");
                    scanner.next();
                    control= false;
                }
            } while (!control);

            if (opc == 2) {
                System.out.println("\nExclusão cancelada!!");
            } else {
                System.out.println("\n"+clienteController.deletarCliente(cpf)); 
            }  
        }
    }
}