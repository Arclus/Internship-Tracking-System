package internship.tracking.system;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Arclus
 */

public class DBOperations
{
    Connection cn;
    
    static final ObservableList studentInfoList = FXCollections.observableArrayList();
    static final ObservableList dOptions = FXCollections.observableArrayList();
    public static String mail;
    public static int internshipBookID;
    
    public Statement connection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/interndb?autoReconnect=true&useSSL=false", "username", "password");
                   
            return cn.createStatement();
        }
        
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null, "Not Connected");
            
            return null;
        }
    }
    
    public static String loginCheck(String id, String pW)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        int tempID = Integer.parseInt(id);
        
        try
        {
            String sql= "SELECT universityID,universityPassword FROM university WHERE universityID = '" + tempID + "' && universityPassword = '" + pW + "'";
            ResultSet rss = connection.executeQuery(sql);
            
            if (rss.next())
            {
                rss.close();
                connection.close();
                
                return "1";
            }

            else
            {
                rss.close();
                connection.close();
                
                return "0";
            }
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
    
    public static String setInternshipRequest(String temp, int selectedRowID)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "UPDATE internshiprequestuniversity SET universityRequestCheck = '" + temp + "' WHERE intershiprequestUID = '" + selectedRowID + "'";
            
            connection.executeUpdate(sql);
            connection.close();
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
    
    public static String checkInternshipBookID()
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "SELECT internshipBookID FROM internshipbook WHERE internshipID = '" + ShowInternshipAndBook.tSelectedInternshipIndex + "' AND studentID = '" + ShowInternshipAndBook.tSelectedStudentIndex + "'";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {  
                while(rss.next())
                {
                    internshipBookID = Integer.parseInt(rss.getString("internshipBookID"));
                    
                    return "1";
                }
                
                connection.close();
                rss.close();
                
                return "0";
            }     
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
    
    public static String getDate()
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
 
        String sql= "SELECT pageDate FROM bookpage WHERE internshipBookID = '" + internshipBookID + "'";

        try (ResultSet rss = connection.executeQuery(sql))
        {       
            while (rss.next())
            {
                dOptions.add(rss.getString("pageDate"));
            }
            
            connection.close();
            rss.close();

            return "1";     
        }
        
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
    
    public static String getMail(String pageDate)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "SELECT pageText FROM bookpage WHERE internshipBookID = '" + internshipBookID + "' AND pageDate = '" + pageDate + "'";

            try (ResultSet rss = connection.executeQuery(sql))
            {       
                while (rss.next())
                {
                    mail = rss.getString("pageText");
                    
                    return "1"; 
                }
                connection.close();
                rss.close();
            }
            
            return "0";     
        }
        
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
    
    public static String getStudentInfo()
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
       
        try
        {
            String sql= "SELECT student.studentName,student.studentSurname,student.studentID,student.studentClass,student.studentAverage,student.studentProgram,student.studentInsurance,department.departmentName,student.studentMail FROM student INNER JOIN department on department.departmentID = student.departmentID WHERE studentID = '" + ShowInternshipAndBook.tSelectedStudentIndex + "'";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {  
                while(rss.next())
                {   
                    studentInfoList.add(rss.getString("student.studentName"));
                    studentInfoList.add(rss.getString("student.studentSurname"));
                    studentInfoList.add(rss.getString("student.studentID"));
                    studentInfoList.add(rss.getString("department.departmentName"));
                    studentInfoList.add(rss.getString("student.studentClass"));
                    studentInfoList.add(rss.getString("student.studentAverage"));
                    studentInfoList.add(rss.getString("student.studentProgram"));
                    studentInfoList.add(rss.getString("student.studentInsurance"));
                    studentInfoList.add(rss.getString("student.studentMail"));
                }
                
                connection.close();
                rss.close();
            }
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
    
    public static String updateInternshipConfirm(String value)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "UPDATE internshipbook SET universityBookCheck = '" + value + "' WHERE internshipBookID = '" + internshipBookID + "'";
            
            connection.executeUpdate(sql);
            connection.close();
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
    
    public static String getInternshipWithDate(int universtiyID, String startDate, String finishDay) throws SQLException
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();

        String sql= "SELECT internship.internshipID,"
                + "student.studentID,"
                + "student.studentName,student.studentSurname,"
                + "internship.program,"
                + "company.companyName,"
                + "internship.acceptedDay,internship.startDate,"
                + "internship.finishDate "
                + "FROM internship "
                + "INNER JOIN student on student.studentID = internship.studentID "
                + "INNER JOIN company on company.companyID = internship.companyID "
                + "WHERE internship.startDate BETWEEN '" + startDate + "' AND '" + finishDay + "' AND student.universityID = '" + universtiyID + "'";
        
        ResultSet rss = connection.executeQuery(sql);
        try
        {
            while (rss.next())
            {
                 ShowInternshipAndBook.data.add(new InternshipTable(
                    rss.getString("internship.internshipID"),
                    rss.getString("student.studentID"),
                    rss.getString("student.studentName"),
                    rss.getString("student.studentSurname"),
                    rss.getString("internship.program"),
                    rss.getString("company.companyName"),
                    rss.getString("internship.acceptedDay"),   
                    rss.getString("internship.startDate"),
                    rss.getString("internship.finishDate")
                ));
            }
            
            ShowInternshipAndBook.table.setItems(ShowInternshipAndBook.data);

            connection.close();
            rss.close();
            
            return "1";     
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
    
    public static String getInternship(int universtiyID) throws SQLException
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();

        String sql= "SELECT internship.internshipID,"
                + "student.studentID,"
                + "student.studentName,student.studentSurname,"
                + "internship.program,"
                + "company.companyName,"
                + "internship.acceptedDay,internship.startDate,"
                + "internship.finishDate "
                + "FROM internship "
                + "INNER JOIN student on student.studentID = internship.studentID "
                + "INNER JOIN company on company.companyID = internship.companyID "
                + "WHERE student.universityID = '" + universtiyID + "'";
        
        ResultSet rss = connection.executeQuery(sql);

        try
        {
            while (rss.next())
            {
                 ShowInternshipAndBook.data.add(new InternshipTable(
                    rss.getString("internship.internshipID"),
                    rss.getString("student.studentID"),
                    rss.getString("student.studentName"),
                    rss.getString("student.studentSurname"),
                    rss.getString("internship.program"),
                    rss.getString("company.companyName"),
                    rss.getString("internship.acceptedDay"),   
                    rss.getString("internship.startDate"),
                    rss.getString("internship.finishDate")
                ));
            }
            
            ShowInternshipAndBook.table.setItems(ShowInternshipAndBook.data);

            connection.close();
            rss.close();
            
            return "1";     
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
    
    public static String getInternshipRequest(int universtiyID) throws SQLException
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();

        String sql= "SELECT internshiprequestuniversity.intershiprequestUID,"
                + "student.studentName,student.studentSurname,"
                + "university.universityName,department.departmentName,"
                + "internshiprequestuniversity.universityProgram,internshiprequestuniversity.internshipTerm,"
                + "internshiprequestuniversity.internshipDay,"
                + "internshiprequestuniversity.internshipDayWeek,internshiprequestuniversity.internshipStartDate,"
                + "internshiprequestuniversity.internshipEndDate "
                + "FROM internshiprequestuniversity "
                + "INNER JOIN student on student.studentID = internshiprequestuniversity.studentID "
                + "INNER JOIN university on university.universityID = internshiprequestuniversity.universityID "
                + "INNER JOIN department on department.departmentID = student.departmentID "
                + "WHERE internshiprequestuniversity.universityID = '" + universtiyID + "'";
        ResultSet rss = connection.executeQuery(sql);

        try
        {
            while (rss.next())
            {
                 ShowInternshipRequest.data.add(new InternshipRequestTable(
                    rss.getString("internshiprequestuniversity.intershiprequestUID"),
                    rss.getString("student.studentName"),
                    rss.getString("student.studentSurname"),
                    rss.getString("university.universityName"),
                    rss.getString("department.departmentName"),
                    rss.getString("internshiprequestuniversity.universityProgram"),
                    rss.getString("internshiprequestuniversity.internshipTerm"),
                    rss.getString("internshiprequestuniversity.internshipDay"),
                    rss.getString("internshiprequestuniversity.internshipDayWeek"),
                    rss.getString("internshiprequestuniversity.internshipStartDate"),
                    rss.getString("internshiprequestuniversity.internshipEndDate")
                ));
            }
            
            ShowInternshipRequest.table.setItems(ShowInternshipRequest.data);

            connection.close();
            rss.close();
            
            return "1";     
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
}