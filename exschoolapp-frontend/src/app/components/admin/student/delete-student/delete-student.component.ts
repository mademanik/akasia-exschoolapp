import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {StudentService} from 'src/app/services/student.service';

@Component({
  selector: 'app-delete-student',
  templateUrl: './delete-student.component.html',
  styleUrls: ['./delete-student.component.scss']
})
export class DeleteStudentComponent {
  id: String = '';

  constructor(
    @Inject(MAT_DIALOG_DATA)
    public data: { id: any },
    private studentService: StudentService,
    private dialogRef: MatDialogRef<DeleteStudentComponent>,
  ) {
  }

  deleteStudent() {
    this.studentService.deleteStudentById(this.data.id).subscribe({
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
