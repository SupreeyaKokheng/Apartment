import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { ApiService } from '../../shared/api.service';

@Component({
  selector: 'app-electric-meter',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './electric-meter.component.html',
  styleUrls: ['./electric-meter.component.css']
})
export class ElectricMeterComponent implements OnInit {
  electricMeterForm: FormGroup;
  rooms = Array.from({ length: 145 }, (_, i) => `Room ${i + 1}`);
  today = new Date().toLocaleDateString();

  constructor(private fb: FormBuilder, private apiService: ApiService) {
    this.electricMeterForm = this.fb.group(
      this.rooms.reduce((acc: { [key: string]: any }, _, i) => {
        acc[`room${i}`] = [''];
        return acc;
      }, {})
    );
  }

  ngOnInit(): void {}

  nextField(index: number): void {
    const inputs = document.querySelectorAll('input');
    if (inputs[index + 1]) {
      (inputs[index + 1] as HTMLElement).focus();
    }
  }

  submitElectricMeter(): void {
    const data = this.electricMeterForm.value;
    this.apiService.saveElectricMeterData(data).subscribe(response => {
      alert('บันทึกสำเร็จ!');
    });
  }
}
