
package internship.tracking.system;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Arclus
 */
public class InternshipTable
{
    private final SimpleStringProperty internshipID;
    private final SimpleStringProperty studentID;
    private final SimpleStringProperty studentName;
    private final SimpleStringProperty studentSurname;
    private final SimpleStringProperty program;
    private final SimpleStringProperty companyName;
    private final SimpleStringProperty acceptedDay;
    private final SimpleStringProperty startDate;
    private final SimpleStringProperty finishDate;
    
    public InternshipTable(String internshipID, String studentID, String studentName, String studentSurname, String program, String companyName, String acceptedDay, String startDate, String finishDate)
    {
        this.internshipID = new SimpleStringProperty(internshipID);
        this.studentID = new SimpleStringProperty(studentID);
        this.studentName = new SimpleStringProperty(studentName);
        this.studentSurname = new SimpleStringProperty(studentSurname);
        this.program = new SimpleStringProperty(program);
        this.companyName = new SimpleStringProperty(companyName);
        this.acceptedDay = new SimpleStringProperty(acceptedDay);
        this.startDate = new SimpleStringProperty(startDate);
        this.finishDate = new SimpleStringProperty(finishDate);
    }
    
    public String getInternshipID()
    {
        return internshipID.get();
    }
        
    public void setInternshipID(String internshipID)
    {
        this.internshipID.set(internshipID);
    }
    
    public String getStudentID()
    {
        return studentID.get();
    }
    
    public void setStudentID(String studentID)
    {
        this.studentID.set(studentID);
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
    
    public String getProgram()
    {
        return program.get();
    }
        
    public void setProgram(String program)
    {
        this.program.set(program);
    }
    
    public String getCompanyName()
    {
        return companyName.get();
    }
        
    public void setCompanyName(String companyName)
    {
        this.companyName.set(companyName);
    }
    
    public String getAcceptedDay()
    {
        return acceptedDay.get();
    }
    
    public void setAcceptedDay(String acceptedDay)
    {
        this.acceptedDay.set(acceptedDay);
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