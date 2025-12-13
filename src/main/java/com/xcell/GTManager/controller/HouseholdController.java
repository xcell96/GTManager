package com.xcell.GTManager.controller;

import com.xcell.GTManager.dto.HouseholdDto;
import com.xcell.GTManager.dto.HouseholdHistoryDto;
import com.xcell.GTManager.model.services.HouseholdService;
import com.xcell.GTManager.model.tables.Household;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/households")
public class HouseholdController {

    private final HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody HouseholdDto dto) {
        Household h = dto.toEntity();
        householdService.create(h);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Integer id,
            @RequestBody HouseholdDto dto
    ) {
        Household h = dto.toEntity();
        householdService.update(id, h);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public HouseholdDto getCurrent(@PathVariable Integer id) {
        return householdService.getCurrent(id);
    }

    public List<HouseholdHistoryDto> getHistory(@PathVariable Integer id){
        return householdService.getHistory(id);
    }
}
