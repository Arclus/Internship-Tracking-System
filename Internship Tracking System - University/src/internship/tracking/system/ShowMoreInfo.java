
package internship.tracking.system;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Arclus
 */
public class ShowMoreInfo
{
    public void start(Stage primaryStage)
    {
        primaryStage.setX(650);
        primaryStage.setY(280);
        primaryStage.setTitle("Show Information");
        
        GridPane grid = new GridPane();
        
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        VBox vLabel = new VBox();
        vLabel.setPadding(new Insets(10));
        vLabel.setSpacing(30);
        
        VBox vLabel2 = new VBox();
        vLabel2.setPadding(new Insets(10));
        vLabel2.setSpacing(30);
        
        Label nameLabel = new Label("Student Name :");
        Label studentNumberLabel = new Label("Student Number :");
        Label departmantLabel = new Label("Student Department :");
        Label clasSLabel = new Label("Student Class :");
        Label averageLabel = new Label("Student Average :");
        Label programLabel = new Label("Student Program :");
        Label studentInsuranceLabel = new Label("Student Insurance :");
        Label mailLabel = new Label("Student Mail :");
        
        vLabel.getChildren().add(0, nameLabel);
        vLabel.getChildren().add(1, studentNumberLabel);
        vLabel.getChildren().add(2, departmantLabel);
        vLabel.getChildren().add(3, clasSLabel);
        vLabel.getChildren().add(4, averageLabel);
        vLabel.getChildren().add(5, programLabel);
        vLabel.getChildren().add(6, studentInsuranceLabel);
        vLabel.getChildren().add(7, mailLabel);
        
        Label nameLabel2 = new Label();
        Label studentNumberLabel2 = new Label();
        Label departmantLabel2 = new Label();
        Label clasSLabel2 = new Label();
        Label averageLabel2 = new Label();
        Label programLabel2 = new Label();
        Label studentInsuranceLabel2 = new Label();
        Label mailLabel2 = new Label();
        
        vLabel2.getChildren().add(0, nameLabel2);
        vLabel2.getChildren().add(1, studentNumberLabel2);
        vLabel2.getChildren().add(2, departmantLabel2);
        vLabel2.getChildren().add(3, clasSLabel2);
        vLabel2.getChildren().add(4, averageLabel2);
        vLabel2.getChildren().add(5, programLabel2);
        vLabel2.getChildren().add(6, studentInsuranceLabel2);
        vLabel2.getChildren().add(7, mailLabel2);
        
        grid.add(vLabel, 0, 0);
        grid.add(vLabel2, 1, 0);
        
        Button bButton = new Button("Back");
        bButton.setMinHeight(50);
        bButton.setMinWidth(100);
        
        bButton.setOnAction((ActionEvent e)
                -> 
                {
                    ShowInternshipAndBook sIAB = new ShowInternshipAndBook();
                    try
                    {
                        sIAB.start(primaryStage);
                    }
                    catch (SQLException ex)
                    {
                        Logger.getLogger(ShowMoreInfo.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });
        
        grid.add(bButton, 0, 1);
        
        String gSI = DBOperations.getStudentInfo();
        String temp;
        
        if (gSI.equals("1"))
        {
            temp = DBOperations.studentInfoList.get(0).toString() + " " + DBOperations.studentInfoList.get(1).toString();    
            nameLabel2.setText(temp);
            
            temp = DBOperations.studentInfoList.get(2).toString();
            studentNumberLabel2.setText(temp);
            
            temp = DBOperations.studentInfoList.get(3).toString();
            departmantLabel2.setText(temp);
            
            temp = DBOperations.studentInfoList.get(4).toString();
            clasSLabel2.setText(temp + ". Class");
            
            temp = DBOperations.studentInfoList.get(5).toString();
            averageLabel2.setText(temp);
            
            temp = DBOperations.studentInfoList.get(6).toString();
            programLabel2.setText(temp);
            
            temp = DBOperations.studentInfoList.get(7).toString();
            studentInsuranceLabel2.setText(temp);
            
            temp = DBOperations.studentInfoList.get(8).toString();
            mailLabel2.setText(temp);
            
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Information of Alert");
            alert.setContentText(gSI);
            alert.show();
        }

        Scene scene = new Scene(grid, 440, 470);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}