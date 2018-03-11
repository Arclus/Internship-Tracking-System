package internship.tracking.system.student;

/**
 *
 * @author Arclus
 */

public class DataCalculate
{
    static int daysInMonth[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    
    static int dayCounter, day, month, year;;
   
    
    boolean isLeap(int y)
    {
        return (y % 4 == 0 && y % 100 != 0) || y % 400 == 0; 
    }
    
    void nextDay()
    {
        day += 1;
        dayCounter += 1;
        
        if (day > daysInMonth[month])
        {
            day = 1;
            month += 1;
            
            if (month > 12)
            {
                month = 1;
                year += 1;
                
                if (isLeap(year))
                {
                    daysInMonth[2] = 29;
                }
                else
                {
                    daysInMonth[2] = 28;
                }
            }
        }
    }

    void setDate(int d, int m, int y) 
    {
        if (m < 1)
        {
            m = 1;
        }
        
        if (m > 12)
        {
            m = 12;
        }

        if (d < 1)
        {
            d = 1;
        }
        
        if (d > daysInMonth[m])
        {
            d = daysInMonth[m];
        }
        
        if (isLeap(y))
        {
            daysInMonth[2] = 29;
        }    
        else
        {
            daysInMonth[2] = 28;
        }
        
        day = d;
        month = m;
        year = y;
    }

    void skipDays(int x)
    {
        for (int i = 0; i < x; i++)
        {
            nextDay();
        }
    }

    void printDate()
    {
        System.out.println(day);
        System.out.println(month);
        System.out.println(year);
    }
    
    public String go(int d, int m, int y, int increasedAmount)
    {
        String result = null;
        
        setDate(d, m, y);
        skipDays(increasedAmount);
        dayCounter = 0;
        
        printDate();
        
        result = "" + year + "-" + month + "-" + day + "";

        
        return result;
    }
}