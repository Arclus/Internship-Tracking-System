package internship.tracking.system;

import static internship.tracking.system.InternshipTrackingSystem.universtiyID;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Arclus
 */

public class ShowInternshipRequest
{
    static ObservableList<InternshipRequestTable> data = null;
    static TableView<InternshipRequestTable> table = null;
    InternshipRequestTable tableSelectedInternshipRequestIndex = null;
    
    public void start(Stage primaryStage) throws SQLException
    {
        primaryStage.setX(250);
        primaryStage.setY(280);
        primaryStage.setTitle("Internship Request");
        
        GridPane grid = new GridPane();
        
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        table = new TableView<>();
        data = FXCollections.observableArrayList();
        
        TableColumn column1 = new TableColumn("Student Name");
        column1.setMinWidth(150);
        column1.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        
        TableColumn column2 = new TableColumn("Student Surname");
        column2.setMinWidth(130);
        column2.setCellValueFactory(new PropertyValueFactory<>("studentSurname"));

        TableColumn column3 = new TableColumn("University");
        column3.setMinWidth(150);
        column3.setCellValueFactory(new PropertyValueFactory<>("universityName"));

        TableColumn column4 = new TableColumn("Department");
        column4.setMinWidth(150);
        column4.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
        
        TableColumn column5 = new TableColumn("Program");
        column5.setMinWidth(100);
        column5.setCellValueFactory(new PropertyValueFactory<>("program"));

        TableColumn column6 = new TableColumn("Internship Term");
        column6.setMinWidth(120);
        column6.setCellValueFactory(new PropertyValueFactory<>("internshipTerm"));

        TableColumn column7 = new TableColumn("Internship Day");
        column7.setMinWidth(120);
        column7.setCellValueFactory(new PropertyValueFactory<>("internshipDay"));
        
        TableColumn column8 = new TableColumn("Days per Week");
        column8.setMinWidth(120);
        column8.setCellValueFactory(new PropertyValueFactory<>("internshipDayWeek"));
        
        TableColumn column9 = new TableColumn("Start Date");
        column9.setMinWidth(140);
        column9.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        
        TableColumn column10 = new TableColumn("Finish Date");
        column10.setMinWidth(140);
        column10.setCellValueFactory(new PropertyValueFactory<>("finishDate"));

        table.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9, column10);
        
        VBox vButton = new VBox();
        vButton.setPadding(new Insets(10));
        vButton.setSpacing(100);
        
        Button confirmButton = new Button("Confirm");
        confirmButton.setDisable(true);
        confirmButton.setMinHeight(50);
        confirmButton.setMinWidth(100);
        
        Button declineButton = new Button("Decline");
        declineButton.setDisable(true);
        declineButton.setMinHeight(50);
        declineButton.setMinWidth(100);
        
        Button bButton = new Button("Back");
        bButton.setMinHeight(50);
        bButton.setMinWidth(100);
        
        vButton.getChildren().add(0, confirmButton);
        vButton.getChildren().add(1, declineButton);
        vButton.getChildren().add(2, bButton);
        
        grid.add(vButton, 1, 0);
        
        DBOperations.getInternshipRequest(InternshipTrackingSystem.universtiyID);
        
        
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
        {
            if (newSelection != null)
            {
                declineButton.setDisable(false);
                confirmButton.setDisable(false);
                tableSelectedInternshipRequestIndex = table.getSelectionModel().getSelectedItem();
            } 
        });
        
        grid.add(table, 0, 0);
        
        confirmButton.setOnAction((ActionEvent e)
                -> 
                {
                    String value = "confirm";
                    String sIR = DBOperations.setInternshipRequest(value, Integer.parseInt(tableSelectedInternshipRequestIndex.getId()));
                    
                    if (sIR.equals("1"))
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("INFORMATION");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText("Intership Confirmed.");
                        alert.show();
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText(sIR);
                        alert.show();
                    }
                    
        });
        
        declineButton.setOnAction((ActionEvent e)
                -> 
                {
                    String value = "decline";
                    String sIR = DBOperations.setInternshipRequest(value, Integer.parseInt(tableSelectedInternshipRequestIndex.getId()));
                    
                    if (sIR.equals("1"))
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("INFORMATION");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText("Intership Declined.");
                        alert.show();
                        
                        
                        //OGRENCİYE MAİL GÖNDER BASKA FİRMADA STAJ YAPMASINI SÖYLE
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Information of Alert");
                        alert.setContentText(sIR);
                        alert.show();
                    }        
        });
        
        bButton.setOnAction((ActionEvent e)
                -> 
                {
                    UniversityInterface uI = new UniversityInterface();
                    uI.start(primaryStage);
        });
        
        Scene scene = new Scene(grid, 1500, 470);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}