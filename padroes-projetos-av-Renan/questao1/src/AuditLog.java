import java.util.ArrayList;
import java.util.List;

// Singleton com operações sincronizadas para não perder registros simultâneos.
public class AuditLog {
    private static final AuditLog INSTANCE = new AuditLog();
    private final List<AuditRecord> records = new ArrayList<>();

    private AuditLog() { }

    public static AuditLog getInstance() { return INSTANCE; }

    public synchronized void register(String environmentName, String provider,
                                      String result, String detail) {
        records.add(new AuditRecord(environmentName, provider, result, detail));
    }

    public synchronized void printHistory() {
        System.out.println("\n=== Histórico de auditoria ===");
        for (AuditRecord record : records) System.out.println(record);
        System.out.println("Total de registros: " + records.size());
    }
}
