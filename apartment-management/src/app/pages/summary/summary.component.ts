import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { ApiService } from '../../shared/api.service';

@Component({
  selector: 'app-summary',
  standalone: true,
  imports: [CommonModule, MatTableModule],
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css']
})
export class SummaryComponent implements OnInit {
  summaryData: any[] = [];
  displayedColumns: string[] = ['room', 'water', 'electric', 'total'];

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.apiService.getSummary().subscribe(data => {
      this.summaryData = data;
    });
  }
}
