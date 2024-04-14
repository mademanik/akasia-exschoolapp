import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {StudentService} from 'src/app/services/student.service';

@Component({
  selector: 'app-show-student',
  templateUrl: './show-student.component.html',
  styleUrls: ['./show-student.component.scss']
})
export class ShowStudentComponent implements OnInit {
  constructor(
    @Inject(MAT_DIALOG_DATA)
    public data: { id: any },
    private studentService: StudentService,
    private dialogRef: MatDialogRef<ShowStudentComponent>
  ) {
  }

  row: any;

  ngOnInit(): void {
    this.findStudentById(this.data.id);
  }

  findStudentById(id: String) {
    this.studentService.findStudentById(id).subscribe({
      next: (res) => {
        this.row = res.data;
      },
      error: (err) => {
        alert(err);
      },
    });
  }
}
