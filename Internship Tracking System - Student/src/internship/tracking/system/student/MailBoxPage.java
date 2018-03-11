package internship.tracking.system.student;


import static internship.tracking.system.student.RequestCompany.table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Arclus
 */
public class MailBoxPage
{
    static ObservableList<MailTable> data = null;
    static TableView<MailTable> table = null;
    MailTable tableSelectedCompanyIndex = null;
    
    void start(Stage primaryStage)
    {
        primaryStage.setX(407);
        primaryStage.setY(263);
        primaryStage.setTitle("Mail Box");
        
        GridPane grid = new GridPane();
        
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        table = new TableView<>();
        data = FXCollections.observableArrayList();
        
        TableColumn column1 = new TableColumn("Mail ID");
        column1.setMinWidth(110);
        column1.setCellValueFactory(new PropertyValueFactory<>("mailID"));

        TableColumn column2 = new TableColumn("Sender");
        column2.setMinWidth(150);
        column2.setCellValueFactory(new PropertyValueFactory<>("senderMail"));

        TableColumn column3 = new TableColumn("Mail Subject");
        column3.setMinWidth(110);
        column3.setCellValueFactory(new PropertyValueFactory<>("mailSubject"));

        TableColumn column4 = new TableColumn("Mail Text");
        column4.setMinWidth(170);
        column4.setCellValueFactory(new PropertyValueFactory<>("mailText"));

        TableColumn column5 = new TableColumn("Mail Date");
        column5.setMinWidth(120);
        column5.setCellValueFactory(new PropertyValueFactory<>("mailDate"));
        
        table.getColumns().addAll(column1, column2, column3, column4, column5);
        
        VBox vButton = new VBox();
        vButton.setPadding(new Insets(10));
        vButton.setSpacing(75);
        
        Button bButton = new Button("Back");
        bButton.setMinHeight(50);
        bButton.setMinWidth(100);
        
        Button iButton = new Button("Inbox");
        iButton.setMinHeight(50);
        iButton.setMinWidth(100);
        
        Button oButton = new Button("Outbox");
        oButton.setMinHeight(50);
        oButton.setMinWidth(100);
        
        
        vButton.getChildren().add(0, iButton);
        vButton.getChildren().add(1, oButton);
        vButton.getChildren().add(2, bButton);
        
        grid.add(vButton, 2, 1);
        
        iButton.setOnAction((ActionEvent e)
                -> 
                {
                    data.clear();
                    table.refresh();
                    column2.setText("Sender");
                    DBOperations.getMailInbox();
                    
        });
        
        oButton.setOnAction((ActionEvent e)
                -> 
                {
                    data.clear();
                    table.refresh();
                    column2.setText("Receiver");
                    DBOperations.getMailOutbox();
                    
        });

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
        {
            if (newSelection != null)
            {
                tableSelectedCompanyIndex = table.getSelectionModel().getSelectedItem();
                System.out.println(tableSelectedCompanyIndex.getMailID());
            } 
        });
        
        grid.add(table, 1, 1);
        
        
        
        bButton.setOnAction((ActionEvent e)
                -> 
                {
                    StudentInterface sI = new StudentInterface();
                    sI.start(primaryStage);
                    
        });
        
        
        Scene scene = new Scene(grid, 900, 433);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}