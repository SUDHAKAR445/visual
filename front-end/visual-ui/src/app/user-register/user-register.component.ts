import { Component } from '@angular/core';
import { Chart, registerables } from 'node_modules/chart.js';
import { ChartData } from '../model/chart-data.model';
import { ChartService } from '../service/chart.service';
Chart.register(...registerables);

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.scss']
})
export class UserRegisterComponent {

  myChart!: Chart;
  years: number[] = [];
  chartData: ChartData = {
    JANUARY: 0,
    FEBRUARY: 0,
    MARCH: 0,
    APRIL: 0,
    MAY: 0,
    JUNE: 0,
    JULY: 0,
    AUGUST: 0,
    SEPTEMBER: 0,
    OCTOBER: 0,
    NOVEMBER: 0,
    DECEMBER: 0
  };
  deletedUser: ChartData = {
    JANUARY: 0,
    FEBRUARY: 0,
    MARCH: 0,
    APRIL: 0,
    MAY: 0,
    JUNE: 0,
    JULY: 0,
    AUGUST: 0,
    SEPTEMBER: 0,
    OCTOBER: 0,
    NOVEMBER: 0,
    DECEMBER: 0
  };

  constructor(private chartService: ChartService) {
    for (let i = new Date().getFullYear(); i > 2000; i--) {
      this.years.push(i);
    }
  }

  ngOnInit() {
    this.loadData(new Date().getFullYear());
  }
  loadData(year: number) {
    this.chartService.getUserRegister(year).subscribe((data) => {
      this.chartData = data;
      this.chartService.getDeleteUser(year).subscribe((data) => {
        this.deletedUser = data;
        this.createChart();
      });
    });
  }

  createChart(): void {
    const labels = Object.keys(this.chartData);
    const registeredData = Object.values(this.chartData);
    const deletedData = Object.values(this.deletedUser);

    const userChart = document.getElementById('user-register-barchart') as HTMLCanvasElement;

    if (this.myChart) {
      this.myChart.destroy();
    }

    this.myChart = new Chart(userChart, {
      type: 'bar',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'User Registrations',
            data: registeredData,
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1
          },
          {
            label: 'Deleted Users',
            data: deletedData,
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

  onYearChange(event: any){
    this.loadData(event.target.value);
  }
}
