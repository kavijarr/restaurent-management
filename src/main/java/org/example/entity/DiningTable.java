package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dining_tables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiningTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String qrCode;

    private String name; // e.g., "Table 1"
}
