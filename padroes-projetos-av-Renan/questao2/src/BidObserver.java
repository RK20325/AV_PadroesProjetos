public interface BidObserver {
    String getName();
    void onNewBid(Lot lot, Bid bid);
}
