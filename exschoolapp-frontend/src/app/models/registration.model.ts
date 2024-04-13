import {Student} from "./student.model";
import {Extracurricular} from "./extracurricular.model";

export class Registration {
  id?: any;
  createdDate?: any;
  lastUpdatedDate?: any;
  studentId?: any;
  student?: Student;
  extracurricularId?: any;
  extracurricular?: Extracurricular;
  description?: string;
}
