package org.example.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiningTableDTO {
    private Long id;
    private String qrCode;
    private String name;
}
