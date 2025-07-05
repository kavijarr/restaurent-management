package org.example.service;

import org.example.dto.DiningTableDTO;
import org.example.entity.DiningTable;

import java.util.List;
import java.util.Optional;

public interface DiningTableService {
    DiningTableDTO createDiningTable(DiningTableDTO dto);
    List<DiningTableDTO> getAllTables();
    DiningTableDTO getTableByQrCode(String qrCode);
    DiningTableDTO updateDiningTable(Long id, DiningTableDTO dto);
    void deleteDiningTable(Long id);
}
