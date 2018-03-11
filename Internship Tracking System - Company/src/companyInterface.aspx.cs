using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Internship_Tracking_System___Company
{
    public partial class companyInterface : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Convert.ToBoolean(Session["giris"]) != true)
            {
                Response.Redirect("companyLogin.aspx");
            }

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            Response.Redirect("showInternshipRequest.aspx");
        }

        protected void Button2_Click(object sender, EventArgs e)
        {
            Response.Redirect("showInternshipAndBook.aspx");
        }


    }
}