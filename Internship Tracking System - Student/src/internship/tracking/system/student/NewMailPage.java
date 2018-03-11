package internship.tracking.system.student;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
 
public class NewMailPage extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
        
    final Button button = new Button ("Send");
    final Button bbutton = new Button ("Back");
    final Label notification = new Label ();
    final TextField emailTF = new TextField("");
    final TextField subject = new TextField("");
    final TextField dateTF = new TextField("");
    final TextArea text = new TextArea ("");
    
    
    @Override public void start(Stage primaryStage)
    {
        primaryStage.setX(600);
        primaryStage.setY(350);
        primaryStage.setTitle("New Mail");
        Scene scene = new Scene(new Group(), 480, 280);

        
        dateTF.setPromptText("Year-Month-Day");
        
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("To: "), 0, 0);
        grid.add(emailTF, 1, 0);
        grid.add(new Label("Date: "), 2, 0);
        grid.add(dateTF, 3, 0);
        grid.add(new Label("Subject: "), 0, 1);
        grid.add(subject, 1, 1, 3, 1);            
        grid.add(text, 0, 2, 4, 1);
        grid.add(button, 0, 3);
        grid.add(bbutton, 1, 3);
        
        
        button.setOnAction((ActionEvent e)
                -> 
                {
                    String cUI = DBOperations.checkUniversityID(emailTF.getText());
                    
                    if (cUI.equals("0"))
                    {
                        String cCI = DBOperations.checkCompanyID(emailTF.getText());
                        
                        if (cCI.equals("0"))
                        {
                            Alert alert2 = new Alert(Alert.AlertType.ERROR);
                            alert2.setTitle("ERROR");
                            alert2.setHeaderText("Information of Alert");
                            alert2.setContentText(cCI);
                            alert2.show();
                        }
                        
                        else
                        {
                            String sMC = DBOperations.sendMailCompany(emailTF.getText(), subject.getText(),text.getText(),dateTF.getText());
                            
                            if (sMC.equals("1"))
                            {           
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Information");
                                alert.setHeaderText("Information of Alert");
                                alert.setContentText("Your message was successfully sent");
                                alert.show();
                                
                                emailTF.clear();
                                dateTF.clear();
                                subject.clear();
                                text.clear();
                            }
                            
                            else
                            {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("ERROR");
                                alert.setHeaderText("Information of Alert");
                                alert.setContentText(sMC);
                                alert.show();
                            }
                        }
                    }                
                    
                    else
                    {
                        String sM = DBOperations.sendMailUniversity(emailTF.getText(), subject.getText(),text.getText(),dateTF.getText());
                        
                        if (sM.equals("1"))   
                        {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information");
                            alert.setHeaderText("Information of Alert");
                            alert.setContentText("Your message was successfully sent");
                            alert.show();
                                
                            emailTF.clear();
                            dateTF.clear();
                            subject.clear();
                            text.clear();
                        }
                        
                        else
                        {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setHeaderText("Information of Alert");
                            alert.setContentText(sM);
                            alert.show();
                        }
                    }
        });
        
        
        bbutton.setOnAction((ActionEvent e)
                -> 
                {
                    StudentInterface sI = new StudentInterface();
                    sI.start(primaryStage);   
        });

        
        Group root = (Group)scene.getRoot();
        root.getChildren().add(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }    
}