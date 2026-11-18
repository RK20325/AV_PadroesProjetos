public class Main {
    public static void main(String[] args) {
        // Dois lotes de categorias diferentes e suas estratégias iniciais.
        Lot artLot = new Lot("Quadro antigo", "Arte", 1000.00, 1500.00, new ArtCommission());
        Lot vehicleLot = new Lot("Carro clássico", "Veículos", 5000.00, 10000.00, new VehicleCommission());

        Participant ana = new Participant("Ana");
        Participant bruno = new Participant("Bruno");
        Participant carla = new Participant("Carla");
        AuctioneerPanel panel = new AuctioneerPanel();
        Auditor auditor = new Auditor();

        // Os três participantes, o painel e o auditor acompanham os dois lotes.
        ana.follow(artLot); bruno.follow(artLot); carla.follow(artLot);
        ana.follow(vehicleLot); bruno.follow(vehicleLot); carla.follow(vehicleLot);
        artLot.addObserver(panel); artLot.addObserver(auditor);
        vehicleLot.addObserver(panel); vehicleLot.addObserver(auditor);

        System.out.println("=== Leilão do lote de Arte ===");
        ana.placeBid(artLot, 1100.00);       // Aceito.
        bruno.placeBid(artLot, 1120.00);     // Recusado: não atinge +5%.
        carla.unfollow(artLot);               // Participante deixa de acompanhar.
        bruno.placeBid(artLot, 1250.00);     // Aceito; Carla não recebe a notificação.
        ana.placeBid(artLot, 1600.00);       // Aceito.

        // Campanha: troca a regra de comissão antes do encerramento.
        artLot.setCommissionStrategy(new ElectronicsCommission());
        artLot.close();

        System.out.println("\n=== Leilão do lote de Veículos ===");
        carla.placeBid(vehicleLot, 5500.00); // Aceito.
        ana.placeBid(vehicleLot, 5600.00);   // Recusado: não atinge +5%.
        bruno.placeBid(vehicleLot, 6000.00); // Aceito, mas abaixo da reserva.
        vehicleLot.close();                  // Não vendido.

        // Fatura com três tipos de serviço; embalagem especial é contratada duas vezes.
        if (artLot.isSold()) {
            InvoiceComponent invoice = new Sale(artLot.getName(), artLot.getCurrentBid());
            invoice = new TransportInsurance(invoice);
            invoice = new AuthenticityCertificate(invoice);
            invoice = new SpecialPackaging(invoice);
            invoice = new SpecialPackaging(invoice);
            new InvoicePrinter().print(invoice);
            new SellerSettlement().print(artLot);
        }

        auditor.printReport();
    }
}
