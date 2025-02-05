package com.apartment.management.service;

import com.apartment.management.model.Billing;
import java.util.List;

public interface BillingService {
    List<Billing> generateBillingForCurrentMonth();

    List<Billing> getBillingRecordsForCurrentMonth();

    Billing calculateBillingForRoom(Long roomId, String month); // ✅ ใช้ String

    List<Billing> getBillingSummaryByMonth(String month); // ✅ ใช้ String
}



// package com.apartment.management.service;

// import java.util.List;

// import com.apartment.management.model.Billing;

// public interface BillingService {

//     // สร้างใบแจ้งหนี้สำหรับทุกห้องในเดือนปัจจุบัน
//     List<Billing> generateBillingForCurrentMonth();

//     // ดึงข้อมูลใบแจ้งหนี้ของห้องทั้งหมดในเดือนปัจจุบัน
//     List<Billing> getBillingRecordsForCurrentMonth();

//     // คำนวณค่าใช้จ่าย (มิเตอร์น้ำและไฟ) สำหรับห้องตามเดือน
//     Billing calculateBillingForRoom(Long roomId, String month);

//     // สรุปค่าใช้จ่ายทั้งหมดในเดือนนี้ (บิลรวม)
//     List<Billing> getBillingSummaryByMonth(String month);
// }
