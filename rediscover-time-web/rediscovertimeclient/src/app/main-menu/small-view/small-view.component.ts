import {Component, Input, OnInit} from '@angular/core';
import {QueueModel} from "../../models/queue.model";
import {QueueService} from "../../queue-service/queue-service.service";


@Component({
  selector: 'app-small-view',
  templateUrl: './small-view.component.html',
  styleUrls: ['./small-view.component.css']
})
export class SmallViewComponent implements OnInit {


  @Input()
  queue : any;

  constructor(
    private queueService: QueueService
  ) {

  }

  ngOnInit() {
  }


}
