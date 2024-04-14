import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Mentor} from 'src/app/models/mentor.model';
import {environment} from 'src/environments/environment';
import {Response} from "../models/response.model";

@Injectable({
  providedIn: 'root',
})
export class MentorService {
  constructor(private _httpClient: HttpClient) {
  }

  private baseUrl = environment.baseUrl;

  createMentor(jsonData: any) {
    const req = new HttpRequest(
      'POST',
      `${this.baseUrl}/api/mentors`,
      jsonData,
      {
        reportProgress: true,
        responseType: 'json',
      }
    );

    return this._httpClient.request(req);
  }

  findAllMentors(): Observable<Response<Mentor[]>> {
    return this._httpClient.get<Response<Mentor[]>>(
      `${this.baseUrl}/api/mentors`
    );
  }

  findMentorById(id: any): Observable<Response<Mentor>> {
    return this._httpClient.get<Response<Mentor>>(
      `${this.baseUrl}/api/mentors/${id}`
    );
  }

  deleteMentorById(id: any): Observable<Response<Mentor>> {
    return this._httpClient.delete<Response<Mentor>>(
      `${this.baseUrl}/api/mentors/${id}`
    );
  }

  updateMentor(id: any, jsonData: any) {
    const req = new HttpRequest(
      'PUT',
      `${this.baseUrl}/api/mentors/${id}`,
      jsonData,
      {
        reportProgress: true,
        responseType: 'json',
      }
    );

    return this._httpClient.request(req);
  }
}
