package internship.tracking.system.student;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Arclus
 */
public class MailTable 
{
    private final SimpleStringProperty mailID;
    private final SimpleStringProperty senderMail;
    private final SimpleStringProperty mailSubject;
    private final SimpleStringProperty mailText;
    private final SimpleStringProperty mailDate;
    
    public MailTable(String mailID, String senderMail, String mailSubject, String mailText, String mailDate)
    {
        this.mailID = new SimpleStringProperty(mailID);
        this.senderMail = new SimpleStringProperty(senderMail);
        this.mailSubject = new SimpleStringProperty(mailSubject);
        this.mailText = new SimpleStringProperty(mailText);
        this.mailDate = new SimpleStringProperty(mailDate);
    }
    
    public String getMailID()
    {
        return mailID.get();
    }
        
    public void setMailID(String mailID)
    {
        this.mailID.set(mailID);
    }
    
    public String getSenderMail()
    {
        return senderMail.get();
    }
    
    public void setSenderMail(String senderMail)
    {
        this.senderMail.set(senderMail);
    }
    
    public String getMailSubject()
    {
        return mailSubject.get();
    }
    
    public void setMailSubject(String mailSubject)
    {
        this.mailSubject.set(mailSubject);
    }
    
    public String getMailText()
    {
        return mailText.get();
    }
    
    public void setMailText(String mailText)
    {
        this.mailText.set(mailText);
    }

    public String getMailDate()
    {
        return mailDate.get();
    }
    
    public void setMailDate(String mailDate)
    {
        this.mailDate.set(mailDate);
    }

    
}