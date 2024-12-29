package com.apartment.management.controller;
import com.apartment.management.model.Room;
import com.apartment.management.model.WaterMeter;
import com.apartment.management.repository.WaterMeterRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/water")
public class WaterController {

    private  final WaterMeterRepository waterMeterRepository;

    public WaterController(WaterMeterRepository waterMeterRepository) {
        this.waterMeterRepository = waterMeterRepository;
    }

    @GetMapping
    public List<WaterMeter> getAllWater() {
        return waterMeterRepository.findAll();
    }
    @PostMapping
    public WaterMeter createWaterMeter(@RequestBody WaterMeter waterMeter) {
        return waterMeterRepository.save(waterMeter);
    }

}
