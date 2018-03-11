package internship.tracking.system.student;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Arclus
 */
public class InternshipBookShow
{
    void start(Stage primaryStage)
    {
        primaryStage.setX(407);
        primaryStage.setY(263);
        primaryStage.setTitle("Internship Book Show");
        
        GridPane grid = new GridPane();
        
        //grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Label dateLabel = new Label("Select the Date :"); 
        ComboBox dateCombo = new ComboBox();
        
        HBox hButton = new HBox();
        hButton.setPadding(new Insets(10));
        hButton.setSpacing(80);
        
        hButton.getChildren().add(0, dateLabel);
        hButton.getChildren().add(1, dateCombo);
        
        grid.add(hButton, 3, 2);
        
        TextArea tA = new TextArea();
        grid.add(tA, 3, 3);
        
        VBox vButton = new VBox();
        vButton.setPadding(new Insets(10));
        vButton.setSpacing(75);
        
        Button bButton = new Button("Back");
        bButton.setMinHeight(50);
        bButton.setMinWidth(100);
        
        Button uButton = new Button("Update");
        uButton.setDisable(true);
        uButton.setMinHeight(50);
        uButton.setMinWidth(100);
        
        Button dButton = new Button("Delete");
        dButton.setDisable(true);
        dButton.setMinHeight(50);
        dButton.setMinWidth(100);
        
        vButton.getChildren().add(0, uButton);
        vButton.getChildren().add(1, dButton);
        vButton.getChildren().add(2, bButton);
        
        grid.add(vButton, 4, 3);
        
        DBOperations.checkInternshipBookID();
        String gD = DBOperations.getDate();
         
        if (gD.equals("1"))
        {
            dateCombo.setItems(DBOperations.dOptions);  
        }
        
        else if (gD.equals("0"))
        {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("ERROR");
            alert2.setHeaderText("Information of Alert");
            alert2.setContentText("There are no records to display.");
            alert2.show();
            
            dateCombo.setDisable(true);
        }
        
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Information of Alert");
            alert.setContentText(gD);
            alert.show();
        }
             
        uButton.setOnAction((ActionEvent e)
                -> 
                {
                    String aIBP = DBOperations.updateInternshipBookPage(tA.getText(), dateCombo.getSelectionModel().getSelectedItem().toString());
                    
                    if (aIBP.equals("1"))
                    {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("CONFIRMATION");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText("Update Successful");
                        alert.show();
                        
                        dateCombo.getItems().clear();
                        tA.setText("");
                        
                        String gD2 = DBOperations.getDate();

                        if (gD2.equals("1"))
                        {
                            dateCombo.setItems(DBOperations.dOptions);
                        }
                        
                        else if (gD2.equals("0"))
                        {
                            Alert alert2 = new Alert(Alert.AlertType.ERROR);
                            alert2.setTitle("ERROR");
                            alert2.setHeaderText("Information of Alert");
                            alert2.setContentText("There are no records to display.");
                            alert2.show();
                            
                            dateCombo.setDisable(true);
                        }
                        
                        else
                        {
                            Alert alert2 = new Alert(Alert.AlertType.ERROR);
                            alert2.setTitle("ERROR");
                            alert2.setHeaderText("Information of Alert");
                            alert2.setContentText(gD2);
                            alert2.show();
                        }
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText(aIBP);
                        alert.show();
                    }
        });
        
        dButton.setOnAction((ActionEvent e)
                -> 
                {
                    String dIBP = DBOperations.deleteInternshipBookPage(dateCombo.getSelectionModel().getSelectedItem().toString());
                    
                    if (dIBP.equals("1"))
                    {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("CONFIRMATION");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText("Delete Successful");
                        alert.show();
                        
                        dateCombo.getItems().clear();
                        tA.setText("");
                        
                        String gD2 = DBOperations.getDate();

                        if (gD2.equals("1"))
                        {
                            dateCombo.setItems(DBOperations.dOptions);
                        }
                        
                        else if (gD2.equals("0"))
                        {
                            Alert alert2 = new Alert(Alert.AlertType.ERROR);
                            alert2.setTitle("ERROR");
                            alert2.setHeaderText("Information of Alert");
                            alert2.setContentText("There are no records to display.");
                            alert2.show();
                            
                            dateCombo.setDisable(true);
                        }
                        
                        else
                        {
                            Alert alert2 = new Alert(Alert.AlertType.ERROR);
                            alert2.setTitle("ERROR");
                            alert2.setHeaderText("Information of Alert");
                            alert2.setContentText(gD);
                            alert2.show();
                        }
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText(dIBP);
                        alert.show();
                    }
        });
        
        dateCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1)
            {
                if (t1 != null)
                {
                    uButton.setDisable(false);
                    dButton.setDisable(false);
                    
                    String gMFU = DBOperations.getMailForUpdate(dateCombo.getSelectionModel().getSelectedItem().toString());
                    
                    if (gMFU.equals("1"))
                    {
                        tA.setText(DBOperations.mail);
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText(gMFU);
                        alert.show();
                    }
                }
            }
        });
        
        bButton.setOnAction((ActionEvent e)
                -> 
                {
                    ShowIntern sI = new ShowIntern();
                    sI.start(primaryStage);
        });
        
        
        Scene scene = new Scene(grid, 753, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}