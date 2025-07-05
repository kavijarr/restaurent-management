package org.example.repository;

import org.example.entity.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiningTableRepository extends JpaRepository<DiningTable,Long> {
    Optional<DiningTable> findByQrCode(String qrCode);
}
