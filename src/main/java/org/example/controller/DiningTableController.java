package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.DiningTableDTO;
import org.example.service.DiningTableService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class DiningTableController {

    private final DiningTableService diningTableService;

    @PostMapping
    public DiningTableDTO createTable(@RequestBody DiningTableDTO dto){
        return diningTableService.createDiningTable(dto);
    }

    @GetMapping
    public List<DiningTableDTO> getAllTables() {
        return diningTableService.getAllTables();
    }

    @GetMapping("/{qrCode}")
    public DiningTableDTO getTableByQrCode(@PathVariable String qrCode) {
        return diningTableService.getTableByQrCode(qrCode);
    }

    @PutMapping("/{id}")
    public DiningTableDTO updateTable(@PathVariable Long id, @RequestBody DiningTableDTO dto) {
        return diningTableService.updateDiningTable(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable Long id) {
        diningTableService.deleteDiningTable(id);
    }
}
