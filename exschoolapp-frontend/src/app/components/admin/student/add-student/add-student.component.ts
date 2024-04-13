import {Component} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import {StudentService} from 'src/app/services/student.service';

export enum Gender {
  MALE = 'MALE',
  FEMALE = 'FEMALE',
}

export enum Grade {
  GRADE_1 = 'GRADE_1',
  GRADE_2 = 'GRADE_2',
  GRADE_3 = 'GRADE_3',
  GRADE_4 = 'GRADE_4',
  GRADE_5 = 'GRADE_5',
  GRADE_6 = 'GRADE_6',
}

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.scss']
})
export class AddStudentComponent {
  constructor(
    private formBuilder: FormBuilder,
    private studentService: StudentService,
    public dialogRef: MatDialogRef<AddStudentComponent>
  ) {
  }

  genders = Object.values(Gender);
  selectedGender?: Gender;

  grades = Object.values(Grade);
  selectedGrade?: Grade;


  studentForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    email: ['', Validators.required],
    dateOfBirth: ['', Validators.required],
    gender: ['', Validators.required],
    address: ['', Validators.required],
    phoneNumber: ['', Validators.required],
    grade: ['', Validators.required],
  });

  submit(): void {
    if (this.studentForm.valid) {
      const jsonData = this.studentForm.value;

      this.studentService.createStudent(jsonData).subscribe({
        next: (res) => {
          this.dialogRef.close({
            message: 'success',
          });
        },
        error: (err) => {
          console.log(err);
          this.dialogRef.close({
            message: 'error',
          });
        },
      });
    } else {
      this.dialogRef.close({
        message: 'invalid',
      });
    }
  }

}
