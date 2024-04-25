import java.io.IOException;
import java.math.BigDecimal;

public class CurrencyConverter {
    public BigDecimal convertCurrency(BigDecimal amount, String fromCurrency, String toCurrency) throws IOException, InterruptedException {
        BigDecimal exchangeRate = ApiService.getExchangeRate(fromCurrency, toCurrency);
        return amount.multiply(exchangeRate);
    }
}
