import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import WaterMeterForm from './components/WaterMeterForm';
import RoomList from './components/RoomList';
import Login from "./components/Login";
//import Welcome from "./Welcome";
import Navigation from './components/Navigation';
import './App.css';

function App() {
    return (
    <div className = "App">
    <header className = "App-header">
        <Router>
         <Navigation />
            <div className="content">
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/RoomList" element={<RoomList />} />
                <Route path="/WaterMeterForm" element={<WaterMeterForm />} />

            </Routes>
             </div>

        </Router>
           </header>
           </div>
    );
}

export default App;
