using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;

namespace Internship_Tracking_System___Company
{

    public class DBOperations
    {
        public static String connString = ConfigurationManager.ConnectionStrings["MySQL"].ToString();
        public static string CompanyID = "";
        public static string companyMail = "";
        public static int internshipBookID;

        public static bool loginCheck(int companyID, string companyPassword)
        {
            MySqlConnection conn = new MySqlConnection(connString);

            string sql = "SELECT * FROM company WHERE companyID=@pcID and companyPassword=@pcPassword";

            MySqlCommand cmd = new MySqlCommand(sql, conn);

            cmd.Parameters.AddWithValue("@pcID", companyID);
            cmd.Parameters.AddWithValue("@pcPassword", companyPassword);

            MySqlDataAdapter adaptor = new MySqlDataAdapter();
            adaptor.SelectCommand = cmd;

            DataSet dS = new DataSet();

            conn.Open();
            adaptor.Fill(dS);
            conn.Close();

            try
            {
                CompanyID = dS.Tables[0].Rows[0][0].ToString();
            }

            catch (IndexOutOfRangeException)
            {

            }

            bool result = false;

            if (dS.Tables[0].Rows.Count > 0)
            {
                result = true;
            }
            else
            {
                result = false;
            }

            return result;
        }

        public static void setInternshipBookConfrim(string temp)
        {
            MySqlConnection conn = new MySqlConnection(connString);

            string sql = "update internshipbook set companyBookCheck=@psCBC where internshipBookID=@psIBID";

            MySqlCommand cmd = new MySqlCommand(sql, conn);

            cmd.Parameters.AddWithValue("@psCBC", temp);
            cmd.Parameters.AddWithValue("@psIBID", internshipBookID);


            conn.Open();
            cmd.ExecuteNonQuery();
            conn.Close();
        }

        public static string getBookPage(int bookPageID)
        {
            MySqlConnection conn = new MySqlConnection(connString);

            string sql = "select pageText from bookpage where bookpageID=@pBPID";

            MySqlCommand cmd = new MySqlCommand(sql, conn);

            cmd.Parameters.AddWithValue("@pBPID", bookPageID);

            MySqlDataAdapter adaptor = new MySqlDataAdapter(cmd);

            DataSet dS = new DataSet();

            conn.Open();
            adaptor.Fill(dS);
            conn.Close();

            string pageText = "";

            if (dS.Tables[0].Rows.Count > 0)
            {
                pageText = dS.Tables[0].Rows[0][0].ToString();
            }

            return pageText;

        }

        public static DataSet getBookDate()
        {
            MySqlConnection conn = new MySqlConnection(connString);

            string sql = "select pageDate,bookpageID from bookpage where internshipBookID=@pIBID";

            MySqlCommand cmd = new MySqlCommand(sql, conn);

            cmd.Parameters.AddWithValue("@pIBID", internshipBookID);

            MySqlDataAdapter adaptor = new MySqlDataAdapter(cmd);

            DataSet dS = new DataSet();

            conn.Open();
            adaptor.Fill(dS);
            conn.Close();

            return dS;
        }

        public static void checkInternshipBookID()
        {
            MySqlConnection conn = new MySqlConnection(connString);

            string sql = "select internshipBookID from internshipbook where internshipID=@psCID and studentID=@psSID";

            MySqlCommand cmd = new MySqlCommand(sql, conn);

            cmd.Parameters.AddWithValue("@psSID", showInternshipAndBook.studentID);
            cmd.Parameters.AddWithValue("@psCID", showInternshipAndBook.internshipID);

            MySqlDataAdapter adaptor = new MySqlDataAdapter();
            adaptor.SelectCommand = cmd;

            DataSet dS = new DataSet();

            conn.Open();
            adaptor.Fill(dS);
            conn.Close();

            if (dS.Tables[0].Rows.Count > 0)
            {
                internshipBookID = Int32.Parse(dS.Tables[0].Rows[0][0].ToString());
            }
        }

        public static DataSet getInternshipInfo()
        {
            MySqlConnection conn = new MySqlConnection(connString);

            string sql = "select internship.internshipID,student.studentID,student.studentName,student.studentSurname,student.studentAverage,student.studentMail,student.studentProgram,student.studentClass,university.universityName,department.departmentName,university.universityName from internship inner join student on internship.studentID = student.studentID inner join university on university.universityID = student.universityID inner join department on department.departmentID = student.departmentID where internship.companyID=@psCID";

            MySqlCommand cmd = new MySqlCommand(sql, conn);

            cmd.Parameters.AddWithValue("@psCID", CompanyID);


            MySqlDataAdapter adaptor = new MySqlDataAdapter();
            adaptor.SelectCommand = cmd;

            DataSet dS = new DataSet();

            conn.Open();
            adaptor.Fill(dS);
            conn.Close();

            return dS;
        }


