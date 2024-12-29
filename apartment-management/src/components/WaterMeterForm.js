
import React, { useState } from 'react';

const WaterMeterForm = () => {
    const [roomId, setRoomId] = useState('');
    const [currentMeter, setCurrentMeter] = useState('');
    const [pricePerUnit, setPricePerUnit] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        fetch('/api/water', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                roomId,
                currentMeter,
                pricePerUnit,
            }),
        })
            .then((res) => res.json())
            .then((data) => console.log(data));
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="number"
                placeholder="Room ID"
                value={roomId}
                onChange={(e) => setRoomId(e.target.value)}
            />
            <input
                type="number"
                placeholder="Current Meter"
                value={currentMeter}
                onChange={(e) => setCurrentMeter(e.target.value)}
            />
            <input
                type="number"
                placeholder="Price Per Unit"
                value={pricePerUnit}
                onChange={(e) => setPricePerUnit(e.target.value)}
            />
            <button type="submit">Submit</button>
        </form>
    );
};

export default WaterMeterForm;