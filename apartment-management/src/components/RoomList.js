import React, { useEffect, useRef, useState } from "react";
import axios from "axios";
import "./RoomList.css"; // Import CSS สำหรับตกแต่ง

const RoomList = () => {
  const [rooms, setRooms] = useState([]);
  const [meterValues, setMeterValues] = useState({});
  const [currentDate, setCurrentDate] = useState("");
  const inputRefs = useRef([]); // สร้าง Array ของ Ref สำหรับ input

  // ดึงข้อมูลห้องและวันที่ปัจจุบัน
  useEffect(() => {
    // ดึงข้อมูลห้อง
    axios.get("/api/rooms").then((response) => {
      setRooms(response.data);
    });

    // ดึงวันที่ปัจจุบัน
    const today = new Date();
    const formattedDate = today.toISOString().split("T")[0]; // รูปแบบ YYYY-MM-DD
    setCurrentDate(formattedDate);
  }, []);

  // ฟังก์ชันจัดการการเปลี่ยนแปลงค่า Water Meter
  const handleInputChange = (roomId, value) => {
    setMeterValues({
      ...meterValues,
      [roomId]: value,
    });
  };

  // ฟังก์ชันตรวจจับ Enter เพื่อบันทึกและเลื่อนไปยังช่องถัดไป
  const handleKeyDown = (e, index, roomId) => {
    if (e.key === "Enter") {
      handleSubmit(roomId); // บันทึกข้อมูล

      // โฟกัสไปที่ input ถัดไป
      const nextIndex = index + 1;
      if (nextIndex < inputRefs.current.length) {
        inputRefs.current[nextIndex].focus();
      }
    }
  };

  // ฟังก์ชันบันทึกค่า Water Meter และวันที่ไปยัง Database
  const handleSubmit = (roomId) => {
    const meterValue = meterValues[roomId];
    axios
      .post("/api/rooms/meter", { roomId, meterValue, recordDate: currentDate })
      .then(() => {
        alert(`บันทึกข้อมูลห้อง ${roomId} สำเร็จ!`);
      })
      .catch((error) => {
        console.error("เกิดข้อผิดพลาด:", error);
      });
  };

  return (
    <div className="water-meter-container">
      <h1>Water Meter</h1>

      {/* แสดงวันที่ปัจจุบัน */}
      <p className="date-display">📅 วันที่: {currentDate}</p>

      <table className="responsive-table">
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
                  ref={(el) => (inputRefs.current[index] = el)} // เก็บ Ref ของแต่ละ input
                  value={meterValues[room.id] || ""}
                  onChange={(e) => handleInputChange(room.id, e.target.value)}
                  onKeyDown={(e) => handleKeyDown(e, index, room.id)} // ตรวจจับ Enter
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

export default RoomList;
//
//
//// RoomList.js
//import React, { useEffect, useRef, useState } from "react";
//import axios from "axios";
//import "./RoomList.css"; // CSS สำหรับจัดการ Responsive UI
//
//const RoomList = () => {
//  const [rooms, setRooms] = useState([]);
//  const [meterValues, setMeterValues] = useState({});
//  const [currentDate, setCurrentDate] = useState("");
//  const inputRefs = useRef([]);
//
//  useEffect(() => {
//    // ดึงข้อมูลห้องจาก API
//    axios.get("/api/rooms").then((response) => {
//      setRooms(response.data);
//    });
//
//    // ตั้งค่าวันที่ปัจจุบัน
//    const today = new Date();
//    const formattedDate = `${today.getFullYear()}-${(today.getMonth() + 1).toString().padStart(2, '0')}-${today.getDate().toString().padStart(2, '0')}`;
//    setCurrentDate(formattedDate);
//  }, []);
//
//  const handleInputChange = (roomId, value) => {
//    setMeterValues({
//      ...meterValues,
//      [roomId]: value,
//    });
//  };
//
//  const handleKeyDown = (e, index, roomId) => {
//    if (e.key === "Enter") {
//      handleSubmit(roomId);
//      const nextIndex = index + 1;
//      if (nextIndex < inputRefs.current.length) {
//        inputRefs.current[nextIndex].focus();
//      }
//    }
//  };
//
//  const handleSubmit = (roomId) => {
//    const meterValue = meterValues[roomId];
//    axios
//      .post("/api/water", {
//        roomId,
//        currentMeter: meterValue,
//        recordDate: currentDate,
//      })
//      .catch((error) => {
//        console.error(error);
//      });
//  };
//
//  return (
//    <div className="room-list">
//      <h1>Water Meter</h1>
//      <p>Date: {currentDate}</p>
//      <table>
//        <thead>
//          <tr>
//            <th>Room Number</th>
//            <th>Water Meter</th>
//            <th>Actions</th>
//          </tr>
//        </thead>
//        <tbody>
//          {rooms.map((room, index) => (
//            <tr key={room.id}>
//              <td>{room.roomNumber}</td>
//              <td>
//                <input
//                  type="number"
//                  ref={(el) => (inputRefs.current[index] = el)}
//                  value={meterValues[room.id] || ""}
//                  onChange={(e) => handleInputChange(room.id, e.target.value)}
//                  onKeyDown={(e) => handleKeyDown(e, index, room.id)}
//                />
//              </td>
//              <td>
//                <button onClick={() => handleSubmit(room.id)}>Submit</button>
//              </td>
//            </tr>
//          ))}
//        </tbody>
//      </table>
//    </div>
//  );
//};
//
//export default RoomList;