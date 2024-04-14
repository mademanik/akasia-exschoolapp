import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Extracurricular} from 'src/app/models/extracurricular.model';
import {environment} from 'src/environments/environment';
import {Response} from "../models/response.model";

@Injectable({
  providedIn: 'root',
})
export class ExtracurricularService {
  constructor(private _httpClient: HttpClient) {
  }

  private baseUrl = environment.baseUrl;

  createExtracurricular(jsonData: any) {
    const req = new HttpRequest(
      'POST',
      `${this.baseUrl}/api/extracurriculars`,
      jsonData,
      {
        reportProgress: true,
        responseType: 'json',
      }
    );

    return this._httpClient.request(req);
  }

  findAllExtracurriculars(): Observable<Response<Extracurricular[]>> {
    return this._httpClient.get<Response<Extracurricular[]>>(
      `${this.baseUrl}/api/extracurriculars`
    );
  }

  findExtracurricularById(id: any): Observable<Response<Extracurricular>> {
    return this._httpClient.get<Response<Extracurricular>>(
      `${this.baseUrl}/api/extracurriculars/${id}`
    );
  }

  deleteExtracurricularById(id: any): Observable<Response<Extracurricular>> {
    return this._httpClient.delete<Response<Extracurricular>>(
      `${this.baseUrl}/api/extracurriculars/${id}`
    );
  }

  updateExtracurricular(id: any, jsonData: any) {
    const req = new HttpRequest(
      'PUT',
      `${this.baseUrl}/api/extracurriculars/${id}`,
      jsonData,
      {
        reportProgress: true,
        responseType: 'json',
      }
    );

    return this._httpClient.request(req);
  }
}
