import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiService {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public static BigDecimal getExchangeRate(String baseCurrency, String targetCurrency) throws IOException {
        String apiUrl = API_URL + baseCurrency;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Error al obtener tasas de cambio. Código de respuesta: " + responseCode);
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            // Parsear la respuesta JSON para obtener la tasa de cambio
            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonObject conversionRates  = jsonResponse.getAsJsonObject("rates");

            return conversionRates.get(targetCurrency).getAsBigDecimal();
        }
    }
}
