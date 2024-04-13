import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import {RegistrationService} from 'src/app/services/registration.service';
import {StudentService} from 'src/app/services/student.service';
import {ExtracurricularService} from 'src/app/services/extracurricular.service';
import {Student} from "../../../../models/student.model";
import {Extracurricular} from "../../../../models/extracurricular.model";

@Component({
  selector: 'app-add-registration',
  templateUrl: './add-registration.component.html',
  styleUrls: ['./add-registration.component.scss']
})
export class AddRegistrationComponent implements OnInit {
  constructor(
    private formBuilder: FormBuilder,
    private registrationService: RegistrationService,
    private studentService: StudentService,
    private extracurricularService: ExtracurricularService,
    public dialogRef: MatDialogRef<AddRegistrationComponent>
  ) {
  }

  students?: Student[];
  selectedStudent?: Student;

  extracurriculars?: Extracurricular[];
  selectedExtracurricular?: Extracurricular;

  registrationForm: FormGroup = this.formBuilder.group({
    studentId: ['', Validators.required],
    extracurricularId: ['', Validators.required],
    description: ['', Validators.required],
  });

  submit(): void {
    if (this.registrationForm.valid) {
      const jsonData = this.registrationForm.value;

      this.registrationService.createRegistration(jsonData).subscribe({
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

  ngOnInit(): void {
    this.findAllStudents();
    this.findAllExtracurriculars();
  }

  findAllStudents() {
    this.studentService.findAllStudents().subscribe({
      next: (res) => {
        this.students = res.data!;
      },
      error: (err) => {
        alert(err);
      },
    });
  }

  findAllExtracurriculars() {
    this.extracurricularService.findAllExtracurriculars().subscribe({
      next: (res) => {
        this.extracurriculars = res.data!;
      },
      error: (err) => {
        alert(err);
      },
    });
  }
}


