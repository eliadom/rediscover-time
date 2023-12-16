import {Component, OnInit} from '@angular/core';
import {QueueModel} from "../models/queue.model";
import {QueueService} from "../queue-service/queue-service.service";

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent implements OnInit {

  allQueues: QueueModel[] = [];
  loading = 0;

  constructor(
    private queueService: QueueService
  ) {

  }

  ngOnInit() {
    // this.loading--;
    // this.queueService.getAllQueues().subscribe((queues : QueueModel[]) => {
    //   console.log("queues:")
    //   console.log(queues)
    //   // TODO: ordenar cues segons temps espera actual
    //
    //   this.allQueues = queues;
    //
    //   console.log("this.allQueues:")
    //   console.log(this.allQueues)
    //
    //   this.loading++;
    // })

    setInterval(() => {
      console.log("This message will display every second");
      this.queueService.getAllQueues().subscribe((queues : QueueModel[]) => {

        this.allQueues = queues;
        this.allQueues.sort((a,b) => a.timeForNextTrain < b.timeForNextTrain ? -1 : 1);

        console.log(this.allQueues)

      })
    }, 1000);


  }
}
