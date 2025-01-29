import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private baseUrl = 'http://localhost:8080/api/meters';

  constructor(private http: HttpClient) {}

  // บันทึกข้อมูลมิเตอร์น้ำ
  saveWaterMeter(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/water`, data);
  }

  // บันทึกข้อมูลมิเตอร์ไฟฟ้า
  saveElectricMeter(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/electric`, data);
  }

  // ดึงข้อมูลสรุป
  getSummary(): Observable<any> {
    return this.http.get(`${this.baseUrl}/summary`);
  }

  // ดึงข้อมูลบิล
  getBilling(): Observable<any> {
    return this.http.get(`${this.baseUrl}/billing`);
  }
}

// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable } from 'rxjs';

// @Injectable({
//   providedIn: 'root'
// })
// export class ApiService {
//   private baseUrl = 'http://localhost:8080/api';

//   constructor(private http: HttpClient) {}

//   saveWaterMeterData(data: any): Observable<any> {
//     return this.http.post(`${this.baseUrl}/water-meter`, data);
//   }

//   saveElectricMeterData(data: any): Observable<any> {
//     return this.http.post(`${this.baseUrl}/electric-meter`, data);
//   }

//   getSummary(): Observable<any> {
//     return this.http.get(`${this.baseUrl}/summary`);
//   }

//   getBilling(): Observable<any> {
//     return this.http.get(`${this.baseUrl}/billing`);
//   }
// }
