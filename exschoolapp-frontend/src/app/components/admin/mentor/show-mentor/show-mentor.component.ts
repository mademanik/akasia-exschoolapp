import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {MentorService} from 'src/app/services/mentor.service';

@Component({
  selector: 'app-show-mentor',
  templateUrl: './show-mentor.component.html',
  styleUrls: ['./show-mentor.component.scss']
})
export class ShowMentorComponent implements OnInit {
  constructor(
    @Inject(MAT_DIALOG_DATA)
    public data: { id: any },
    private mentorService: MentorService,
    private dialogRef: MatDialogRef<ShowMentorComponent>
  ) {
  }

  row: any;

  ngOnInit(): void {
    this.findMentorById(this.data.id);
  }

  findMentorById(id: String) {
    this.mentorService.findMentorById(id).subscribe({
      next: (res) => {
        this.row = res.data;
      },
      error: (err) => {
        alert(err);
      },
    });
  }
}

