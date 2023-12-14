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

  laMevaCua : ClientModel;
  trobat : boolean = false;
  loading = 0;

  idName : string;
  constructor(
    private queueService: QueueService
  ) {

  }
  formGroup : FormGroup;
  ngOnInit() {

    this.formGroup = new FormGroup({
      cardid: new FormControl(''),
    });
  }

  buscaLaMevaCua(){
    // this.laMevaCua = null;
    let tarja = this.formGroup.get('cardid')!.value;
    this.loading--;
    this.queueService.getClient(tarja).subscribe((queues : ClientModel) => {
      console.log("me:")
      this.trobat = true;
      console.log(queues)
      this.laMevaCua = queues;

      this.loading++;
    })
  }
}
