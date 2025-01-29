package com.apartment.management.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.apartment.management.model.ElectricMeter;
import com.apartment.management.model.WaterMeter;

public interface MeterService {

    WaterMeter saveWaterMeter(Long roomId, BigDecimal meterValue, LocalDate recordDate) throws Exception; // ✅ ต้อง return WaterMeter

    ElectricMeter saveElectricMeter(Long roomId, BigDecimal meterValue, LocalDate recordDate) throws Exception; // ✅ ต้อง return ElectricMeter

    List<WaterMeter> getWaterMeterByRoomAndMonth(Long roomId, LocalDate month); // ✅ ตรวจสอบ Method Signature

    List<ElectricMeter> getElectricMeterByRoomAndMonth(Long roomId, LocalDate month);

    Optional<WaterMeter> getLatestWaterMeter(Long roomId); // ✅ ตรวจสอบว่ามี Method นี้จริง

    Optional<ElectricMeter> getLatestElectricMeter(Long roomId);
}


// import com.apartment.management.model.ElectricMeter;
// import com.apartment.management.model.WaterMeter;

// import java.time.LocalDate;
// import java.time.YearMonth;
// import java.util.List;

// public interface MeterService {

//     void saveWaterMeter(Long roomId, Double meterValue, LocalDate recordDate) throws Exception;

//     void saveElectricMeter(Long roomId, Double meterValue, LocalDate recordDate) throws Exception;

//     List<WaterMeter> getWaterMeterRecordsByRoomAndMonth(Long roomId, YearMonth month);

//     List<ElectricMeter> getElectricMeterRecordsByRoomAndMonth(Long roomId, YearMonth month);

//     List<ElectricMeter> getElectricMeterRecordsByMonth(YearMonth month);

//     //List<?> getMeterRecordsByRoomNumber(String roomNumber) throws Exception; // เพิ่ม throws Exception
// }
