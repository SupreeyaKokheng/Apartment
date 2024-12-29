import React, { useEffect, useRef, useState } from "react";
import axios from "axios";

const RoomList = () => {
  const [rooms, setRooms] = useState([]);
  const [meterValues, setMeterValues] = useState({});
  const inputRefs = useRef([]); // สร้าง Array ของ Ref สำหรับ input

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

  const handleSubmit = (roomId) => {
    const meterValue = meterValues[roomId];
    axios
      .post("/api/rooms/meter", { roomId, meterValue })
      .catch((error) => {
        console.error(error); // เก็บ Error Logging ไว้สำหรับ Debugging
      });
  };

  return (
    <div>
      <h1>Room List</h1>
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
