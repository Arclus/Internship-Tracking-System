using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Internship_Tracking_System___Company
{
    public partial class showInternshipRequest : System.Web.UI.Page
    {
        public static int studentID;
        public static string studentMail;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Convert.ToBoolean(Session["giris"]) != true)
            {
                Response.Redirect("companyLogin.aspx");
            }

            else
            {
                if (!IsPostBack)
                {
                    
                    DataSet gIR = DBOperations.getInternshipRequest();

                    GridView1.DataSource = gIR.Tables[0];
                    GridView1.DataBind();
                }
            } 
        }
        protected void Button1_Click(object sender, EventArgs e)
        {

        }

        protected void Button2_Click(object sender, EventArgs e)
        {

        }

        protected void Button3_Click(object sender, EventArgs e)
        {
            Response.Redirect("companyInterface.aspx");
        }

        protected void GridView1_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "Confirm")
            {
                string text = "Your internship application has been approved.";
                string text2 = "Internship";
                string text3 = DateTime.Now.ToString("yyyy-MM-dd");
                string temp = "confirm";
                string message = "Internship application already has been rejected.";
                string message2 = "Internship application already has been approved.";


                int rowNo = Convert.ToInt32(e.CommandArgument);
                GridViewRow rowClass = GridView1.Rows[rowNo];

                studentID = Convert.ToInt32(((Label)rowClass.FindControl("Label")).Text);

                string cIRC = DBOperations.checkInternshipRequestConfrim(studentID);

                if (cIRC == "1")
                {
                    Label10.Text = message2;
                }

                else if (cIRC == "0")
                {
                    Label10.Text = message;
                }

                else if (cIRC == "2")
                {
                    DBOperations.setInternshipRequest(temp, studentID);
                    DBOperations.getCompanyMailAddress();
                    studentMail = ((Label)rowClass.FindControl("Label7")).Text;

                    DBOperations.sendMailInternshipRequest(text, text2, text3, studentMail);

                    Label10.Text = "Internship application has been approved and information mail has been sent.";
                }
            }

            if (e.CommandName == "Decline")
            {
                string text = "Your internship application has been rejected.";
                string text2 = "Internship";
                string text3 = DateTime.Now.ToString("yyyy-MM-dd");

                string temp = "decline";

                string message = "Internship application already has been rejected.";
                string message2 = "Internship application already has been approved.";

                int rowNo = Convert.ToInt32(e.CommandArgument);
                GridViewRow rowClass = GridView1.Rows[rowNo];
                studentID = Convert.ToInt32(((Label)rowClass.FindControl("Label")).Text);

                string cIRC = DBOperations.checkInternshipRequestConfrim(studentID);

                if (cIRC == "1")
                {
                    Label10.Text = message2;
                }

                else if (cIRC == "0")
                {
                    Label10.Text = message;
                }

                else if (cIRC == "2")
                {
                    DBOperations.setInternshipRequest(temp, studentID);
                    DBOperations.getCompanyMailAddress();
                    studentMail = ((Label)rowClass.FindControl("Label7")).Text;

                    DBOperations.sendMailInternshipRequest(text, text2, text3, studentMail);

                    Label10.Text = "Internship application has been rejected and information mail has been sent.";
                }
            }
        }
    }
}