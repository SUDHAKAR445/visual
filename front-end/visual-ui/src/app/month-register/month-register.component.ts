import { Component, OnInit, Inject } from '@angular/core';
import { Chart } from 'chart.js';
import { ChartService } from '../service/chart.service';
import { MonthData } from '../model/chart-data.model';

@Component({
  selector: 'app-month-register',
  templateUrl: './month-register.component.html',
  styleUrls: ['./month-register.component.scss']
})
export class MonthRegisterComponent implements OnInit {

  myChart!: Chart;
  years: number[] = [];
  months: string[] = ['JANUARY', 'FEBRUARY', 'MARCH', 'APRIL', 'MAY', 'JUNE', 'JULY', 'AUGUST', 'SEPTEMBER', 'OCTOBER', 'NOVEMBER', 'DECEMBER']

  month: string = new Date().toLocaleString('default', { month: 'long' });
  year: number = new Date().getFullYear();
  constructor(private chartService: ChartService) {
    for (let i = new Date().getFullYear(); i > 2000; i--) {
      this.years.push(i);
    }
  }

  chartData: { labels: string[], counts: number[] } = { labels: [], counts: [] };
  deleteUserData: { labels: string[], counts: number[] } = { labels: [], counts: [] };

  ngOnInit() {
    this.loadData(this.month, this.year);
  }

  loadData(month: string, year: number) {
    this.chartService.getUserByMonth(month, year).subscribe((data: any) => {
      this.chartData = data;
      this.chartService.getDeleteUserByMonth(month, year).subscribe((data: any) => {
        this.deleteUserData = data;
        this.createChart();
      });
    });
  }

  createChart(): void {
    const labels: string[] = Object.keys(this.chartData);
    const counts: number[] = Object.values(this.chartData).map(value => Number(value));
    const deleteUserCounts: number[] = Object.values(this.deleteUserData).map(value => Number(value));

    const userChart = document.getElementById('user-register-lineChart') as HTMLCanvasElement;

    if (this.myChart) {
      this.myChart.destroy();
    }

    this.myChart = new Chart(userChart, {
      type: 'line',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'User Registrations',
            data: counts,
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1
          },
          {
            label: 'Deleted Users',
            data: deleteUserCounts,
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderColor: 'rgba(255, 99, 132, 1)',
            borderWidth: 1
          }
        ]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }

  onYearChange(event: any) {
    this.year = event.target.value;
    this.loadData(this.month, this.year);
  }

  onMonthChange(event: any) {
    this.month = event.target.value;
    this.loadData(this.month, this.year);
  }
}
