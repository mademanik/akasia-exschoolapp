import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ExtracurricularService} from 'src/app/services/extracurricular.service';
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-show-extracurricular',
  templateUrl: './show-extracurricular.component.html',
  styleUrls: ['./show-extracurricular.component.scss']
})
export class ShowExtracurricularComponent implements OnInit {
  constructor(
    @Inject(MAT_DIALOG_DATA)
    public data: { id: any },
    private extracurricularService: ExtracurricularService,
    private datePipe: DatePipe,
    private dialogRef: MatDialogRef<ShowExtracurricularComponent>
  ) {
  }

  row: any;

  ngOnInit(): void {
    this.findExtracurricularById(this.data.id);
  }

  findExtracurricularById(id: String) {
    this.extracurricularService.findExtracurricularById(id).subscribe({
      next: (res) => {
        this.row = res.data;
      },
      error: (err) => {
        alert(err);
      },
    });
  }

  convertDate(date: string): string {
    const parsedDate = new Date(date);

    return this.datePipe.transform(parsedDate, 'dd-MM-yyyy HH-mm-ss') || '';
  }
}


