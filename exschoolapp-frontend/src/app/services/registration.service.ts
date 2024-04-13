import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Registration} from 'src/app/models/registration.model';
import {environment} from 'src/environments/environment';
import {Response} from "../models/response.model";

@Injectable({
  providedIn: 'root',
})
export class RegistrationService {
  constructor(private _httpClient: HttpClient) {
  }

  private baseUrl = environment.baseUrl;

  createRegistration(jsonData: any) {
    const req = new HttpRequest(
      'POST',
      `${this.baseUrl}/api/registrations`,
      jsonData,
      {
        reportProgress: true,
        responseType: 'json',
      }
    );

    return this._httpClient.request(req);
  }

  findAllRegistrations(): Observable<Response<Registration[]>> {
    return this._httpClient.get<Response<Registration[]>>(
      `${this.baseUrl}/api/registrations`
    );
  }
}
