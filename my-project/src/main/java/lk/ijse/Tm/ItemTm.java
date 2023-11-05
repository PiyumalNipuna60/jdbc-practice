package lk.ijse.Tm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class ItemTm {
    private String itemCode;
    private String description;
    private double price;
    private int qty;
}