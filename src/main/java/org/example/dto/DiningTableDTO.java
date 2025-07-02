package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiningTableDTO {
    private Long id;
    private String qrCode;
    private String tableName;
}
