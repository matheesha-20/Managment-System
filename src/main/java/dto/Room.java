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
    private double roomPrice_adults;
    private double roomPrice_children;
    private String roomStatus;
    private String description;
    private Boolean has_ac;
    private Boolean has_wifi;
    private Boolean has_bathroom;
    private Boolean has_tv;
}
