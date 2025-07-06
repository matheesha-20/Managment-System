package dto;

import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservation {
    private String customer_id;
    private String room_number;
    private LocalDate check_in;
    private LocalDate check_out;
    private Double price;
    private String status;
    private Integer num_of_adults;
    private Integer num_of_children;
}
