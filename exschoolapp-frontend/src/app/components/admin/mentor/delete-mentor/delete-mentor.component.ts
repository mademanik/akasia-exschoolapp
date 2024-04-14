import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {MentorService} from 'src/app/services/mentor.service';

@Component({
  selector: 'app-delete-mentor',
  templateUrl: './delete-mentor.component.html',
  styleUrls: ['./delete-mentor.component.scss']
})
export class DeleteMentorComponent {
  id: String = '';

  constructor(
    @Inject(MAT_DIALOG_DATA)
    public data: { id: any },
    private mentorService: MentorService,
    private dialogRef: MatDialogRef<DeleteMentorComponent>,
  ) {
  }

  deleteMentor() {
    this.mentorService.deleteMentorById(this.data.id).subscribe({
      next: (res) => {
        this.dialogRef.close({
          message: 'success',
        });
      },
      error: (err) => {
        this.dialogRef.close({
          message: 'error',
        });
        console.log(err);
      },
    });
  }
}

