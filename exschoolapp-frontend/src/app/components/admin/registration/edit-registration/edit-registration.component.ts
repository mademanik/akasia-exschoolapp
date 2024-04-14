import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {RegistrationService} from 'src/app/services/registration.service';
import {
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import {StudentService} from 'src/app/services/student.service';
import {ExtracurricularService} from 'src/app/services/extracurricular.service';
import {Student} from "../../../../models/student.model";
import {Extracurricular} from "../../../../models/extracurricular.model";

@Component({
  selector: 'app-edit-registration',
  templateUrl: './edit-registration.component.html',
  styleUrls: ['./edit-registration.component.scss']
})
export class EditRegistrationComponent implements OnInit {
  registrationForm: FormGroup = this.formBuilder.group({
    id: ['', Validators.required],
    studentId: ['', Validators.required],
    extracurricularId: ['', Validators.required],
    description: ['', Validators.required],
  });

  constructor(
    @Inject(MAT_DIALOG_DATA)
    public data: { id: any },
    private formBuilder: FormBuilder,
    private registrationService: RegistrationService,
    private studentService: StudentService,
    private extracurricularService: ExtracurricularService,
    private dialogRef: MatDialogRef<EditRegistrationComponent>
  ) {
  }

  students?: Student[];
  selectedStudent?: Student;

  extracurriculars?: Extracurricular[];
  selectedExtracurricular?: Extracurricular;

  ngOnInit(): void {
    this.findRegistrationById(this.data.id);
    this.findAllStudents();
    this.findAllExtracurriculars();
  }

  findRegistrationById(id: String) {
    this.registrationService.findRegistrationById(id).subscribe({
      next: (res) => {
        this.registrationForm.setValue({
          id: res.data!.id,
          studentId: res.data!.studentId,
          extracurricularId: res.data!.extracurricularId,
          description: res.data!.description,
        });
      },
      error: (err) => {
        alert(err);
      },
    });
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

  submit(): void {
    if (this.registrationForm.valid) {

      const id = this.registrationForm.get('id')?.value;
      const jsonData = this.registrationForm.value;

      this.registrationService.updateRegistration(id, jsonData).subscribe({
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
