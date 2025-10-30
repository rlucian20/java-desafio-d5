import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Conversao {
    public static final Map<String, Map<String, BigDecimal>> taxasCambio = new HashMap<>();

    static {
        Map<String, BigDecimal> usd = new HashMap<>();
        usd.put("USD", BigDecimal.valueOf(1));
        usd.put("EUR", BigDecimal.valueOf(0.8599001336));
        usd.put("BRL", BigDecimal.valueOf(5.3594005576));
        taxasCambio.put("USD",usd);

        Map<String, BigDecimal> brl = new HashMap<>();
        brl.put("BRL", BigDecimal.valueOf(1));
        brl.put("EUR", BigDecimal.valueOf(0.1595243478));
        brl.put("USD", BigDecimal.valueOf(0.1855149704));
        taxasCambio.put("BRL", brl);

        Map<String, BigDecimal> eur = new HashMap<>();
        eur.put("BRL", BigDecimal.valueOf(6.2686355624));
        eur.put("EUR", BigDecimal.valueOf(1));
        eur.put("USD", BigDecimal.valueOf(1.1629257409));
        taxasCambio.put("EUR", eur);
    }

    public BigDecimal converterMoeda(BigDecimal valorInicialBigDecimal, BigDecimal taxaDescontoBigDecimal, String moedaOrigem, String moedaDestino) {

        //      Fazendo o calculo da taxaDesconto
        valorInicialBigDecimal = valorInicialBigDecimal.subtract(valorInicialBigDecimal.multiply(taxaDescontoBigDecimal));

        if (!taxasCambio.containsKey(moedaOrigem) || !taxasCambio.get(moedaOrigem).containsKey(moedaDestino)){
            throw new IllegalArgumentException("Conversão não suportada: " + moedaOrigem + " → "+  moedaDestino);
        }

        BigDecimal valorConvertido = valorInicialBigDecimal
                .multiply(taxasCambio.get(moedaOrigem).get(moedaDestino))
                .setScale(2, RoundingMode.HALF_UP);

        return valorConvertido;


    }
}
