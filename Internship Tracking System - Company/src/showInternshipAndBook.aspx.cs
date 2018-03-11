using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Internship_Tracking_System___Company
{
    public partial class showInternshipAndBook : System.Web.UI.Page
    {
        public static int studentID, internshipID;
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
                    DataSet gII = DBOperations.getInternshipInfo();

                    GridView2.DataSource = gII.Tables[0];
                    GridView2.DataBind();
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

        protected void GridView2_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "Book")
            {
                int rowNo = Convert.ToInt32(e.CommandArgument);
                GridViewRow rowClass = GridView2.Rows[rowNo];

                studentID = Convert.ToInt32(((Label)rowClass.FindControl("Label")).Text);
                internshipID = Convert.ToInt32(((Label)rowClass.FindControl("Label11")).Text);

                DBOperations.checkInternshipBookID();
                Response.Redirect("internshipBook.aspx");
            }

            if (e.CommandName == "Info")
            {
                int rowNo = Convert.ToInt32(e.CommandArgument);
                GridViewRow rowClass = GridView2.Rows[rowNo];

                studentID = Convert.ToInt32(((Label)rowClass.FindControl("Label")).Text);
                internshipID = Convert.ToInt32(((Label)rowClass.FindControl("Label11")).Text);

                DBOperations.checkInternshipBookID();


                Response.Redirect("internshipInfo.aspx");
            }
        }
    }
}