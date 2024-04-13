import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Student} from 'src/app/models/student.model';
import {environment} from 'src/environments/environment';
import {Response} from "../models/response.model";

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  constructor(private _httpClient: HttpClient) {
  }

  private baseUrl = environment.baseUrl;

  createStudent(jsonData: any) {
    const req = new HttpRequest(
      'POST',
      `${this.baseUrl}/api/students`,
      jsonData,
      {
        reportProgress: true,
        responseType: 'json',
      }
    );

    return this._httpClient.request(req);
  }

  findAllStudents(): Observable<Response<Student[]>> {
    return this._httpClient.get<Response<Student[]>>(
      `${this.baseUrl}/api/students`
    );
  }
}
