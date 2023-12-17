import {Component, Input, OnInit} from '@angular/core';
import {QueueModel} from "../../models/queue.model";
import {QueueService} from "../../queue-service/queue-service.service";
import {MatDialog} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
  selector: 'app-small-view-client',
  templateUrl: './small-view-client.component.html',
  styleUrls: ['./small-view-client.component.css']
})
export class SmallViewClientComponent implements OnInit {

  apuntat: boolean = false;

  @Input()
  queue: any;


  @Input()
  client : any;

  @Input()
  personal: boolean = false;

  constructor(
    private queueService: QueueService,
    private _snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {

  }

  ngOnInit() {

  }



}
