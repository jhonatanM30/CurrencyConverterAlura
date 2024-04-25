import java.io.IOException;
import java.util.Scanner;
import java.math.BigDecimal;

public class UserInterface {

    private final Scanner scanner = new Scanner(System.in);
    private final CurrencyConverter converter = new CurrencyConverter();

    public void start() {
        while (true) {
            printMenu();
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (option == 5) {
                System.out.println("¡Hasta luego!");
                break;
            }

            System.out.println("Ingrese el monto a convertir:");
            BigDecimal amount = scanner.nextBigDecimal();

            String fromCurrency, toCurrency;
            switch (option) {
                case 1:
                    fromCurrency = "USD";
                    toCurrency = "COP";
                    break;
                case 2:
                    fromCurrency = "COP";
                    toCurrency = "USD";
                    break;
                case 3:
                    fromCurrency = "USD";
                    toCurrency = "EUR";
                    break;
                case 4:
                    fromCurrency = "EUR";
                    toCurrency = "USD";
                    break;
                default:
                    System.out.println("Opción inválida, por favor intente de nuevo.");
                    continue;
            }

            try {
                BigDecimal convertedAmount = converter.convertCurrency(amount, fromCurrency, toCurrency);

                String line = " #######################";
                String format = " %.2f %s Equivalen a %.2f %s: ";

                System.out.printf("%s%s%s\n", line, String.format(format, amount, fromCurrency, convertedAmount, "en moneda " + toCurrency), line);
            } catch (InterruptedException | IOException e) {
                System.out.println("Error al realizar la conversión: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Convertir de dólar a peso colombiano");
        System.out.println("2. Convertir de peso colombiano a dólar");
        System.out.println("3. Convertir de dólar a euro");
        System.out.println("4. Convertir de euro a dólar");
        System.out.println("5. Salir");
    }
}
