import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Notification} from 'src/app/models/notification.model';
import {environment} from 'src/environments/environment';
import {Response} from "../models/response.model";

@Injectable({
  providedIn: 'root',
})
export class NotificationService {
  constructor(private _httpClient: HttpClient) {
  }

  private baseUrl = environment.baseUrl;

  findAllNotifications(): Observable<Response<Notification[]>> {
    return this._httpClient.get<Response<Notification[]>>(
      `${this.baseUrl}/api/notifications`
    );
  }
}
