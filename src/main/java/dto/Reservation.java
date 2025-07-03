package dto;

import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservation {
    private Integer id;
    private String customerId;
    private String roomId;
    private LocalDate check_inDate;
    private LocalDate check_outDate;
    private Double totalPrice;
    private String status;

}
