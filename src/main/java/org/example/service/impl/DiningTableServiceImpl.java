package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.DiningTableDTO;
import org.example.entity.DiningTable;
import org.example.repository.DiningTableRepository;
import org.example.service.DiningTableService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiningTableServiceImpl implements DiningTableService {

    private final DiningTableRepository diningTableRepository;
    @Override
    public DiningTableDTO createDiningTable(DiningTableDTO dto) {
        DiningTable table = DiningTable.builder()
                .name(dto.getName())
                .qrCode(dto.getQrCode())
                .build();
        DiningTable saved = diningTableRepository.save(table);
        return mapToDTO(saved);
    }

    @Override
    public List<DiningTableDTO> getAllTables() {
        return diningTableRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DiningTableDTO getTableByQrCode(String qrCode) {
        return diningTableRepository.findByQrCode(qrCode)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Table not found"));
    }

    @Override
    public void deleteDiningTable(Long id) {
        diningTableRepository.deleteById(id);
    }

    @Override
    public DiningTableDTO updateDiningTable(Long id, DiningTableDTO dto) {
        DiningTable table = diningTableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found"));
        table.setName(dto.getName());
        table.setQrCode(dto.getQrCode());
        DiningTable updated = diningTableRepository.save(table);
        return mapToDTO(updated);
    }

    private DiningTableDTO mapToDTO(DiningTable table) {
        return DiningTableDTO.builder()
                .id(table.getId())
                .name(table.getName())
                .qrCode(table.getQrCode())
                .build();
    }
}
