import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {StudentService} from 'src/app/services/student.service';
import {MatSort} from '@angular/material/sort';
import {Student} from 'src/app/models/student.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import {AddStudentComponent} from "./add-student/add-student.component";
import {ShowStudentComponent} from "./show-student/show-student.component";
import {EditStudentComponent} from "./edit-student/edit-student.component";
import {DeleteStudentComponent} from "./delete-student/delete-student.component";

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.scss']
})
export class StudentComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = [
    'name',
    'email',
    'dateOfBirth',
    'gender',
    'grade',
    'action'
  ];

  dataSource = new MatTableDataSource<Student>();
  ELEMENT_DATA = [];

  @ViewChild(MatPaginator) paginator: any = MatPaginator;
  @ViewChild(MatSort) sort: any = MatSort;

  constructor(
    public dialog: MatDialog,
    private studentService: StudentService,
    private _snackBar: MatSnackBar
  ) {
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  ngOnInit(): void {
    this.findAllStudents();
  }

  findAllStudents() {
    this.studentService.findAllStudents().subscribe({
      next: (res) => {
        this.dataSource.data = res.data!;
      },
      error: (err) => {
        alert(err);
      },
    });
  }

  addDialog() {
    const dialogRef = this.dialog.open(AddStudentComponent, {
      width: '50%',
      position: {top: '20px'},
    });

    dialogRef.afterClosed().subscribe((result) => {
      setTimeout(() => {
        this.findAllStudents();
      }, 500);

      if (result?.message == 'success') {
        this.openSnackbarSuccess('Success', 'Data successfully created');
      } else if (result?.message == 'error') {
        this.openSnackbarError('Error', 'Data create Failed');
      } else if (result?.message == 'invalid') {
        this.openSnackbarError('Error', 'Form invalid');
      }
    });
  }

  openSnackbarSuccess(title: string, message: string) {
    this._snackBar.open(message, title, {
      horizontalPosition: 'right',
      verticalPosition: 'top',
      duration: 3000,
      panelClass: 'app-notification-success',
    });
  }

  openSnackbarError(title: string, message: string) {
    this._snackBar.open(message, title, {
      horizontalPosition: 'right',
      verticalPosition: 'top',
      duration: 3000,
      panelClass: 'app-notification-error',
    });
  }

  showDialog(id: String) {
    const dialogRef = this.dialog.open(ShowStudentComponent, {
      width: '50%',
      position: {top: '20px'},
      data: {id: id},
    });

    dialogRef.afterClosed().subscribe((result) => {
      setTimeout(() => {
        this.findAllStudents();
      }, 500);
    });
  }

  editDialog(id: String) {
    const dialogRef = this.dialog.open(EditStudentComponent, {
      width: '50%',
      position: {top: '20px'},
      data: {id: id},
    });

    dialogRef.afterClosed().subscribe((result) => {
      setTimeout(() => {
        this.findAllStudents();
      }, 500);

      this.resultSnackBar(result, 'update');
    });
  }

  deleteDialog(id: String) {
    const dialogRef = this.dialog.open(DeleteStudentComponent, {
      width: '20%',
      position: {top: '20px'},
      data: {id: id},
    });

    dialogRef.afterClosed().subscribe((result) => {
      setTimeout(() => {
        this.findAllStudents();
      }, 500);

      this.resultSnackBar(result, 'delete');
    });
  }

  resultSnackBar(result: any, type: string) {
    if (result?.message == 'success') {
      this.openSnackbarSuccess('Success', `Data ${type} successfully`);
    } else if (result?.message == 'error') {
      this.openSnackbarError('Error', `Data ${type} Failed`);
    } else if (result?.message == 'invalid') {
      this.openSnackbarError('Error', `Form ${type} invalid`);
    }
  }

}
