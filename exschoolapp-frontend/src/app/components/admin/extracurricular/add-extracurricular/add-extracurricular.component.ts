import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import {ExtracurricularService} from 'src/app/services/extracurricular.service';
import {MentorService} from 'src/app/services/mentor.service';
import {Mentor} from "../../../../models/mentor.model";

@Component({
  selector: 'app-add-extracurricular',
  templateUrl: './add-extracurricular.component.html',
  styleUrls: ['./add-extracurricular.component.scss']
})
export class AddExtracurricularComponent implements OnInit {
  constructor(
    private formBuilder: FormBuilder,
    private extracurricularService: ExtracurricularService,
    private mentorService: MentorService,
    public dialogRef: MatDialogRef<AddExtracurricularComponent>
  ) {
  }

  mentors?: Mentor[];
  selectedMentor?: Mentor;

  extracurricularForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
    startDate: ['', Validators.required],
    endDate: ['', Validators.required],
    registrationStartDate: ['', Validators.required],
    registrationEndDate: ['', Validators.required],
    location: ['', Validators.required],
    description: ['', Validators.required],
    quota: ['', Validators.required],
    mentorId: ['', Validators.required],
  });

  submit(): void {
    if (this.extracurricularForm.valid) {
      const jsonData = this.extracurricularForm.value;

      this.extracurricularService.createExtracurricular(jsonData).subscribe({
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
    this.findAllMentors();
  }

  findAllMentors() {
    this.mentorService.findAllMentors().subscribe({
      next: (res) => {
        this.mentors = res.data!;
      },
      error: (err) => {
        alert(err);
      },
    });
  }

}
