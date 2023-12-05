import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {QueueModel} from "../models/queue.model";

@Injectable({
  providedIn: 'root'
})


export class QueueService {

  urlQueue : string = '/api/queues'
  constructor(
    private http : HttpClient
  ){ }

  getAllQueues() : Observable<QueueModel[]> {
    return this.http.get<QueueModel[]>(`${this.urlQueue}/all`);
  }

  getQueue(id : string){
    return this.http.get<QueueModel>(`${this.urlQueue}/${id}`);
  }
}


