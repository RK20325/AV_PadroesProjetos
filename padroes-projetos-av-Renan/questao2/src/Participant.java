public class Participant implements BidObserver {
    private final String name;

    public Participant(String name) { this.name = name; }
    public String getName() { return name; }

    public void follow(Lot lot) { lot.addObserver(this); }
    public void unfollow(Lot lot) { lot.removeObserver(this); }
    public boolean placeBid(Lot lot, double value) { return lot.placeBid(this, value); }

    public void onNewBid(Lot lot, Bid bid) {
        System.out.printf("[Participante %s] Novo lance em %s: R$ %.2f%n",
                name, lot.getName(), bid.getValue());
    }
}
