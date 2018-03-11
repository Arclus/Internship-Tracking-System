package internship.tracking.system.student;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    
    static final ObservableList cOptions = FXCollections.observableArrayList();
    static final ObservableList dOptions = FXCollections.observableArrayList();
    static final ObservableList studentInfoList = FXCollections.observableArrayList();
    static final ObservableList internshipRequestUniversity = FXCollections.observableArrayList();
    static final ObservableList iRUC = FXCollections.observableArrayList();
    static final ObservableList checkInternship = FXCollections.observableArrayList();
    static final ObservableList gIInfo = FXCollections.observableArrayList();
    static final ObservableList gMInbox = FXCollections.observableArrayList();
    public static int universityID;
    public static int internshipBookID;
    public static String mail;
    
    
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
            String sql= "SELECT studentID,studentPassword,studentMail FROM student WHERE studentID = '" + tempID + "' && studentPassword = '" + pW + "'";
            ResultSet rss = connection.executeQuery(sql);
            
            if (rss.next())
            {
                InternshipTrackingSystemStudent.studentMail = rss.getString("studentMail");
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
    
    public static String checkUniversityID(String universityEmail)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "SELECT universityID FROM university WHERE universityEmail = '" + universityEmail + "'";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {  
                while(rss.next())
                {  
                    System.out.println("girdi mi");
                    return rss.getString("universityID");
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
    
        public static String checkCompanyID(String companyEmail)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "SELECT companyID FROM company WHERE companyMail = '" + companyEmail + "'";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {  
                while(rss.next())
                {  
                    
                    return rss.getString("companyID");
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
        
    
    public static String getMailOutbox()
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "SELECT mailID,receiverMail,mailSubject,mailText,mailDate FROM mailbox WHERE senderMail = '" + InternshipTrackingSystemStudent.studentMail + "'";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {       
                while (rss.next())
                {
                     MailBoxPage.data.add(new MailTable(
                        rss.getString("mailID"),
                        rss.getString("receiverMail"),
                        rss.getString("mailSubject"),
                        rss.getString("mailText"),
                        rss.getString("mailDate")
                    ));
                }
                MailBoxPage.table.setItems(MailBoxPage.data);
                
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
                
    public static String getMailInbox()
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "SELECT mailID,senderMail,mailSubject,mailText,mailDate FROM mailbox WHERE receiverMail = '" + InternshipTrackingSystemStudent.studentMail + "'";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {       
                while (rss.next())
                {
                     MailBoxPage.data.add(new MailTable(
                        rss.getString("mailID"),
                        rss.getString("senderMail"),
                        rss.getString("mailSubject"),
                        rss.getString("mailText"),
                        rss.getString("mailDate")
                    ));
                }
                MailBoxPage.table.setItems(MailBoxPage.data);
                
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
        
    public static String sendMailCompany(String companyMail, String mailSubject, String mailText, String mailDate)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "INSERT INTO mailbox(mailSubject,mailText,mailDate,senderMail,receiverMail) VALUES (" + " '" + mailSubject + "', " + " '" + mailText + "', " + " '" + mailDate + "', " + " '" + InternshipTrackingSystemStudent.studentMail + "', " + " '" + companyMail + "')";
            
            connection.executeUpdate(sql);
            connection.close();
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
    
    public static String sendMailUniversity(String universityMail, String mailSubject, String mailText, String mailDate)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "INSERT INTO mailbox(mailSubject,mailText,mailDate,senderMail,receiverMail) VALUES (" + " '" + mailSubject + "', " + " '" + mailText + "', " + " '" + mailDate + "', " + " '" + InternshipTrackingSystemStudent.studentMail + "', " + " '" + universityMail + "')";
            
            connection.executeUpdate(sql);
            connection.close();
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
    
    public static String insertPDF(String fileName)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        String sql= "SELECT internship.internshipID FROM internship";

        return "0";
    }
    
    public static String addInternshipBookPage(String pageText, String date)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "INSERT INTO bookpage(pageText,internshipBookID,pageDate) VALUES (" + " '" + pageText + "', " + " '" + internshipBookID + "', " + " '" + date + "')";
            
            connection.executeUpdate(sql);
            connection.close();
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
    
    public static String checkInternship(int id)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "SELECT internship.internshipID FROM internship INNER JOIN student on internship.internshipID = student.studentID WHERE internship.studentID = '" + id + "'";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {  
                while(rss.next())
                {   
                    checkInternship.add(rss.getString("internship.internshipID"));
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
    
    public static String getInternshipInfo(int comboID)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "SELECT internship.internshipID,internship.program,company.companyName,internship.startDate,internship.finishDate,internship.acceptedDay,internship.universtiyCheck,internship.companyCheck  FROM internship INNER JOIN company on company.companyID = internship.companyID WHERE internshipID = '" + comboID + "'";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {  
                while(rss.next())
                {   
                    gIInfo.add(rss.getString("internship.internshipID"));
                    gIInfo.add(rss.getString("internship.program"));
                    gIInfo.add(rss.getString("company.companyName"));
                    gIInfo.add(rss.getString("internship.startDate"));
                    gIInfo.add(rss.getString("internship.finishDate"));
                    gIInfo.add(rss.getString("internship.acceptedDay"));
                    gIInfo.add(rss.getString("internship.universtiyCheck"));
                    gIInfo.add(rss.getString("internship.companyCheck"));
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
    
    public static String getStudentInfo(int id)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
       
        try
        {
            String sql= "SELECT student.studentName,student.studentSurname,"
                    + "student.studentID,student.studentClass,"
                    + "student.studentAverage,department.departmentName,student.universityID "
                    + "FROM student "
                    + "INNER JOIN department on department.departmentID = student.departmentID "
                    + "WHERE studentID = '" + id + "'";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {  
                while(rss.next())
                {   
                    studentInfoList.add(rss.getString("student.studentName"));
                    studentInfoList.add(rss.getString("student.studentSurname"));
                    studentInfoList.add(rss.getString("student.studentID"));
                    studentInfoList.add(rss.getString("student.studentClass"));
                    studentInfoList.add(rss.getString("student.studentAverage"));
                    studentInfoList.add(rss.getString("department.departmentName"));
                    universityID = Integer.parseInt(rss.getString("student.universityID"));
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
    
    public static String requestInternUniversity(int studentID, int universityID, int internshipTerm, int internshipDay, int iDWeek, String iStartDate, String iEndDate, String program)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "INSERT INTO internshiprequestuniversity(studentID,universityID,universityProgram,internshipTerm,internshipDay,internshipDayWeek,internshipStartDate,internshipEndDate) VALUES (" + " '" + studentID + "', " + " '" + universityID + "', " + " '" + program + "', " + " '" + internshipTerm + "', " + " '" + internshipDay + "', " + " '" + iDWeek + "', " + " '" + iStartDate + "', " + " '" + iEndDate + "')";
            
            connection.executeUpdate(sql);
            connection.close();
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
    
    public static String requestInternCompany(int studentID, int selectedCompany)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "INSERT INTO internshiprequestcompany(studentID,companyID) VALUES (" + " '" + studentID + "', " + " '" + selectedCompany + "')";
            
            connection.executeUpdate(sql);
            connection.close();
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
    
    public static String getMailInbox(int id)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "SELECT senderID,mailSubject,mailText,mailDate FROM mailbox WHERE receiverID = '" + id + "'";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {  
                while(rss.next())
                {   
                    gMInbox.add(rss.getString("mailSubject"));
                    gMInbox.add(rss.getString("mailText"));
                    gMInbox.add(rss.getString("mailDate"));
                    gMInbox.add(rss.getString("senderID"));
                    
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
    
    public static String getRequestInternInfo(int id)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "SELECT company.companyName,company.companyCity,"
                    + "company.companyFieldActivity,company.companyMail,"
                    + "company.companyPhone,company.companyAddress "
                    + "FROM internshiprequestcompany "
                    + "INNER JOIN student on internshiprequestcompany.studentID = student.studentID "
                    + "INNER JOIN company on company.companyID = internshiprequestcompany.companyID "
                    + "WHERE student.studentID = '" + id + "'";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {
                while(rss.next())
                {
                    internshipRequestUniversity.add(rss.getString("company.companyName"));
                    iRUC.add(rss.getString("company.companyCity"));
                    internshipRequestUniversity.add(rss.getString("company.companyFieldActivity"));
                    internshipRequestUniversity.add(rss.getString("company.companyMail"));
                    internshipRequestUniversity.add(rss.getString("company.companyPhone"));
                    internshipRequestUniversity.add(rss.getString("company.companyAddress"));
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
    
    public static String checkCompanyConfirm(int id)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        String temp = "1";
        
        try
        {
            String sql= "SELECT internshiprequestcompany.companyRequestCheck"
                    + " FROM internshiprequestcompany "
                    + "INNER JOIN student on internshiprequestcompany.studentID = student.studentID "
                    + "WHERE student.studentID = '" + id + "'";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {  
                while(rss.next())
                {   
                    temp = rss.getString("internshiprequestcompany.companyRequestCheck");
                }
                
                connection.close();
                rss.close();
            }
            
            return temp;
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
            String sql= "SELECT internshipBookID FROM internshipbook "
                    + "WHERE internshipID = '" + ShowIntern.selectedInternship + "' "
                    + "AND studentID = '" + InternshipTrackingSystemStudent.studentNumber + "'";
            
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
    
    public static String deleteInternshipBookPage(String pageDate)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "DELETE FROM bookpage WHERE pageDate = '" + pageDate + "' AND internshipBookID = '" + internshipBookID + "'";
                   
            connection.executeUpdate(sql);
            connection.close();
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
            return "a"; 
        }
    }
    
    public static String updateInternshipBookPage(String pageText, String pageDate)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "UPDATE bookpage SET pageText = '" + pageText + "' WHERE internshipBookID = '" + internshipBookID + "' AND pageDate = '" + pageDate + "'";
            
            connection.executeUpdate(sql);
            connection.close();
            
            return "1";
        }
        catch (SQLException | HeadlessException a)
        {
           return a.toString();
        }
    }
    
    public static String getMailForUpdate(String pageDate)
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
    
    public static String getCity()
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "SELECT cityName FROM city";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {       
                while (rss.next())
                {
                    cOptions.add(rss.getString("cityName"));
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
    
    public static String getCompany(String selectedcityName)
    {
        DBOperations vt = new DBOperations();
        Statement connection = vt.connection();
        
        try
        {
            String sql= "SELECT companyID,companyName,companyCity,companyFieldActivity,companyPhone,companyMail,companyAddress FROM company WHERE companyCity = '" + selectedcityName + "'";
            
            try (ResultSet rss = connection.executeQuery(sql))
            {       
                while (rss.next())
                {
                     RequestCompany.data.add(new CompanyTable(
                        rss.getString("companyID"),
                        rss.getString("companyName"),
                        rss.getString("companyCity"),
                        rss.getString("companyFieldActivity"),
                        rss.getString("companyPhone"),
                        rss.getString("companyMail"),
                        rss.getString("companyAddress")
                    ));
                }
                RequestCompany.table.setItems(RequestCompany.data);
                
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
}