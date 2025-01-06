import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import WaterMeterForm from './components/WaterMeterForm';
import RoomList from './components/RoomList';
import Login from "./components/Login";
//import Welcome from "./Welcome";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/RoomList" element={<RoomList />} />

            </Routes>
        </Router>
    );
}

export default App;
//import logo from './logo.svg';

//import './App.css';
//import WaterMeterForm from './components/WaterMeterForm';
//import RoomList from './components/RoomList';
//function App() {
//  return (
//    <div className="App">
//      <header className="App-header">
//      <RoomList/>
//        //<WaterMeterForm />
//      </header>
//    </div>
//  );
//}
//
//export default App;
