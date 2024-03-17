import { Component, inject } from '@angular/core';
import * as worldMapData from '../worldMap.json';
import { Maps, Marker } from '@syncfusion/ej2-angular-maps';
import { ChartService } from '../service/chart.service';

Maps.Inject(Marker);

@Component({
  selector: 'app-district-density-map',
  templateUrl: './district-density-map.component.html',
  styleUrls: ['./district-density-map.component.scss']
})
export class DistrictDensityMapComponent {

  chartService: ChartService = inject(ChartService);
  markerData: Object[] = [];

  ngOnInit() {
    this.chartService.getUserMapData().subscribe((data) => {
      this.markerData = data;
      this.renderMap();
    });
  }

  renderMap(): void {
    const maps = new Maps({
      layers: [
        {
          shapeData: worldMapData,
          shapeSettings: {
            fill: '#E5E5E5'
          },
          markerSettings: [
            {
              visible: true,
              dataSource: this.markerData,
              shapeValuePath: 'shape',
              colorValuePath: 'color',
              fill: 'White',
              border: { width: 2, color: '#285255' },
              height: 15,
              width: 15,
              tooltipSettings: {
                visible: true,
                valuePath: 'population',
                template: '<div>${name}</div><div>No.of users: ${population}</div>',
              }
            }
          ]
        },
      ],
    });
    maps.appendTo('#district-density-map');
  }
}
