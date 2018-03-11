package internship.tracking.system.student;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Arclus
 */

public class InternshipBook
{
    void start(Stage primaryStage)
    {
        primaryStage.setX(500);
        primaryStage.setY(250);
        primaryStage.setTitle("Internship Book");
        
        GridPane grid = new GridPane();
        
        //grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(10));
        
        Label textLabel = new Label("Enter the Date :"); 
        TextField tField = new TextField();
        tField.setPromptText("Year-Month-Day");
        
        HBox hButton = new HBox();
        hButton.setPadding(new Insets(10));
        hButton.setSpacing(80);
        
        hButton.getChildren().add(0, textLabel);
        hButton.getChildren().add(1, tField);
        
        grid.add(hButton, 3, 2);
        
        TextArea tA = new TextArea();
        grid.add(tA, 3, 3);
        
        VBox vButton = new VBox();
        vButton.setPadding(new Insets(10));
        vButton.setSpacing(150);
        
        Button bButton = new Button("Back");
        bButton.setMinHeight(50);
        bButton.setMinWidth(100);
        
        Button aButton = new Button("Add");
        aButton.setDisable(true);
        aButton.setMinHeight(50);
        aButton.setMinWidth(100);
                
        vButton.getChildren().add(0, aButton);
        vButton.getChildren().add(1, bButton);
        
        grid.add(vButton, 4, 3);
        
        tField.textProperty().addListener((observable, oldValue, newValue) ->
        {
            String tempDate;
            
            if (newValue.length() > 0)
            {
                aButton.setDisable(false);
                tempDate = tField.getText();
            } 
            
        });
        
        DBOperations.checkInternshipBookID();
        
        aButton.setOnAction((ActionEvent e)
                -> 
                {
                    String aIBP = DBOperations.addInternshipBookPage(tA.getText(), tField.getText());
                    
                    if (aIBP.equals("1"))
                    {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("CONFIRMATION");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText("Upload Successful");
                        alert.show();
                        
                        tA.setText("");
                        tField.setText("");
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
        
        bButton.setOnAction((ActionEvent e)
                -> 
                {
                    ShowIntern sI = new ShowIntern();
                    sI.start(primaryStage);
        });

        Scene scene = new Scene(grid, 800, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}