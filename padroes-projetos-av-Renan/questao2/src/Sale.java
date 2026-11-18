import java.util.ArrayList;
import java.util.List;

// Componente base da fatura, válido mesmo sem serviços adicionais.
public class Sale implements InvoiceComponent {
    private final String lotName;
    private final double saleValue;

    public Sale(String lotName, double saleValue) {
        this.lotName = lotName;
        this.saleValue = saleValue;
    }

    public List<String> getLines() {
        List<String> lines = new ArrayList<>();
        lines.add("Lote: " + lotName);
        lines.add(String.format("Valor de venda: R$ %.2f", saleValue));
        return lines;
    }

    public double getSaleValue() { return saleValue; }
    public double getTotal() { return saleValue; }
}
