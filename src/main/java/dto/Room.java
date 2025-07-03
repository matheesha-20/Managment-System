package dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Room {
    private int roomId;
    private String roomName;
    private String roomType;
    private double roomPrice;
    private String roomStatus;
}
