import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditRecord {
    private final LocalDateTime dateTime;
    private final String environmentName;
    private final String provider;
    private final String result;
    private final String detail;

    public AuditRecord(String environmentName, String provider, String result, String detail) {
        this.dateTime = LocalDateTime.now();
        this.environmentName = environmentName;
        this.provider = provider;
        this.result = result;
        this.detail = detail;
    }

    public String toString() {
        String date = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        return date + " | " + environmentName + " | " + provider + " | " + result + " | " + detail;
    }
}
