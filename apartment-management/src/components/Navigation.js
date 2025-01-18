import React from 'react';
import { Link } from 'react-router-dom';
import "./Navigation.css";

const Navigation = () => {
  return (
    <div className="sidebar">
      <Link to="/dashboard" className="nav-button">
        🏠 Dashboard
      </Link>
      <Link to="/water-meter" className="nav-button">
        💧 Water Meter
      </Link>
      {/* เพิ่ม Link สำหรับ Electric Meter ถ้ามี */}
      <Link to="/electric-meter" className="nav-button">
        ⚡️ Electric Meter
      </Link>
    </div>
  );
};

export default Navigation;