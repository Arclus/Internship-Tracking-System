using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Internship_Tracking_System___Company
{
    public partial class companyLogin : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            int companyID = Int32.Parse(TextBox1.Text);
            string companyPassword = TextBox2.Text;

            bool result = DBOperations.loginCheck(companyID, companyPassword);

            if (result == true)
            {
                Session["giris"] = true;
                Session["CompanyID"] = DBOperations.CompanyID;
            
                Response.Redirect("companyInterface.aspx");

            }
            else
            {
                Response.Write("Wrong ID or Password");
            }
        }
    }
}