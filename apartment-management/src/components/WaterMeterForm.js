import React, { useState, useEffect } from "react";
import axios from "axios";


const WaterMeterForm = () => {
  // สร้าง state สำหรับเก็บวันที่
  const [currentDate, setCurrentDate] = useState("");

  // ฟังก์ชันดึงวันที่ปัจจุบัน
  useEffect(() => {
    const today = new Date();
    const formattedDate = today.toISOString().split("T")[0]; // รูปแบบ YYYY-MM-DD
    setCurrentDate(formattedDate);
  }, []);

  // สร้าง state สำหรับฟอร์ม
  const [waterMeter, setWaterMeter] = useState({
    roomId: "",
    previousMeter: "",
    currentMeter: "",
    pricePerUnit: "",
  });

  // ฟังก์ชันจัดการการเปลี่ยนแปลงของ input
  const handleChange = (e) => {
    const { name, value } = e.target;
    setWaterMeter({ ...waterMeter, [name]: value });
  };

  // ฟังก์ชันจัดการการบันทึกข้อมูล
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // ส่งข้อมูลไปยัง backend รวมวันที่ปัจจุบัน
      await axios.post("http://localhost:8080/api/water", {
        ...waterMeter,
        recordDate: currentDate,
      });
      alert("บันทึกข้อมูลสำเร็จ!");
    } catch (error) {
      console.error("เกิดข้อผิดพลาด:", error);
    }
  };

  return (
    <div className="water-meter-form">
      <h2>Water Meter Form</h2>

      {/* แสดงวันที่ปัจจุบัน */}
      <p>📅 วันที่: {currentDate}</p>

      <form onSubmit={handleSubmit}>
        <div>
          <label>Room ID:</label>
          <input
            type="text"
            name="roomId"
            value={waterMeter.roomId}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Previous Meter:</label>
          <input
            type="number"
            name="previousMeter"
            value={waterMeter.previousMeter}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Current Meter:</label>
          <input
            type="number"
            name="currentMeter"
            value={waterMeter.currentMeter}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Price per Unit:</label>
          <input
            type="number"
            name="pricePerUnit"
            value={waterMeter.pricePerUnit}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">บันทึกข้อมูล</button>
      </form>
    </div>
  );
};

export default WaterMeterForm;
