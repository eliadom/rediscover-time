import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {QueueModel} from "../models/queue.model";
import {ClientModel} from "../models/client.model";

@Injectable({
  providedIn: 'root'
})


export class QueueService {

  urlQueue : string = '/api/queues'
  urlClient : string = '/api/clients'
  constructor(
    private http : HttpClient
  ){ }

  apunta(client : string, atraccio : string){
    return this.http.post<any>(`${this.urlClient}/apunta`, {
      id : client ,
      queueof : {
        id : atraccio
      }
    })
  }

  getClient(id : string){
    return this.http.get<ClientModel>(`${this.urlClient}/${id}`);
  }

  getAllQueues() : Observable<QueueModel[]> {
    return this.http.get<QueueModel[]>(`${this.urlQueue}/all`);
  }

  getQueue(id : string){
    return this.http.get<QueueModel>(`${this.urlQueue}/${id}`);
  }
}


