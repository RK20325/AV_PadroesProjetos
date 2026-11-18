public class InvoicePrinter {
    // Não verifica quais serviços foram contratados: trata o objeto Decorator como um todo.
    public void print(InvoiceComponent invoice) {
        System.out.println("\n=== Fatura do comprador ===");
        for (String line : invoice.getLines()) System.out.println(line);
        System.out.printf("TOTAL A PAGAR: R$ %.2f%n", invoice.getTotal());
    }
}
