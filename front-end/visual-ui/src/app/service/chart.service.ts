import { Injectable, inject } from "@angular/core";
import { Observable } from "rxjs";
import { ChartData, MonthData } from "../model/chart-data.model";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/environments/environment.development";

@Injectable({
    providedIn: "root",
})

export class ChartService {

    http: HttpClient = inject(HttpClient)
    getUserRegister(inputYear: number): Observable<ChartData> {
        return this.http.get<ChartData>(`${environment.userRegisterUrl}/register/${inputYear}`);
    }

    getDeleteUser(inputYear: number): Observable<ChartData> {
        return this.http.get<ChartData>(`${environment.userRegisterUrl}/delete/${inputYear}`);
    }

    getUserDistrictVice(): Observable<Map<string, number>> {
        return this.http.get<Map<string, number>>(`${environment.userRegisterUrl}/district`);
    }

    getUserMapData(): Observable<any> {
        return this.http.get<any>(`${environment.userRegisterUrl}`);
    }

    getUserByMonth(month: string, year: number): Observable<any> {
        return this.http.get<any>(`${environment.userRegisterUrl}/month/${month}/${year}`);
    }

    getDeleteUserByMonth(month: string, year: number): Observable<any> {
        return this.http.get<any>(`${environment.userRegisterUrl}/delete/${month}/${year}`);
    }
}