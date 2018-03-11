package internship.tracking.system.student;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Arclus
 */
public class RequestCompany
{
    static ObservableList<CompanyTable> data = null;
    static TableView<CompanyTable> table = null;
    CompanyTable tableSelectedCompanyIndex = null;
    
    void start(Stage primaryStage)
    {
        primaryStage.setX(407);
        primaryStage.setY(263);
        primaryStage.setTitle("Request to Company");
        
        GridPane grid = new GridPane();
        
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Label cLabel = new Label("City : ");
        grid.add(cLabel, 2, 1);
        final ComboBox cLabel2 = new ComboBox();
        grid.add(cLabel2, 3, 1);
        
        Button sButton = new Button("Send");
        sButton.setMaxSize(100, 100);
        grid.add(sButton, 3, 2);
        
        Button bButton = new Button("Back");
        bButton.setMaxSize(100, 100);
        grid.add(bButton, 3, 0);
        
        table = new TableView<>();
        data = FXCollections.observableArrayList();
        
        TableColumn column1 = new TableColumn("COMPANY NAME");
        column1.setMinWidth(150);
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn column2 = new TableColumn("CITY");
        column2.setMinWidth(100);
        column2.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn column3 = new TableColumn("FIELD OF ACTIVITY");
        column3.setMinWidth(150);
        column3.setCellValueFactory(new PropertyValueFactory<>("fieldActivity"));

        TableColumn column4 = new TableColumn("PHONE NUMBER");
        column4.setMinWidth(120);
        column4.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn column5 = new TableColumn("E-MAIL");
        column5.setMinWidth(120);
        column5.setCellValueFactory(new PropertyValueFactory<>("mail"));
        
        TableColumn column6 = new TableColumn("ADDRESS");
        column6.setMinWidth(150);
        column6.setCellValueFactory(new PropertyValueFactory<>("address"));

        table.getColumns().addAll(column1, column2, column3, column4, column5, column6);
        
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
        {
            if (newSelection != null)
            {
                tableSelectedCompanyIndex = table.getSelectionModel().getSelectedItem();
                System.out.println(tableSelectedCompanyIndex.getId());
            } 
        });
        
        grid.add(table, 1, 1);
        

        String cB = DBOperations.getCity();
         
        if (cB.equals("1"))
        {
            cLabel2.setItems(DBOperations.cOptions);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Information of Alert");
            alert.setContentText(cB);
            alert.show();
        }
         
        cLabel2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1)
            {
                if (t1 != null)
                {
                    DBOperations.getCompany(cLabel2.getSelectionModel().getSelectedItem().toString());
                }
            }
        });
        
        bButton.setOnAction((ActionEvent e)
                -> 
                {
                    StudentInterface sI = new StudentInterface();
                    sI.start(primaryStage);
                    
        });
        
        sButton.setOnAction((ActionEvent e)
                -> 
                {
                    String a = DBOperations.requestInternCompany(InternshipTrackingSystemStudent.studentNumber, Integer.parseInt(tableSelectedCompanyIndex.getId()));
                    System.out.println(a);
        });
        
        Scene scene = new Scene(grid, 1100, 433);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}