import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class View {
    public void menu(){
        Scanner ler = new Scanner(System.in);
        System.out.println("Bem-vindo ao conversor de moedas: ");

        while (true) {
            System.out.print("\n1- Converter moeda " +
                    "\n2- Historico de conversões" +
                    "\n0- Sair" +
                    "\nSelecione uma opção: ");

            String menu = ler.nextLine();
            int opcao= 10;
            try {
                opcao = Integer.valueOf(menu);

            } catch (Exception e) {
                System.out.println("Opção escolhida Inválida" + e);
            }
            switch (opcao) {
                case 1:
                    BigDecimal valorInicialBigDecimal = null;
                    BigDecimal taxaDescontoBigDecimal = null;

                    System.out.print("Moeda Origem ('USD, BRL, EUR'): ");
                    String moedaOrigem = ler.nextLine().toUpperCase().trim();

                    System.out.print("Moeda Destino ('USD, BRL, EUR'): ");
                    String moedaDestino = ler.nextLine().toUpperCase().trim();

                    System.out.print("Valor a ser convertido: ");
                    String valorInicial = ler.nextLine().trim();

                    System.out.print("Taxa Desconto: ");
                    String taxaDesconto = ler.nextLine().trim();

                    Conversao conversao = new Conversao();

                    try {
                        valorInicialBigDecimal = new BigDecimal(valorInicial);
                        taxaDescontoBigDecimal =  new BigDecimal(taxaDesconto).divide(BigDecimal.valueOf(100),4, RoundingMode.HALF_UP);
                    }
                    catch (NumberFormatException e){
                        System.out.println("Erro algo não numerico digitado " + e);
                        return;
                    }
                    BigDecimal valorConvertido = conversao.converterMoeda(valorInicialBigDecimal, taxaDescontoBigDecimal, moedaOrigem, moedaDestino);

                    System.out.println("O valor em: " + moedaDestino + " e: " + valorConvertido);

                    ConversaoService gravacao = new ConversaoService();
                    gravacao.registrarConversao(valorConvertido, moedaOrigem, moedaDestino, valorInicialBigDecimal, taxaDescontoBigDecimal);
                    break;
                case 2:
                    ConversaoService relatorio = new ConversaoService();
                    relatorio.gerarRelatorio();
                    break;

                case 0:
                    System.exit(0);
                    break;
                default: System.out.println("Opção Inválida.");
            }

        }
    }
}
