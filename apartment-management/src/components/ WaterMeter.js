import React, { useEffect, useRef, useState } from "react";
import axios from "axios";

const WaterMeter = () => {
  const [rooms, setRooms] = useState([]);
  const [meterValues, setMeterValues] = useState({});
  const [selectedDate, setSelectedDate] = useState(new Date().toISOString().split('T')[0]); // เพิ่ม state สำหรับวันที่
  const inputRefs = useRef([]);

  useEffect(() => {
    axios.get("/api/rooms").then((response) => {
      setRooms(response.data);
    });
  }, []);

  const handleInputChange = (roomId, value) => {
    setMeterValues({
      ...meterValues,
      [roomId]: value,
    });
  };

  const handleDateChange = (event) => {
    setSelectedDate(event.target.value);
  };

  const handleSubmit = async (roomId) => {
    const meterValue = meterValues[roomId];

    try {
      const response = await axios.post("/api/water", {
        roomId,
        meterValue,
        recordDate: selectedDate, // ส่งวันที่ที่เลือก
      });
      console.log("บันทึกมิเตอร์น้ำสำเร็จ:", response.data);
      alert("บันทึกสำเร็จ")
      // อาจจะเคลียร์ค่า meterValue หรือทำอะไรต่อ
    } catch (error) {
      console.error("เกิดข้อผิดพลาดในการบันทึกมิเตอร์น้ำ:", error);
    }
  };

  return (
    <div>
      <h1>Water Meter</h1>
      <div>
        <label htmlFor="recordDate">วันที่บันทึก:</label>
        <input
          type="date"
          id="recordDate"
          value={selectedDate}
          onChange={handleDateChange}
        />
      </div>
      <table>
        <thead>
          <tr>
            <th>Room Number</th>
            <th>Water Meter</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {rooms.map((room, index) => (
            <tr key={room.id}>
              <td>{room.roomNumber}</td>
              <td>
                <input
                  type="number"
                  ref={(el) => (inputRefs.current[index] = el)}
                  value={meterValues[room.id] || ""}
                  onChange={(e) => handleInputChange(room.id, e.target.value)}
                />
              </td>
              <td>
                <button onClick={() => handleSubmit(room.id)}>Submit</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default WaterMeter;