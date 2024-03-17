import { Component, OnInit } from '@angular/core';
import { ChartService } from '../service/chart.service';
import { Chart } from 'chart.js';

@Component({
  selector: 'app-district-vice',
  templateUrl: './district-vice.component.html',
  styleUrls: ['./district-vice.component.scss']
})
export class DistrictViceComponent implements OnInit {

  chartData: Map<string, number> = new Map();
  colors: string[] = [];

  constructor(private chartService: ChartService) {}

  ngOnInit() {
    this.chartService.getUserDistrictVice().subscribe((apiData) => {
      this.chartData = new Map(Object.entries(apiData));

      const data = {
        labels: Array.from(this.chartData.keys()),
        datasets: [
          {
            data: Array.from(this.chartData.values()),
            backgroundColor: this.generateColors(this.chartData.size),
          },
        ],
      };

      const ctx = document.getElementById('user-register-pieChart') as HTMLCanvasElement;
      new Chart(ctx, {
        type: 'pie',
        data: data,
      });
    });
  }

  generateColors(count: number): string[] {
    for (let i = 0; i < count; i++) {
      const randomColor = '#' + Math.floor(Math.random() * 16777215).toString(16);
      this.colors.push(randomColor);
    }

    return this.colors;
  }
}
