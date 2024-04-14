import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {StudentService} from 'src/app/services/student.service';
import {
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';

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
  selector: 'app-edit-student',
  templateUrl: './edit-student.component.html',
  styleUrls: ['./edit-student.component.scss']
})
export class EditStudentComponent implements OnInit {
  studentForm: FormGroup = this.formBuilder.group({
    id: ['', Validators.required],
    name: ['', Validators.required],
    email: ['', Validators.required],
    dateOfBirth: ['', Validators.required],
    gender: ['', Validators.required],
    address: ['', Validators.required],
    phoneNumber: ['', Validators.required],
    grade: ['', Validators.required],
  });

  constructor(
    @Inject(MAT_DIALOG_DATA)
    public data: { id: any },
    private formBuilder: FormBuilder,
    private studentService: StudentService,
    private dialogRef: MatDialogRef<EditStudentComponent>
  ) {
  }

  genders = Object.values(Gender);
  selectedGender?: Gender;

  grades = Object.values(Grade);
  selectedGrade?: Grade;

  ngOnInit(): void {
    this.getProductById(this.data.id);
  }

  getProductById(id: String) {
    this.studentService.findStudentById(id).subscribe({
      next: (res) => {
        this.studentForm.setValue({
          id: res.data!.id,
          name: res.data!.name,
          email: res.data!.email,
          dateOfBirth: res.data!.dateOfBirth,
          gender: res.data!.gender,
          address: res.data!.address,
          phoneNumber: res.data!.phoneNumber,
          grade: res.data!.grade,
        });
      },
      error: (err) => {
        alert(err);
      },
    });
  }

  submit(): void {
    if (this.studentForm.valid) {

      const id = this.studentForm.get('id')?.value;
      const jsonData = this.studentForm.value;

      this.studentService.updateStudent(id, jsonData).subscribe({
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
    } else {
      this.dialogRef.close({
        message: 'invalid',
      });
    }
  }
}
