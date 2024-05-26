package com.conversor.main;

import com.conversor.service.ServicoDeRequisicao;
import java.util.Scanner;

public class ConversorDeMoeda {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicoDeRequisicao service = new ServicoDeRequisicao();

        while (true) {
            String baseCurrency = "";
            String targetCurrency = "";

            System.out.println("*************************************************************");
            System.out.println("============ Bem Vindo ao Conversor de Moeda ================");
            System.out.println("*************************************************************");
            System.out.println("");
            System.out.println("");
            System.out.println("   OPÇÕES:");
            System.out.println("");
            System.out.println("1 - Dólar dos Estados Unidos ==> Real Brasileiro");
            System.out.println("2 - Peso Argentino ==> Dólar das Bahamas");
            System.out.println("3 - Peso Chileno ==> Euro");
            System.out.println("4 - Rupia do Sri Lanka ==> Dinar Líbio");
            System.out.println("5 - Boliviano Boliviano ==> Libra Egípcia");
            System.out.println("6 - Dólar do Caribe Oriental ==> Peso Colombiano");
            System.out.println("");
            System.out.println("Digite SAIR para finalizar.");
            System.out.println("*************************************************************");
            System.out.println("");
            System.out.print("Escolha alguma das opções acima: ");

            String option = scanner.nextLine().trim().toUpperCase();

            if (option.equals("SAIR")) {
                System.out.println("Finalizando o programa.");
                break;
            }

            switch (option) {
                case "1":
                    baseCurrency = "USD";
                    targetCurrency = "BRL";
                    break;
                case "2":
                    baseCurrency = "ARS";
                    targetCurrency = "BSD";
                    break;
                case "3":
                    baseCurrency = "CLP";
                    targetCurrency = "EUR";
                    break;
                case "4":
                    baseCurrency = "LKR";
                    targetCurrency = "LYD";
                    break;
                case "5":
                    baseCurrency = "BOB";
                    targetCurrency = "EGP";
                    break;
                case "6":
                    baseCurrency = "XCD";
                    targetCurrency = "COP";
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;
            }


            try {
                double rate = service.getExchangeRate(baseCurrency, targetCurrency);
                System.out.println("Taxa de câmbio " + baseCurrency + " para " + targetCurrency + ": " + rate);
            } catch (Exception e) {
                System.out.println("Erro ao obter a taxa de câmbio: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
