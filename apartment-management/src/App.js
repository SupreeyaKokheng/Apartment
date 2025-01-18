import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navigation from './components/Navigation';
import DashboardPage from './components/DashboardPage';
import WaterMeterPage from './components/WaterMeterPage';

import './App.css'; // Import CSS file

const App = () => {
  return (
    <Router>
      <div className="app-container">
        <Navigation />
        <div className="content">
          <Routes>
            <Route path="/" element={<DashboardPage />} />
            <Route path="/water-meter" element={<WaterMeterPage />} />

          </Routes>
        </div>
      </div>
    </Router>
  );
};

export default App;