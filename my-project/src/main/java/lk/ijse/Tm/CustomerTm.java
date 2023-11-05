package lk.ijse.Tm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class CustomerTm {
    private String id ;
    private String name;
    private String address;
    private int tel;
}
