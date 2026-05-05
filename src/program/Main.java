package program;

import entities.*;
import ultilities.Registravel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<funcionarios> funcionario = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = -1;

        // Regra 1: Boas-vindas e menu de opções
        System.out.println("=== Bem-vindo ao Sistema de RH ===");

        while (opcao != 0) {
            System.out.println("\n1. Criar Funcionário");
            System.out.println("2. Registrar Ponto");
            System.out.println("3. Listar Funcionários");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1 -> criarFuncionario();
                case 2 -> gerenciarRegistroPonto();
                case 3 -> listarTodos();
                case 0 -> System.out.println("Encerrando sistema...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void criarFuncionario(){
        System.out.println("Selecione o tipo de funcionario que deseja criar: 1.Gerente 2.Coordenador 3.Analista 4.Assistente 5.Estagiario");
        Integer tipo = scanner.nextInt();
        System.out.println("Id do novo funcionario: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nome do novo funcionario: ");
        String name = scanner.nextLine();

        funcionarios f = switch (tipo){
            case 1 -> new gerente(name, id);
            case 2 -> new coordenador(name, id);
            case 3 -> new analista(name, id);
            case 4 -> new assistente(name, id);
            case 5 -> new estagiário(name, id);
            default -> null;
        };

        if(f != null){
            funcionario.add(f);
            System.out.println("Funcionario Cadastrado!");
        }else {
            System.out.println("Cancelando adição de funcionario...");
            return;
        }

    }

    private static void gerenciarRegistroPonto(){
        Registravel f = new Registravel() {
            @Override
            public void registrarPonto(LocalDateTime entryTime, LocalDateTime exitTime) {
                Registravel.super.registrarPonto(entryTime, exitTime);
            }
        };
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        try{

            if(f instanceof Registravel registravel){
            System.out.println("Digite a data e hora de ENTRADA (dd/MM/yyyy HH:mm): ");
            String entradaStr = scanner.nextLine();
            LocalDateTime entrada = LocalDateTime.parse(entradaStr, parser);
            System.out.println("Digite a data e hora de SAÍDA (dd/MM/yyyy HH:mm): ");
            String saidaStr = scanner.nextLine();
            LocalDateTime saida = LocalDateTime.parse(saidaStr, parser);

            f.registrarPonto(entrada, saida);
        }
        }catch (Exception e){
            throw new RuntimeException("Formato de data inválido! Use: 05/05/2026 09:00! Erro: " + e.getMessage());
        }
    }

    private static void listarTodos() {
        if (funcionario.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            funcionario.forEach(f -> System.out.println("ID: " + f.getId() + " | Nome: " + f.getName() + " | Cargo: " + f.getClass().getSimpleName()));
        }
    }

}