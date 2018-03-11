using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Internship_Tracking_System___Company
{
    public partial class internshipBook : System.Web.UI.Page
    {
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
                    DataSet gBD = DBOperations.getBookDate();

                    DropDownList1.DataTextField = "pageDate";
                    DropDownList1.DataValueField = "bookpageID";
                    DropDownList1.DataSource = gBD.Tables[0];
                    DropDownList1.DataBind();
                }
            }
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            Response.Redirect("showInternshipAndBook.aspx");
        }

        protected void Button2_Click(object sender, EventArgs e)
        {
            string temp = "confirm";

            DBOperations.setInternshipBookConfrim(temp);
        }

        protected void Button3_Click(object sender, EventArgs e)
        {
            string temp = "decline";

            DBOperations.setInternshipBookConfrim(temp);
        }

        protected void Button4_Click(object sender, EventArgs e)
        {

        }

        protected void DropDownList1_SelectedIndexChanged(object sender, EventArgs e)
        {
            int selectedDateIndex = Int32.Parse(DropDownList1.SelectedValue.ToString());

            string pageText = DBOperations.getBookPage(selectedDateIndex);

            Label2.Text = pageText;
        }
    }
}