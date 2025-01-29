import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ApiService } from '../../shared/api.service';

@Component({
  selector: 'app-water-meter',
  templateUrl: './water-meter.component.html',
  styleUrls: ['./water-meter.component.css']
})
export class WaterMeterComponent implements OnInit {
  waterMeterForm: FormGroup;
  rooms: string[] = Array.from({ length: 145 }, (_, i) => `ห้อง ${i + 1}`);
  today: string = new Date().toLocaleDateString('th-TH', {
    year: 'numeric',
    month: 'long',
    day: 'numeric'
  });

  constructor(private fb: FormBuilder, private apiService: ApiService) {
    // สร้างฟอร์มสำหรับแต่ละห้อง
    this.waterMeterForm = this.fb.group(
      this.rooms.reduce((acc, _, i) => {
        acc[`room${i + 1}`] = [''];
        return acc;
      }, {})
    );
  }

  ngOnInit(): void {}

  // ฟังก์ชันสำหรับเลื่อน focus ไปที่ input ถัดไป
  focusNextField(index: number): void {
    const inputs = document.querySelectorAll<HTMLInputElement>('.room-input');
    if (inputs[index + 1]) {
      inputs[index + 1].focus();
    }
  }

  // ฟังก์ชันสำหรับส่งข้อมูล
  submitData(): void {
    const formData = this.waterMeterForm.value;
    this.apiService.saveWaterMeter(formData).subscribe(
      () => {
        alert('บันทึกสำเร็จ');
        this.waterMeterForm.reset(); // ล้างฟอร์มหลังจากบันทึกสำเร็จ
      },
      (error) => {
        alert('เกิดข้อผิดพลาด: ' + error.message);
      }
    );
  }
}
