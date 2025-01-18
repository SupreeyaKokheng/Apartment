import React, { useEffect, useRef, useState } from "react";
import axios from "axios";

const RoomList = () => {
  const [rooms, setRooms] = useState([]);
  const [meterValues, setMeterValues] = useState({});
  const inputRefs = useRef([]);

  // ดึงข้อมูลห้อง
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

  const handleSubmit = (roomId) => {
    const meterValue = meterValues[roomId];
    const recordDate = new Date().toISOString().split("T")[0];

    axios
      .post("/api/water", { roomId, meterValue, recordDate })
      .catch((error) => {
        console.error(error);
      });
  };

  return (
    <div>
      <h1>Water Meter</h1>
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