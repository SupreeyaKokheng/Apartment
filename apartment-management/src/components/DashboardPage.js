import React, { useState, useEffect } from 'react';
import axios from 'axios';

const DashboardPage = () => {
  const [rooms, setRooms] = useState([]);
  const [selectedMonth, setSelectedMonth] = useState(new Date().toISOString().slice(0, 7)); // yyyy-MM
  const [waterData, setWaterData] = useState({});
  const [electricData, setElectricData] = useState({}); // เพิ่ม state สำหรับ Electric

  useEffect(() => {
    const fetchRooms = async () => {
      const response = await axios.get('/api/rooms');
      setRooms(response.data);
    };

    fetchRooms();
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      const waterPromises = rooms.map((room) =>
        axios.get(`/api/water/${room.id}?month=${selectedMonth}`)
      );

      // เพิ่มการดึงข้อมูล Electric
      const electricPromises = rooms.map((room) =>
      axios.get(`/api/electric/${room.id}?month=${selectedMonth}`)
    );

      try {
        const waterResponses = await Promise.all(waterPromises);
        const newWaterData = {};
        waterResponses.forEach((response, index) => {
          newWaterData[rooms[index].id] = response.data;
        });
        setWaterData(newWaterData);

        // ดึงข้อมูลและอัปเดต state ของ Electric
        const electricResponses = await Promise.all(electricPromises);
        const newElectricData = {};
        electricResponses.forEach((response, index) => {
          newElectricData[rooms[index].id] = response.data;
        });
        setElectricData(newElectricData);

      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    if (rooms.length > 0) {
      fetchData();
    }
  }, [rooms, selectedMonth]);

  const handleMonthChange = (event) => {
    setSelectedMonth(event.target.value);
  };

  return (
    <div>
      <h1>Apartment Dashboard</h1>
      <div>
        <label htmlFor="month">Select Month:</label>
        <input
          type="month"
          id="month"
          value={selectedMonth}
          onChange={handleMonthChange}
        />
      </div>
      <table>
        <thead>
          <tr>
            <th>Room Number</th>
            <th>Water Meter ({selectedMonth})</th>
             <th>Electric Meter ({selectedMonth})</th>
          </tr>
        </thead>
        <tbody>
          {rooms.map((room) => (
            <tr key={room.id}>
              <td>{room.roomNumber}</td>
              <td>
                {waterData[room.id] && waterData[room.id].length > 0
                  ? waterData[room.id][0].meterValue // สมมติว่าเอาค่าล่าสุด
                  : 'N/A'}
              </td>
              <td>
                {electricData[room.id] && electricData[room.id].length > 0
                  ? electricData[room.id][0].meterValue // สมมติว่าเอาค่าล่าสุด
                  : 'N/A'}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default DashboardPage;