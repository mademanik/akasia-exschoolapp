import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {RegistrationService} from 'src/app/services/registration.service';
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-show-registration',
  templateUrl: './show-registration.component.html',
  styleUrls: ['./show-registration.component.scss']
})
export class ShowRegistrationComponent implements OnInit {
  constructor(
    @Inject(MAT_DIALOG_DATA)
    public data: { id: any },
    private registrationService: RegistrationService,
    private datePipe: DatePipe,
    private dialogRef: MatDialogRef<ShowRegistrationComponent>
  ) {
  }

  row: any;

  ngOnInit(): void {
    this.findRegistrationById(this.data.id);
  }

  findRegistrationById(id: String) {
    this.registrationService.findRegistrationById(id).subscribe({
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

