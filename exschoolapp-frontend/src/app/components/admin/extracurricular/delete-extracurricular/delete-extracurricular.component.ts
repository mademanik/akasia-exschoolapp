import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ExtracurricularService} from 'src/app/services/extracurricular.service';

@Component({
  selector: 'app-delete-extracurricular',
  templateUrl: './delete-extracurricular.component.html',
  styleUrls: ['./delete-extracurricular.component.scss']
})
export class DeleteExtracurricularComponent {
  id: String = '';

  constructor(
    @Inject(MAT_DIALOG_DATA)
    public data: { id: any },
    private extracurricularService: ExtracurricularService,
    private dialogRef: MatDialogRef<DeleteExtracurricularComponent>,
  ) {
  }

  deleteExtracurricular() {
    this.extracurricularService.deleteExtracurricularById(this.data.id).subscribe({
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

