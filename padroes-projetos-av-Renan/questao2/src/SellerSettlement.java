public class SellerSettlement {
    public void print(Lot lot) {
        if (!lot.isSold()) {
            System.out.println("Não há acerto: o lote " + lot.getName() + " não foi vendido.");
            return;
        }
        double saleValue = lot.getCurrentBid();
        double commission = lot.calculateCommission();
        System.out.println("\n=== Acerto do vendedor ===");
        System.out.printf("Valor de venda: R$ %.2f%n", saleValue);
        System.out.printf("Comissão cobrada: R$ %.2f%n", commission);
        System.out.printf("Valor líquido: R$ %.2f%n", saleValue - commission);
    }
}
