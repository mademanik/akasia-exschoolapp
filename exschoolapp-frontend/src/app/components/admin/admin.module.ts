// Angular Modules
import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

// Angular Material Modules
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatRippleModule, MatNativeDateModule } from '@angular/material/core';
import {MatMenuModule} from '@angular/material/menu';
import {MatDividerModule} from '@angular/material/divider';
import {MatCardModule} from '@angular/material/card';
import {MatTableModule} from '@angular/material/table';
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {MatSelectModule} from '@angular/material/select';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatBadgeModule} from '@angular/material/badge';
import {MatDatepickerModule} from '@angular/material/datepicker';

// Components
import {AdminComponent} from './admin.component';
import {ProfileComponent} from './profile/profile.component';
import {StudentComponent} from './student/student.component';
import {RegistrationComponent} from './registration/registration.component';
import {ExtracurricularComponent} from './extracurricular/extracurricular.component';
import {MentorComponent} from './mentor/mentor.component';
import {AddStudentComponent} from './student/add-student/add-student.component';
import { AddMentorComponent } from './mentor/add-mentor/add-mentor.component';
import { AddExtracurricularComponent } from './extracurricular/add-extracurricular/add-extracurricular.component';
import { AddRegistrationComponent } from './registration/add-registration/add-registration.component';

const routes: Routes = [
  {
    path: '',
    component: AdminComponent,
    children: [
      {path: '', component: StudentComponent},
      {path: 'students', component: StudentComponent},
      {path: 'mentors', component: MentorComponent},
      {path: 'extracurriculars', component: ExtracurricularComponent},
      {path: 'registrations', component: RegistrationComponent},
    ],
  },
];

@NgModule({
  declarations: [
    AdminComponent,
    ProfileComponent,
    StudentComponent,
    RegistrationComponent,
    ExtracurricularComponent,
    MentorComponent,
    AddStudentComponent,
    AddMentorComponent,
    AddExtracurricularComponent,
    AddRegistrationComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MatRippleModule,
    MatMenuModule,
    MatSidenavModule,
    MatDividerModule,
    MatCardModule,
    MatTableModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatPaginatorModule,
    MatSelectModule,
    MatSortModule,
    MatSnackBarModule,
    MatBadgeModule,
    MatDatepickerModule,
    MatNativeDateModule,
    RouterModule.forChild(routes),
  ],
  providers: [DatePipe],
})
export class AdminModule {
}