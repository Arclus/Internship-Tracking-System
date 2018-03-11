package internship.tracking.system.student;

import static java.awt.SystemColor.desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Arclus
 */

public class StudentInterface
{
    public void start(Stage primaryStage)
    {
        primaryStage.setX(407);
        primaryStage.setY(263);
        primaryStage.setTitle("Interntship Tracking System - Student Interface");
        
        
        GridPane grid = new GridPane();
        
        //grid.setAlignment(Pos.CENTER);
        grid.setHgap(25);
        grid.setVgap(25);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Label name = new Label("");
        grid.add(name, 0, 0);
        
        Label studentNumber = new Label();
        grid.add(studentNumber, 0, 1);
        
        Label departmant = new Label();
        grid.add(departmant, 0, 2);
        
        Label clasS = new Label();
        grid.add(clasS, 0, 3);
        
        Label average = new Label();
        grid.add(average, 0, 4);
        

        
        Button btn = new Button("New Request to Universtiy");  
        Button btn2 = new Button("New Request to Company");
        Button btn3 = new Button("Show Internship");
        Button btn5 = new Button("Upload CV");
        Button btn4 = new Button("Logout");
        Button btn6 = new Button("Mail Box");
        Button btn7 = new Button("New Mail");
        
        btn.setMinSize(170, 30);
        btn2.setMinSize(170, 30);
        btn3.setMinSize(100, 30);
        btn4.setMaxSize(100, 30);
        btn5.setMinSize(100, 30);
        btn6.setMinSize(100, 30);
        btn7.setMinSize(100, 30);
         
        
        HBox hbBtn = new HBox(1);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 2, 7);           
                
        HBox hbBtn4 = new HBox(1);
        hbBtn4.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn4.getChildren().add(btn5);
        grid.add(hbBtn4, 5, 7);
        
        HBox hbBtn2 = new HBox(1);
        hbBtn2.setAlignment(Pos.TOP_RIGHT);
        hbBtn2.getChildren().add(btn4);
        grid.add(hbBtn2, 8, 0);
        
        HBox hbBtn3 = new HBox(1);
        hbBtn3.setAlignment(Pos.CENTER);
        hbBtn3.getChildren().add(btn3);
        grid.add(hbBtn3, 5, 9);
        
        HBox hbBtn5 = new HBox(1);
        hbBtn5.setAlignment(Pos.CENTER);
        hbBtn5.getChildren().add(btn2);
        grid.add(hbBtn5, 2, 9);
        
        HBox hbBtn6 = new HBox(1);
        hbBtn6.setAlignment(Pos.TOP_RIGHT);
        hbBtn6.getChildren().add(btn6);
        grid.add(hbBtn6, 7, 7);
        
        HBox hbBtn7 = new HBox(1);
        hbBtn7.setAlignment(Pos.TOP_RIGHT);
        hbBtn7.getChildren().add(btn7);
        grid.add(hbBtn7, 7, 9);
        
        String gSI = DBOperations.getStudentInfo(InternshipTrackingSystemStudent.studentNumber);
        String temp;
        
        if (gSI.equals("1"))
        {
            temp = DBOperations.studentInfoList.get(0).toString() + " " + DBOperations.studentInfoList.get(1).toString();    
            name.setText(temp);
            
            temp = DBOperations.studentInfoList.get(2).toString();
            studentNumber.setText(temp);
            
            temp = DBOperations.studentInfoList.get(3).toString();
            clasS.setText(temp + ". Class");
            
            temp = DBOperations.studentInfoList.get(4).toString();
            average.setText(temp);
            
            temp = DBOperations.studentInfoList.get(5).toString();
            departmant.setText(temp);
            
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Information of Alert");
            alert.setContentText(gSI);
            alert.show();
        }
        
        
        btn.setOnAction((ActionEvent e)
                -> 
                {
                    RequestUniversity rU = new RequestUniversity();
                    rU.start(primaryStage);
                    
        });
        
        btn2.setOnAction((ActionEvent e)
                -> 
                {
                    RequestCompany rC = new RequestCompany();
                    rC.start(primaryStage);
                    
        });
        
        btn3.setOnAction((ActionEvent e)
                -> 
                {
                    ShowIntern sI = new ShowIntern();
                    sI.start(primaryStage);
                    
        });
        
        btn6.setOnAction((ActionEvent e)
                -> 
                {
                    MailBoxPage mBP = new MailBoxPage();
                    mBP.start(primaryStage);
                    
        });
        
        btn7.setOnAction((ActionEvent e)
                -> 
                {
                    NewMailPage nMP = new NewMailPage();
                    nMP.start(primaryStage);
                    
        });
        
        btn4.setOnAction((ActionEvent e)
                -> 
                {
                    InternshipTrackingSystemStudent iTSS = new InternshipTrackingSystemStudent();
                    iTSS.start(primaryStage);
                    
        });
        
        btn5.setOnAction((ActionEvent e)
                -> 
                {
                    FileChooser fileChooser = new FileChooser();

                    // Set Initial Directory to Desktop
                    fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));

                    // Set extension filter, only PDF files will be shown
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
                    fileChooser.getExtensionFilters().add(extFilter);

                    // Show open file dialog
                    File selectedFile = fileChooser.showOpenDialog(primaryStage);
                    
                    if (selectedFile != null)
                    {
			System.out.println("File selected: " + selectedFile.toString());
                    }
                    else
                    {
                        System.out.println("File save cancelled.");
                    }
                    /*if (file != null) {
                        openFile(file);
                    }

                    //Open PDF file
                    HostServices hostServices = getHostServices();
                    hostServices.showDocument(file.getAbsolutePath());*/
                    
        });
            
        
        Scene scene = new Scene(grid, 800, 433);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}