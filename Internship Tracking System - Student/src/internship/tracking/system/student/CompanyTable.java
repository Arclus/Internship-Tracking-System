package internship.tracking.system.student;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Arclus
 */
public class CompanyTable
{
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty city;
    private final SimpleStringProperty fieldActivity;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty mail;
    private final SimpleStringProperty address;
    
    public CompanyTable(String id, String name, String city, String fieldActivity, String phone, String mail, String address)
    {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.city = new SimpleStringProperty(city);
        this.fieldActivity = new SimpleStringProperty(fieldActivity);
        this.phone = new SimpleStringProperty(phone);
        this.mail = new SimpleStringProperty(mail);
        this.address = new SimpleStringProperty(address);

    }
    
    public String getId()
    {
        return id.get();
    }
        
    public void setId(String id)
    {
        this.id.set(id);
    }
    
    public String getName()
    {
        return name.get();
    }
    
    public void setName(String name)
    {
        this.name.set(name);
    }
    
    public String getCity()
    {
        return city.get();
    }
    
    public void setCity(String city)
    {
        this.city.set(city);
    }
    
    public String getFieldActivity()
    {
        return fieldActivity.get();
    }
    
    public void setFieldActivity(String fieldActivity)
    {
        this.fieldActivity.set(fieldActivity);
    }

    public String getPhone()
    {
        return phone.get();
    }
    
    public void setPhone(String phone)
    {
        this.phone.set(phone);
    }

    public String getMail()
    {
        return mail.get();
    }
    
    public void setMail(String mail)
    {
        this.mail.set(mail);
    }

    public String getAddress()
    {
        return address.get();
    }
    
    public void setAddress(String address)
    {
        this.address.set(address);
    }
}