import {Component, OnInit} from '@angular/core';
import {QueueModel} from "../models/queue.model";
import {QueueService} from "../queue-service/queue-service.service";
import {ClientModel} from "../models/client.model";
import {FormControl, FormGroup} from "@angular/forms";
import {pipe} from "rxjs";

@Component({
  selector: 'app-cua-menu',
  templateUrl: './cua-menu.component.html',
  styleUrls: ['./cua-menu.component.css']
})
export class CuaMenuComponent implements OnInit {

  allQueues: QueueModel[] = [];

  elMeuClient: ClientModel;
  laMevaCua: QueueModel;
  trobat: boolean = false;
  loading = 0;

  tarja: string;
  idName: string;

  constructor(
    private queueService: QueueService
  ) {

  }

  formGroup: FormGroup;

  ngOnInit() {

    this.formGroup = new FormGroup({
      cardid: new FormControl(''),
    });
  }

  buscaLaMevaCua() {
    // this.laMevaCua = null;
    this.tarja = this.formGroup.get('cardid')!.value;
    if (this.tarja.length !== 0) {
      this.loading--;
      this.queueService.getClient(this.tarja).subscribe((queues: any) => {
        if (queues === null) {
          this.trobat = false;
        } else {
          console.log("me:")
          this.trobat = true;
          console.log(queues)
          this.elMeuClient = queues;
          this.laMevaCua = this.elMeuClient.queueof;
        }

        this.loading++;
      })
    }
  }
}
