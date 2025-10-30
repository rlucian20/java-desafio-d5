import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConversaoService {

    private static final String ARQUIVO = "conversao.txt";

    public void registrarConversao(BigDecimal valorConvertido, String moedaOrigem, String moedaDestino, BigDecimal valorInicialBigDecimal, BigDecimal taxaDescontoBigDecimal) {
        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = hoje.format(formatter);

        String registro = String.format(
                "Data: %s | De: %s | Para: %s | Valor Anterior: %s | Taxa de conversão: %s | Valor Final: %s",
                dataFormatada, moedaOrigem, moedaDestino, valorInicialBigDecimal, taxaDescontoBigDecimal, valorConvertido
        );

        try (FileWriter fw = new FileWriter(ARQUIVO, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(registro);  // println já adiciona a quebra de linha
        } catch (IOException e) {
            throw new RuntimeException("Erro ao registrar conversão: " + e.getMessage(), e);
        }


    }

    public void gerarRelatorio() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo de conversão não encontrado.", e);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler arquivo de conversão.", e);
        }
    }

}