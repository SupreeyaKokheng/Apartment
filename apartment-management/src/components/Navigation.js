import React from 'react';
import { Link } from 'react-router-dom';
import "./Navigation.css";

const Navigation = () => {
  return (
    <div className="sidebar">
      <Link to="/RoomList" className="nav-button">
              🏠 Room List
            </Link>
            <Link to="/WaterMeterForm" className="nav-button">
              💧 Water Meter
            </Link>
    </div>
  );
};

export default Navigation;
