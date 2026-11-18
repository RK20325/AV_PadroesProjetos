import java.time.LocalDateTime;

public class Bid {
    private final String bidderName;
    private final double value;
    private final LocalDateTime dateTime;

    public Bid(String bidderName, double value) {
        this.bidderName = bidderName;
        this.value = value;
        this.dateTime = LocalDateTime.now();
    }

    public String getBidderName() { return bidderName; }
    public double getValue() { return value; }
    public LocalDateTime getDateTime() { return dateTime; }
}
