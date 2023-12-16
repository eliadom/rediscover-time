import {Component, Input, OnInit} from '@angular/core';
import {QueueModel} from "../../models/queue.model";
import {QueueService} from "../../queue-service/queue-service.service";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmQueueDialogComponent} from "./confirm-queue-dialog/confirm-queue-dialog.component";
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
  selector: 'app-small-view',
  templateUrl: './small-view.component.html',
  styleUrls: ['./small-view.component.css']
})
export class SmallViewComponent implements OnInit {

  apuntat: boolean = false;

  @Input()
  queue: any;

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

  apunta() {
    // this.queueService.apunta().subscribe()
    const dialogRef = this.dialog.open(ConfirmQueueDialogComponent, {
      data: {queue: this.queue},
    });

    dialogRef.afterClosed().subscribe((result: any) => {
      this.apuntat = true;
      if (result != null) {
        if (result.resultat === null) {
          this._snackBar.open("Ja estàs apuntat a una atracció.", "OK")
        } else {
          this._snackBar.open("Apuntat a la cua correctament!", "OK")

        }
      }
    });
  }

}
