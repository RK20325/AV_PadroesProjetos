import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// Singleton: a configuração é carregada uma única vez.
public class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();
    private final String defaultRegion;
    private final double monthlyBudget;

    private AppConfig() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("questao1/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Não foi possível ler questao1/config.properties. Usando valores padrão.");
        }
        defaultRegion = properties.getProperty("default.region", "us-east-1");
        monthlyBudget = Double.parseDouble(properties.getProperty("monthly.budget", "1000.0"));
    }

    public static AppConfig getInstance() { return INSTANCE; }
    public String getDefaultRegion() { return defaultRegion; }
    public double getMonthlyBudget() { return monthlyBudget; }
}
