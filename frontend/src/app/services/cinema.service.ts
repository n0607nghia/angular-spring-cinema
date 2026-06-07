import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {routesConfig} from '../core/config/routes.config';
import { ScreeningModel } from '../models/screening-model';

@Injectable({
  providedIn: 'root'
})
export class CinemaService {
  constructor(private http: HttpClient) { }

  fetchScreens(): Observable<ScreeningModel[]> {
    return this.http.get<ScreeningModel[]>(routesConfig.apiBaseUrl + routesConfig.fetchScreensUrl);
  }

  createScreens(): Observable<any> {
    return this.http.post(routesConfig.apiBaseUrl + routesConfig.postScreenUrl, {});
  }
}
