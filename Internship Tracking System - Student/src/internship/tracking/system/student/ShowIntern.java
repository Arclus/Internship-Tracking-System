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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Arclus
 */
public class ShowIntern
{
    public static int selectedInternship;
    void start(Stage primaryStage)
    {
        primaryStage.setX(407);
        primaryStage.setY(263);
        primaryStage.setTitle("Show Internship");
        
        GridPane grid = new GridPane();
        
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Label comboBoxLabel = new Label("Please Select the Internship");
        grid.add(comboBoxLabel, 0, 4);
        
        ComboBox iCombo = new ComboBox();
        grid.add(iCombo, 1, 4);
       
        
        Label internLabel = new Label("Internship Number");
        grid.add(internLabel, 0, 9);
        
        Label iLabel = new Label();
        grid.add(iLabel, 0, 10);
        
        Label pLabel = new Label("Program");
        grid.add(pLabel, 1, 9);
        
        Label pLabel2 = new Label();
        grid.add(pLabel2, 1, 10);
        
        Label cLabel = new Label("Company");
        grid.add(cLabel, 2, 9);
        
        Label copLabel = new Label();
        grid.add(copLabel, 2, 10);
        
        Label sDLabel = new Label("Start Date");
        grid.add(sDLabel, 3, 9);
        
        Label sDLabel2 = new Label();
        grid.add(sDLabel2, 3, 10);
        
        Label eDLabel = new Label("End Date");
        grid.add(eDLabel, 4, 9);
        
        Label eDLabel2 = new Label();
        grid.add(eDLabel2, 4, 10);
        
        Label aDLabel = new Label("Accepted Day");
        grid.add(aDLabel, 5, 9);
        
        Label aDLabel2 = new Label();
        grid.add(aDLabel2, 5, 10);
        
        Label rSLabel = new Label("Request Status");
        grid.add(rSLabel, 6, 9);
        
        Label rSLabel2 = new Label();
        grid.add(rSLabel2, 6, 10);
        
        Label iPLabel = new Label("Internship Paper");
        grid.add(iPLabel, 7, 9);
        
        Button iPLabel2 = new Button("Add");
        iPLabel2.setDisable(true);
        iPLabel2.setMaxSize(150, 150);
        grid.add(iPLabel2, 7, 10);
        
        Label sIPLabel = new Label("Show Internship Paper");
        grid.add(sIPLabel, 8, 9);
        
        Button sIPLabel2 = new Button("Show");
        sIPLabel2.setDisable(true);
        sIPLabel2.setMaxSize(250, 250);
        grid.add(sIPLabel2, 8, 10);
        
        Button bButton = new Button("Back");
        bButton.setMaxSize(150, 150);
        grid.add(bButton, 8, 0);
        
        
        bButton.setOnAction((ActionEvent e)
                -> 
                {
                    iCombo.getItems().clear();
                    StudentInterface sI = new StudentInterface();
                    sI.start(primaryStage);
        });
        
        String cI = DBOperations.checkInternship(InternshipTrackingSystemStudent.studentNumber);
        
        switch (cI)
        {
            case "0":
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("Information of Alert");
                    alert.setContentText("No internship to be displayed.");
                    alert.show();
                    
                    break;
                }
            case "1":
                iCombo.setItems(DBOperations.checkInternship);
                break;
            default:
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Information of Alert");
                    alert.setContentText(cI);
                    alert.show();
                    break;
                }
        }
        
        iCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1)
            {
                if (t1 != null)
                {
                    sIPLabel2.setDisable(false);
                    iPLabel2.setDisable(false);
                    
                    selectedInternship = Integer.parseInt(iCombo.getSelectionModel().getSelectedItem().toString());
                    
                    String gII = DBOperations.getInternshipInfo(Integer.parseInt(iCombo.getSelectionModel().getSelectedItem().toString()));

                    if (gII.equals("1"))
                    {
                        iLabel.setText(DBOperations.gIInfo.get(0).toString());
                        pLabel2.setText(DBOperations.gIInfo.get(1).toString());
                        copLabel.setText(DBOperations.gIInfo.get(2).toString());
                        sDLabel2.setText(DBOperations.gIInfo.get(3).toString());
                        eDLabel2.setText(DBOperations.gIInfo.get(4).toString());
                        aDLabel2.setText(DBOperations.gIInfo.get(5).toString());
                        
                        if (DBOperations.gIInfo.get(6).toString().equals("confirm") && DBOperations.gIInfo.get(7).toString().equals("confirm"))
                        {
                            rSLabel2.setText("Confirmed");
                        }
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText(gII);
                        alert.show();
                    }
                }
            }
        });
        
        
        iPLabel2.setOnAction((ActionEvent e)
                -> 
                {
                    iCombo.getItems().clear();
                    InternshipBook iB = new InternshipBook();
                    iB.start(primaryStage);
                    
        });
        
        sIPLabel2.setOnAction((ActionEvent e)
                -> 
                {
                    iCombo.getItems().clear();
                    InternshipBookShow iBS = new InternshipBookShow();
                    iBS.start(primaryStage);
        });
        
        Scene scene = new Scene(grid, 953, 433);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}