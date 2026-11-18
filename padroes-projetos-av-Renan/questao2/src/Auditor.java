import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Auditor implements BidObserver {
    private final List<String> records = new ArrayList<>();

    public String getName() { return "Auditor"; }

    public void onNewBid(Lot lot, Bid bid) {
        String date = bid.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String record = date + " | " + lot.getName() + " | " + bid.getBidderName()
                + " | R$ " + String.format("%.2f", bid.getValue());
        records.add(record);
        System.out.println("[Auditoria] " + record);
    }

    public void printReport() {
        System.out.println("\n=== Registros do auditor ===");
        for (String record : records) System.out.println(record);
    }
}
