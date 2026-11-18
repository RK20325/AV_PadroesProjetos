import java.util.List;

public interface InvoiceComponent {
    List<String> getLines();
    double getSaleValue();
    double getTotal();
}
