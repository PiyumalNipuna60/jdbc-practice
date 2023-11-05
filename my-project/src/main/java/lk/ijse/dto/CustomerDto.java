package lk.ijse.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class CustomerDto {
    private String id ;
    private String name;
    private String address;
    private int tel;
}