        public static DataSet getInternshipRequest()
        {
            MySqlConnection conn = new MySqlConnection(connString);

            string sql = "select student.studentID,student.studentName,student.studentSurname,student.studentAverage,student.studentMail,student.studentProgram,student.studentClass,department.departmentName,university.universityName from internshiprequestcompany inner join student on internshiprequestcompany.studentID = student.studentID inner join university on university.universityID = student.universityID inner join department on department.departmentID = student.departmentID where internshiprequestcompany.companyID=@psCID";

            MySqlCommand cmd = new MySqlCommand(sql, conn);

            cmd.Parameters.AddWithValue("@psCID", CompanyID);


            MySqlDataAdapter adaptor = new MySqlDataAdapter();
            adaptor.SelectCommand = cmd;

            DataSet dS = new DataSet();

            conn.Open();
            adaptor.Fill(dS);
            conn.Close();


            return dS;
        }

        public static void getCompanyMailAddress()
        {
            MySqlConnection conn = new MySqlConnection(connString);

            string sql = "select companyMail from company where companyID=@psCID";

            MySqlCommand cmd = new MySqlCommand(sql, conn);

            cmd.Parameters.AddWithValue("@psCID", CompanyID);


            MySqlDataAdapter adaptor = new MySqlDataAdapter();
            adaptor.SelectCommand = cmd;

            DataSet dS = new DataSet();

            conn.Open();
            adaptor.Fill(dS);
            conn.Close();

            try
            {
                companyMail = dS.Tables[0].Rows[0][0].ToString();
            }

            catch (IndexOutOfRangeException)
            {

            }
        }

        public static string checkInternshipRequestConfrim(int studentID)
        {
            MySqlConnection conn = new MySqlConnection(connString);

            string sql = "select companyRequestCheck from internshiprequestcompany where companyID=@pCID and studentID=@pSID";

            MySqlCommand cmd = new MySqlCommand(sql, conn);

            cmd.Parameters.AddWithValue("@pCID", CompanyID);
            cmd.Parameters.AddWithValue("@pSID", studentID);

            MySqlDataAdapter adaptor = new MySqlDataAdapter();
            adaptor.SelectCommand = cmd;

            DataSet dS = new DataSet();

            conn.Open();
            adaptor.Fill(dS);
            conn.Close();

            string temp = "";

            string result = "";

            if (dS.Tables[0].Rows.Count > 0)
            {
                temp = dS.Tables[0].Rows[0][0].ToString();

                if (temp == "confirm")
                {
                    result = "1";
                }
                else if (temp == "decline")
                {
                    result = "0";
                }
                else
                {
                    result = "2";
                }
            }
            else
            {
                result = "2";
            }

            return result;
        }

        public static void sendMailInternshipRequest(string mailText, string mailSubject, string mailDate, string studentMail)
        {
            MySqlConnection conn = new MySqlConnection(connString);

            string sql = "INSERT INTO mailbox(mailSubject,mailText,mailDate,senderMail,receiverMail) VALUES(@pMS,@pMT,@pMD,@pCM,@pSM)";

            MySqlCommand cmd = new MySqlCommand(sql, conn);

            cmd.Parameters.AddWithValue("@pMS", mailSubject);
            cmd.Parameters.AddWithValue("@pMT", mailText);
            cmd.Parameters.AddWithValue("@pMD", mailDate);
            cmd.Parameters.AddWithValue("@pCM", companyMail);
            cmd.Parameters.AddWithValue("@pSM", studentMail);

            conn.Open();
            cmd.ExecuteNonQuery();
            conn.Close();
        }

        public static void setInternshipRequest(string confirm, int studentID)
        {
            MySqlConnection conn = new MySqlConnection(connString);

            string sql = "update internshiprequestcompany set companyRequestCheck = @pcRC where studentID = @psID and companyID = @pCID";

            MySqlCommand cmd = new MySqlCommand(sql, conn);

            cmd.Parameters.AddWithValue("@pcRC", confirm);
            cmd.Parameters.AddWithValue("@psID", studentID);
            cmd.Parameters.AddWithValue("@pCID", CompanyID);

            conn.Open();
            cmd.ExecuteNonQuery();
            conn.Close();
        }
    }
}