import java.util.ArrayList;
import java.util.List;

// Subject do Observer: conhece somente a interface BidObserver.
public class Lot {
    private final String name;
    private final String category;
    private final double startingPrice;
    private final double reservePrice;
    private final List<BidObserver> observers = new ArrayList<>();
    private final List<Bid> bids = new ArrayList<>();
    private CommissionStrategy commissionStrategy;
    private double currentBid;
    private boolean open = true;
    private boolean sold;
    private double commissionAmount;

    public Lot(String name, String category, double startingPrice,
               double reservePrice, CommissionStrategy commissionStrategy) {
        this.name = name;
        this.category = category;
        this.startingPrice = startingPrice;
        this.currentBid = startingPrice;
        this.reservePrice = reservePrice;
        this.commissionStrategy = commissionStrategy;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getStartingPrice() { return startingPrice; }
    public double getCurrentBid() { return currentBid; }
    public boolean isOpen() { return open; }
    public boolean isSold() { return sold; }
    public List<Bid> getBids() { return new ArrayList<>(bids); }

    public void addObserver(BidObserver observer) {
        if (!observers.contains(observer)) observers.add(observer);
    }

    public void removeObserver(BidObserver observer) { observers.remove(observer); }

    public boolean placeBid(BidObserver bidder, double value) {
        if (!open) {
            System.out.println("Lance recusado: o lote " + name + " já foi encerrado.");
            return false;
        }
        double minimum = currentBid * 1.05;
        if (value + 0.0000001 < minimum) {
            System.out.printf("Lance de %s recusado. O mínimo para %s é R$ %.2f.%n",
                    bidder.getName(), name, minimum);
            return false;
        }

        currentBid = value;
        Bid bid = new Bid(bidder.getName(), value);
        bids.add(bid);
        System.out.printf("Lance de %s aceito em %s: R$ %.2f%n", bidder.getName(), name, value);

        // O autor do lance não recebe a própria notificação.
        for (BidObserver observer : new ArrayList<>(observers)) {
            if (observer != bidder) observer.onNewBid(this, bid);
        }
        return true;
    }

    public void setCommissionStrategy(CommissionStrategy commissionStrategy) {
        if (!open) throw new IllegalStateException("A regra de comissão não pode mudar após o encerramento.");
        this.commissionStrategy = commissionStrategy;
    }

    public double calculateCommission() {
        return open ? commissionStrategy.calculate(currentBid) : commissionAmount;
    }

    public void close() {
        if (!open) return;
        open = false;
        sold = currentBid >= reservePrice;
        if (sold) commissionAmount = commissionStrategy.calculate(currentBid);
        if (sold) {
            System.out.printf("Lote %s VENDIDO por R$ %.2f.%n", name, currentBid);
        } else {
            System.out.printf("Lote %s NÃO VENDIDO. Maior lance: R$ %.2f; reserva: R$ %.2f.%n",
                    name, currentBid, reservePrice);
        }
    }
}
