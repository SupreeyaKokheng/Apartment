import React from 'react';
import './DatePicker.css'; // Import CSS file

const DatePicker = ({ value, onChange }) => {
  const handleDateChange = (event) => {
    onChange(event.target.value);
  };

  return (
    <input
      type="date"
      className="date-picker"
      value={value}
      onChange={handleDateChange}
    />
  );
};

export default DatePicker;