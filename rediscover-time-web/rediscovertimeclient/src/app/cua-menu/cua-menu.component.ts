import {Component, OnInit} from '@angular/core';
import {QueueModel} from "../models/queue.model";
import {QueueService} from "../queue-service/queue-service.service";

@Component({
  selector: 'app-cua-menu',
  templateUrl: './cua-menu.component.html',
  styleUrls: ['./cua-menu.component.css']
})
export class CuaMenuComponent implements OnInit {

  allQueues: QueueModel[] = [];
  loading = 0;

  constructor(
    private queueService: QueueService
  ) {

  }

  ngOnInit() {
    this.loading--;
    this.queueService.getAllQueues().subscribe((queues : QueueModel[]) => {
      console.log("queues:")
      console.log(queues)

      this.allQueues = queues;

      console.log("this.allQueues:")
      console.log(this.allQueues)

      this.loading++;
    })


  }
}
