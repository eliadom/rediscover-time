import { Component, OnInit, Inject } from '@angular/core';
import { VERSION, MatDialogRef, MatDialog, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import {QueueModel} from "../../../models/queue.model";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-confirm-queue-dialog',
  templateUrl: './confirm-queue-dialog.component.html',
  styleUrls: ['./confirm-queue-dialog.component.css']
})
export class ConfirmQueueDialogComponent implements  OnInit {
  queue : QueueModel;

  formGroup : FormGroup;

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: any,
    private dialogRef: MatDialogRef<ConfirmQueueDialogComponent>) {
    if (data) { //
      this.queue = data.queue;
    }
  }

  ngOnInit() {
    this.formGroup = new FormGroup({
      cardId: new FormControl(''),
    });
  }

  onConfirmClick(): void {
    this.dialogRef.close();
  }

}
