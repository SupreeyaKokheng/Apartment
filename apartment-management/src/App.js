import logo from './logo.svg';
import './App.css';
import WaterMeterForm from './components/WaterMeterForm';
import RoomList from './components/RoomList';
function App() {
  return (
    <div className="App">
      <header className="App-header">
      <RoomList/>
        //<WaterMeterForm />
      </header>
    </div>
  );
}

export default App;
