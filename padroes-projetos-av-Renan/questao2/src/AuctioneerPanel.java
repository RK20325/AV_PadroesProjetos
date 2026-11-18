public class AuctioneerPanel implements BidObserver {
    public String getName() { return "Painel do leiloeiro"; }

    public void onNewBid(Lot lot, Bid bid) {
        System.out.printf("[Painel] %s | maior lance: R$ %.2f | lances recebidos: %d%n",
                lot.getName(), lot.getCurrentBid(), lot.getBids().size());
    }
}
