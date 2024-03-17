import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserRegisterComponent } from './user-register/user-register.component';
import { DistrictViceComponent } from './district-vice/district-vice.component';
import { DistrictDensityMapComponent } from './district-density-map/district-density-map.component';
import { MapsModule, MapsTooltipService } from '@syncfusion/ej2-angular-maps';
import { MonthRegisterComponent } from './month-register/month-register.component';

@NgModule({
  declarations: [
    AppComponent,
    UserRegisterComponent,
    DistrictViceComponent,
    DistrictDensityMapComponent,
    MonthRegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MapsModule,
  ],
  providers: [MapsTooltipService],
  bootstrap: [AppComponent]
})
export class AppModule { }
