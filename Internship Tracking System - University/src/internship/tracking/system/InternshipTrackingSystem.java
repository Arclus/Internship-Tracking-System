package internship.tracking.system;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Arclus
 */


public class InternshipTrackingSystem extends Application
{
    public static int universtiyID;
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setX(707);
        primaryStage.setY(223);
        primaryStage.setTitle("Interntship Tracking System - University Access");
        
        GridPane grid = new GridPane();
        
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label id = new Label("University ID :");
        grid.add(id, 0, 1);
        
        TextField idTextField = new TextField();
        idTextField.setPromptText("ID");
        grid.add(idTextField, 1, 1);
        
        Label pw = new Label("University Password :");
        grid.add(pw, 0, 2); 

        PasswordField pwBox = new PasswordField();
        pwBox.setPromptText("Password");
        grid.add(pwBox, 1, 2);
        

        Button btn = new Button("Login");    
        
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);
        
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction((ActionEvent e) ->
        {
            String result = DBOperations.loginCheck(idTextField.getText(), pwBox.getText());
            
            universtiyID = Integer.parseInt(idTextField.getText());
            
            if (idTextField.getText().equals("") || pwBox.getText().equals(""))
            {
                result = "2";
            }
            
            switch (result)
            {
                case "1":
                {
                    UniversityInterface uI = new UniversityInterface();
                    uI.start(primaryStage);
                    break;
                }
                
                case "2":
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Information Alert Example");
                    String s = "All Field" +
                            "Must" + " \n \n" +
                            "Enter!";
                    alert.setContentText(s);
                    alert.show();
                    break;
                }
                    
                case "0":
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Information Alert Example");
                    String s = "ID" +
                            "or " + " \n \n" +
                            "Password Wrong!";
                    alert.setContentText(s);
                    alert.show();
                    break;
                }
                default:
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Alert Information");
                    alert.setContentText(result);
                    alert.show();
                    break;
                } 
            }      
        });
        
        Scene scene = new Scene(grid, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}