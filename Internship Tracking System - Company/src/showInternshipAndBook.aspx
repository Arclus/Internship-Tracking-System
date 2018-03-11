<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="showInternshipAndBook.aspx.cs" Inherits="Internship_Tracking_System___Company.showInternshipAndBook" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
        </div>
        <asp:GridView ID="GridView2" runat="server" AutoGenerateColumns="False" OnRowCommand="GridView2_RowCommand" Width="1461px" Height="172px">
            <Columns>
                <asp:TemplateField HeaderText="Internship ID">
                    <ItemTemplate>
                        <asp:Label ID="Label11" runat="server" Text='<%#Eval("internshipID")%>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="Student Number">
                    <ItemTemplate>
                        <asp:Label ID="Label" runat="server" Text='<%#Eval("studentID")%>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="Student Name">
                    <ItemTemplate>
                        <asp:Label ID="Label1" runat="server" Text='<%#Eval("studentName")%>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="Student Surname">
                    <ItemTemplate>
                        <asp:Label ID="Label2" runat="server" Text='<%#Eval("studentSurname")%>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="Student University">
                    <ItemTemplate>
                        <asp:Label ID="Label3" runat="server" Text='<%#Eval("universityName")%>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="Student Department">
                    <ItemTemplate>
                        <asp:Label ID="Label31" runat="server" Text='<%#Eval("departmentName")%>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="Student Class">
                    <ItemTemplate>
                        <asp:Label ID="Label4" runat="server" Text='<%#Eval("studentClass")%>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="Student Average">
                    <ItemTemplate>
                        <asp:Label ID="Label5" runat="server" Text='<%#Eval("studentAverage")%>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="Student Program">
                    <ItemTemplate>
                        <asp:Label ID="Label6" runat="server" Text='<%#Eval("studentProgram")%>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>         
                <asp:TemplateField HeaderText="Student Mail">
                    <ItemTemplate>
                        <asp:Label ID="Label7" runat="server" Text='<%#Eval("studentMail")%>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField>
                    <ItemTemplate>
                        <asp:Button ID="Button1" runat="server" Text="Internship Book" CommandName="Book" CommandArgument="<%#((GridViewRow)Container).RowIndex%>" OnClick="Button1_Click" Height="40px" Width="130px"/>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField>
                    <ItemTemplate>
                        <asp:Button ID="Button2" runat="server" CommandArgument="<%#((GridViewRow)Container).RowIndex %>" CommandName="Info" Height="40px" Text="More Info" Width="130px" />
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
        &nbsp;<br />
&nbsp;&nbsp;&nbsp;
        <br />
        <br />
        <br />
        <br />
        <asp:Button ID="Button3" runat="server" OnClick="Button3_Click" Text="&lt;&lt; Back" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </form>
</body>
</html>
