import React, { useState, useEffect } from 'react';
import { getRooms, saveWaterMeterRecord } from '../services/api';
import { Button, Table, DatePicker, Input } from './common';
import './WaterMeterPage.css'; // Import CSS file

const WaterMeterPage = () => {
  const [rooms, setRooms] = useState([]);
  const [selectedRoom, setSelectedRoom] = useState(null);
  const [meterValue, setMeterValue] = useState('');
  const [recordDate, setRecordDate] = useState(new Date().toISOString().slice(0, 10));
  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  useEffect(() => {
    const fetchRooms = async () => {
      const fetchedRooms = await getRooms();
      setRooms(fetchedRooms);
      if (fetchedRooms.length > 0) {
        setSelectedRoom(fetchedRooms[0].id); // เลือกห้องแรกเป็นค่าเริ่มต้น
      }
    };

    fetchRooms();
  }, []);

  const handleRoomChange = (event) => {
    setSelectedRoom(event.target.value);
  };

  const handleMeterValueChange = (event) => {
    setMeterValue(event.target.value);
  };

  const handleRecordDateChange = (date) => {
    setRecordDate(date);
  };

  const handleSubmit = async () => {
    setSuccessMessage('');
    setErrorMessage('');
    try {
      await saveWaterMeterRecord(selectedRoom, meterValue, recordDate);
      setSuccessMessage('Water meter record saved successfully!');
      setMeterValue('');
    } catch (error) {
      setErrorMessage('Failed to save water meter record.');
      console.error('Error saving water meter record:', error);
    }
  };

  return (
    <div className="water-meter-container">
      <h1 className="water-meter-title">Water Meter</h1>
      <div className="water-meter-form">
        <div className="form-group">
          <label htmlFor="room">Room:</label>
          <select id="room" value={selectedRoom} onChange={handleRoomChange}>
            {rooms.map((room) => (
              <option key={room.id} value={room.id}>
                {room.roomNumber}
              </option>
            ))}
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="meterValue">Meter Value:</label>
          <Input
            type="number"
            id="meterValue"
            value={meterValue}
            onChange={handleMeterValueChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="recordDate">Record Date:</label>
          <DatePicker value={recordDate} onChange={handleRecordDateChange} />
        </div>
        <Button onClick={handleSubmit}>Save</Button>
      </div>

      {successMessage && <div className="success-message">{successMessage}</div>}
      {errorMessage && <div className="error-message">{errorMessage}</div>}
    </div>
  );
};

export default WaterMeterPage;