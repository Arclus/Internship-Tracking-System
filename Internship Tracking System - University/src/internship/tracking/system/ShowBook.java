package internship.tracking.system;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class ShowBook
{
    public void start(Stage primaryStage)
    {
        primaryStage.setX(650);
        primaryStage.setY(350);
        primaryStage.setTitle("Show Book");
        
        GridPane grid = new GridPane();

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
        tA.setEditable(false);
        grid.add(tA, 3, 3);
        
        VBox vButton = new VBox();
        vButton.setPadding(new Insets(10));
        vButton.setSpacing(75);
        
        Button bButton = new Button("Back");
        bButton.setMinHeight(50);
        bButton.setMinWidth(100);
        
        Button confirmButton = new Button("Confirm");
        confirmButton.setDisable(true);
        confirmButton.setMinHeight(50);
        confirmButton.setMinWidth(100);
        
        Button declineButton = new Button("Decline");
        declineButton.setDisable(true);
        declineButton.setMinHeight(50);
        declineButton.setMinWidth(100);
        
        vButton.getChildren().add(0, confirmButton);
        vButton.getChildren().add(1, declineButton);
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
        
        dateCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1)
            {
                if (t1 != null)
                {
                    confirmButton.setDisable(false);
                    declineButton.setDisable(false);
                    
                    String gMFU = DBOperations.getMail(dateCombo.getSelectionModel().getSelectedItem().toString());
                    
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
        
        confirmButton.setOnAction((ActionEvent e)
                -> 
                {
                    String temp = "confirm";
                    String uIC = DBOperations.updateInternshipConfirm(temp);
                    
                    if (uIC.equals("1"))
                    {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("CONFIRMATION");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText("Confirmation Succesful");
                        alert.show();
                        
                        dateCombo.getItems().clear();
                        DBOperations.dOptions.clear();
                        
                        ShowInternshipAndBook sIAB = new ShowInternshipAndBook();
                        try
                        {
                            sIAB.start(primaryStage);
                        }
                        catch (SQLException ex)
                        {
                            Logger.getLogger(ShowBook.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText(uIC);
                        alert.show();
                    }
        });
        
        declineButton.setOnAction((ActionEvent e)
                -> 
                {
                    String temp2 = "decline";
                    String uIC = DBOperations.updateInternshipConfirm(temp2);
                    
                    if (uIC.equals("1"))
                    {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("CONFIRMATION");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText("Decline Succesful");
                        alert.show();
                        
                        dateCombo.getItems().clear();
                        DBOperations.dOptions.clear();
                        
                        ShowInternshipAndBook sIAB = new ShowInternshipAndBook();
                        try
                        {
                            sIAB.start(primaryStage);
                        }
                        catch (SQLException ex)
                        {
                            Logger.getLogger(ShowBook.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText(uIC);
                        alert.show();
                    }
        });
        
        bButton.setOnAction((ActionEvent e)
                -> 
                {
                    dateCombo.getItems().clear();
                    DBOperations.dOptions.clear();
                        
                    ShowInternshipAndBook sIAB = new ShowInternshipAndBook();
                    try
                    {
                        sIAB.start(primaryStage);
                    }
                    catch (SQLException ex)
                    {
                        Logger.getLogger(ShowBook.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });
        
        Scene scene = new Scene(grid, 753, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}