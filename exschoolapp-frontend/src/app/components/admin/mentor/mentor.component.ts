import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {MentorService} from 'src/app/services/mentor.service';
import {MatSort} from '@angular/material/sort';
import {Mentor} from 'src/app/models/mentor.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import {AddMentorComponent} from "./add-mentor/add-mentor.component";
import {ShowMentorComponent} from "./show-mentor/show-mentor.component";
import {EditMentorComponent} from "./edit-mentor/edit-mentor.component";
import {DeleteMentorComponent} from "./delete-mentor/delete-mentor.component";

@Component({
  selector: 'app-mentor',
  templateUrl: './mentor.component.html',
  styleUrls: ['./mentor.component.scss']
})
export class MentorComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = [
    'name',
    'email',
    'gender',
    'address',
    'phoneNumber',
    'action'
  ];

  dataSource = new MatTableDataSource<Mentor>();
  ELEMENT_DATA = [];

  @ViewChild(MatPaginator) paginator: any = MatPaginator;
  @ViewChild(MatSort) sort: any = MatSort;

  constructor(
    public dialog: MatDialog,
    private mentorService: MentorService,
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
    this.findAllMentors();
  }

  findAllMentors() {
    this.mentorService.findAllMentors().subscribe({
      next: (res) => {
        this.dataSource.data = res.data!;
      },
      error: (err) => {
        alert(err);
      },
    });
  }

  addDialog() {
    const dialogRef = this.dialog.open(AddMentorComponent, {
      width: '50%',
      position: {top: '20px'},
    });

    dialogRef.afterClosed().subscribe((result) => {
      setTimeout(() => {
        this.findAllMentors();
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
    const dialogRef = this.dialog.open(ShowMentorComponent, {
      width: '50%',
      position: {top: '20px'},
      data: {id: id},
    });

    dialogRef.afterClosed().subscribe((result) => {
      setTimeout(() => {
        this.findAllMentors();
      }, 500);
    });
  }

  editDialog(id: String) {
    const dialogRef = this.dialog.open(EditMentorComponent, {
      width: '50%',
      position: {top: '20px'},
      data: {id: id},
    });

    dialogRef.afterClosed().subscribe((result) => {
      setTimeout(() => {
        this.findAllMentors();
      }, 500);

      this.resultSnackBar(result, 'update');
    });
  }

  deleteDialog(id: String) {
    const dialogRef = this.dialog.open(DeleteMentorComponent, {
      width: '20%',
      position: {top: '20px'},
      data: {id: id},
    });

    dialogRef.afterClosed().subscribe((result) => {
      setTimeout(() => {
        this.findAllMentors();
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

