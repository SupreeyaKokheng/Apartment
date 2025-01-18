import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api'; // URL ของ backend

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
    // Authorization: `Bearer ${localStorage.getItem('token')}`  // ถ้ามีการใช้ JWT
  },
});

// Interceptor สำหรับใส่ Authorization header (ถ้าใช้ JWT)
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export const getRooms = async () => {
  const response = await api.get('/rooms');
  return response.data;
};

export const getWaterMeterRecords = async (roomId, month) => {
  const response = await api.get(`/meters/water/${roomId}?month=${month}`);
  return response.data;
};

export const getElectricMeterRecords = async (roomId, month) => {
  const response = await api.get(`/meters/electric/${roomId}?month=${month}`);
  return response.data;
};

export const saveWaterMeterRecord = async (roomId, meterValue, recordDate) => {
  const response = await api.post('/meters/water', { roomId, meterValue, recordDate });
  return response.data;
};

export const saveElectricMeterRecord = async (roomId, meterValue, recordDate) => {
  const response = await api.post('/meters/electric', { roomId, meterValue, recordDate });
  return response.data;
};

// ... เพิ่มฟังก์ชันอื่นๆ สำหรับเรียก API ตามที่ต้องการ