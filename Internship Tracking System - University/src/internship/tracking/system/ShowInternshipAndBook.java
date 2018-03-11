package internship.tracking.system;

import static internship.tracking.system.ShowInternshipRequest.table;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Arclus
 */
public class ShowInternshipAndBook
{
    static ObservableList<InternshipTable> data = null;
    static TableView<InternshipTable> table = null;
    InternshipTable tableSelectedInternshipIndex = null;
    public static int tSelectedInternshipIndex, tSelectedStudentIndex;
    
    public void start(Stage primaryStage) throws SQLException
    {
        primaryStage.setX(250);
        primaryStage.setY(280);
        primaryStage.setTitle("Show Internship");
        
        GridPane grid = new GridPane();
        
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        HBox hButton = new HBox();
        hButton.setPadding(new Insets(10));
        hButton.setSpacing(100);
        
        Label dateLabel = new Label("Please Select the Start Date :");
        Label dateLabel2 = new Label("Please Select the End Date :");
        
        DatePicker rDatePicker = new DatePicker();
        rDatePicker.setPromptText("Year-Month-Day");
        rDatePicker.setShowWeekNumbers(true);

        Callback<DatePicker, DateCell> dayCellFactory = this.getDayCellFactory();
        rDatePicker.setDayCellFactory(dayCellFactory);
        
        DatePicker rDatePicker2 = new DatePicker();
        rDatePicker2.setEditable(false);
        rDatePicker2.setPromptText("Year-Month-Day");
        rDatePicker2.setShowWeekNumbers(true);

        Callback<DatePicker, DateCell> dayCellFactory2 = this.getDayCellFactory();
        rDatePicker2.setDayCellFactory(dayCellFactory2);
        
        hButton.getChildren().add(0, dateLabel);
        hButton.getChildren().add(1, rDatePicker);
        hButton.getChildren().add(2, dateLabel2);
        hButton.getChildren().add(3, rDatePicker2);
        
        grid.add(hButton, 0, 0);
        
        
        table = new TableView<>();
        data = FXCollections.observableArrayList();

        TableColumn column1 = new TableColumn("Student Number");
        column1.setMinWidth(100);
        column1.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        
        TableColumn column2 = new TableColumn("Student Name");
        column2.setMinWidth(130);
        column2.setCellValueFactory(new PropertyValueFactory<>("studentName"));

        TableColumn column3 = new TableColumn("Student Surname");
        column3.setMinWidth(130);
        column3.setCellValueFactory(new PropertyValueFactory<>("studentSurname"));
        
        TableColumn column4 = new TableColumn("Program");
        column4.setMinWidth(100);
        column4.setCellValueFactory(new PropertyValueFactory<>("program"));

        TableColumn column5 = new TableColumn("Company Name");
        column5.setMinWidth(120);
        column5.setCellValueFactory(new PropertyValueFactory<>("companyName"));

        TableColumn column6 = new TableColumn("Accepted Day");
        column6.setMinWidth(100);
        column6.setCellValueFactory(new PropertyValueFactory<>("acceptedDay"));
        
        TableColumn column7 = new TableColumn("Start Date");
        column7.setMinWidth(100);
        column7.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        
        TableColumn column8 = new TableColumn("Finish Date");
        column8.setMinWidth(100);
        column8.setCellValueFactory(new PropertyValueFactory<>("finishDate"));

        table.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8);
        
        VBox vButton = new VBox();
        vButton.setPadding(new Insets(10));
        vButton.setSpacing(100);
        
        Button showButton = new Button("Show Book");
        showButton.setDisable(true);
        showButton.setMinHeight(50);
        showButton.setMinWidth(100);
        
        Button informationButton = new Button("More Information");
        informationButton.setDisable(true);
        informationButton.setMinHeight(50);
        informationButton.setMinWidth(100);
        
        Button bButton = new Button("Back");
        bButton.setMinHeight(50);
        bButton.setMinWidth(100);
        
        vButton.getChildren().add(0, showButton);
        vButton.getChildren().add(1, informationButton);
        vButton.getChildren().add(2, bButton);
        
        grid.add(vButton, 1, 1);
        
        DBOperations.getInternship(InternshipTrackingSystem.universtiyID);
        
        
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
        {
            if (newSelection != null)
            {
                showButton.setDisable(false);
                informationButton.setDisable(false);
                tableSelectedInternshipIndex = table.getSelectionModel().getSelectedItem();
                tSelectedInternshipIndex = Integer.parseInt(tableSelectedInternshipIndex.getInternshipID());
                tSelectedStudentIndex = Integer.parseInt(tableSelectedInternshipIndex.getStudentID());
            } 
        });
        
        grid.add(table, 0, 1);
        
        rDatePicker.setOnAction((event) ->
        {
            if (rDatePicker.getValue() != null)
            {
                rDatePicker2.setEditable(true);
            }

        });
        
        rDatePicker2.setOnAction((event) ->
        {
            if (rDatePicker2.getValue() != null)
            {
                try
                {
                    data.clear();
                    table.refresh();
                    DBOperations.getInternshipWithDate(InternshipTrackingSystem.universtiyID, rDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), rDatePicker2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(ShowInternshipAndBook.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        
        showButton.setOnAction((ActionEvent e)
                -> 
                {
                    ShowBook sB = new ShowBook();
                    sB.start(primaryStage);
        });
        
        informationButton.setOnAction((ActionEvent e)
                -> 
                {
                    ShowMoreInfo sMI = new ShowMoreInfo();
                    sMI.start(primaryStage);
        });
        
        bButton.setOnAction((ActionEvent e)
                -> 
                {
                    UniversityInterface uI = new UniversityInterface();
                    uI.start(primaryStage);
        });
        
        Scene scene = new Scene(grid, 1200, 470);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Callback<DatePicker, DateCell> getDayCellFactory()
    {
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>()
        {
            @Override
            public DateCell call(final DatePicker datePicker)
            {
                return new DateCell()
                {
                    @Override
                    public void updateItem(LocalDate item, boolean empty)
                    {
                        super.updateItem(item, empty);

                        // Disable Monday, Tueday, Wednesday.
                        if (item.getDayOfWeek() == DayOfWeek.SUNDAY)
                        {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        
        return dayCellFactory;
    }
}