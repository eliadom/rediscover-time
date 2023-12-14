import { Component, OnInit, Inject } from '@angular/core';
import {QueueModel} from "../../../models/queue.model";
import {FormControl, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {QueueService} from "../../../queue-service/queue-service.service";

@Component({
  selector: 'app-confirm-queue-dialog',
  templateUrl: './confirm-queue-dialog.component.html',
  styleUrls: ['./confirm-queue-dialog.component.css']
})
export class ConfirmQueueDialogComponent implements  OnInit {
  queue : QueueModel;

  formGroup : FormGroup;

  loading : number = 0;

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: any,
    private queueService : QueueService,
    private dialogRef: MatDialogRef<ConfirmQueueDialogComponent>) {
    if (data) { //
      this.queue = data.queue;
    }
  }

  ngOnInit() {
    this.formGroup = new FormGroup({
      cardid: new FormControl(''),
    });
  }

  onConfirmClick(): void {
    this.dialogRef.close();
  }

  test : string = "";
  apuntaCua(){
    let tarja = this.formGroup.get('cardid')!.value;
    this.loading--;
    this.queueService.apunta(tarja,this.queue.id).subscribe((res : any) => {
      this.loading++;
      this.dialogRef.close({resultat : res});
    })
  }
}
