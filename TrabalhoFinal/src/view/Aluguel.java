package view;

import control.*;
import dao.*;
import model.*;
import java.util.Scanner;


public class Aluguel {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Scanner scanner = new Scanner(System.in);
        ClienteController clienteController = new ClienteController();
        
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
        
    }
}
