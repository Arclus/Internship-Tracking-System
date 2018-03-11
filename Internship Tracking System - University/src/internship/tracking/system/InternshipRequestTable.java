package internship.tracking.system;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Arclus
 */
public class InternshipRequestTable
{
    private final SimpleStringProperty id;
    private final SimpleStringProperty studentName;
    private final SimpleStringProperty studentSurname;
    private final SimpleStringProperty universityName;
    private final SimpleStringProperty departmentName;
    private final SimpleStringProperty program;
    private final SimpleStringProperty internshipTerm;
    private final SimpleStringProperty internshipDay;
    private final SimpleStringProperty internshipDayWeek;
    private final SimpleStringProperty startDate;
    private final SimpleStringProperty finishDate;
    
    public InternshipRequestTable(String id, String studentName, String studentSurname,String universityName, String departmentName, String program, String internshipTerm, String internshipDay, String internshipDayWeek, String startDate, String finishDate)
    {
        this.id = new SimpleStringProperty(id);
        this.studentName = new SimpleStringProperty(studentName);
        this.studentSurname = new SimpleStringProperty(studentSurname);
        this.universityName = new SimpleStringProperty(universityName);
        this.departmentName = new SimpleStringProperty(departmentName);
        this.program = new SimpleStringProperty(program);
        this.internshipTerm = new SimpleStringProperty(internshipTerm);
        this.internshipDay = new SimpleStringProperty(internshipDay);
        this.internshipDayWeek = new SimpleStringProperty(internshipDayWeek);
        this.startDate = new SimpleStringProperty(startDate);
        this.finishDate = new SimpleStringProperty(finishDate);

    }
    
    public String getId()
    {
        return id.get();
    }
        
    public void setId(String id)
    {
        this.id.set(id);
    }
    
    public String getStudentName()
    {
        return studentName.get();
    }
    
    public void setStudentName(String studentName)
    {
        this.studentName.set(studentName);
    }
    
    public String getStudentSurname()
    {
        return studentSurname.get();
    }
    
    public void setStudentSurname(String studentSurname)
    {
        this.studentSurname.set(studentSurname);
    }
    
    public String getUniversityName()
    {
        return universityName.get();
    }
    
    public void setUniversityName(String universityName)
    {
        this.universityName.set(universityName);
    }
    
    public String getDepartmentName()
    {
        return departmentName.get();
    }
    
    public void setDepartmentName(String departmentName)
    {
        this.departmentName.set(departmentName);
    }
    
    public String getProgram()
    {
        return program.get();
    }
        
    public void setProgram(String program)
    {
        this.program.set(program);
    }

    public String getInternshipTerm()
    {
        return internshipTerm.get();
    }
    
    public void setInternshipTerm(String internshipTerm)
    {
        this.internshipTerm.set(internshipTerm);
    }

    public String getInternshipDay()
    {
        return internshipDay.get();
    }
    
    public void setInternshipDay(String internshipDay)
    {
        this.internshipDay.set(internshipDay);
    }

    public String getInternshipDayWeek()
    {
        return internshipDayWeek.get();
    }
    
    public void setInternshipDayWeek(String internshipDayWeek)
    {
        this.internshipDayWeek.set(internshipDayWeek);
    }
    
    public String getStartDate()
    {
        return startDate.get();
    }
    
    public void setStartDate(String startDate)
    {
        this.startDate.set(startDate);
    }
    
    public String getFinishDate()
    {
        return finishDate.get();
    }
        
    public void setFinishDate(String finishDate)
    {
        this.finishDate.set(finishDate);
    }
}