package internship.tracking.system.student;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Arclus
 */

public class RequestUniversity
{
    int selectedWeekDayNumber = 0;
    int wroteInternshipDayNumber =0;
    int increasedAmount = 0;

    void start(Stage primaryStage)
    {
        primaryStage.setX(407);
        primaryStage.setY(263);
        primaryStage.setTitle("Request to University");
        
        GridPane grid = new GridPane();
        
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));

        
        Label cNLabel = new Label("Company Name : ");
        grid.add(cNLabel, 1, 1);
        TextField cNLabel2 = new TextField();
        grid.add(cNLabel2, 2, 1);
        
        Label cLabel = new Label("City : ");
        grid.add(cLabel, 1, 2);
        ComboBox cLabel2 = new ComboBox();
        grid.add(cLabel2, 2, 2);
               
        Label cFLabel = new Label("Company field of activity : ");
        grid.add(cFLabel, 1, 3);
        TextField cFLabel2 = new TextField();
        grid.add(cFLabel2, 2, 3);
        
        Label cEMLabel = new Label("Company E-Mail : ");
        grid.add(cEMLabel, 1, 4);
        TextField cEMLabel2 = new TextField();
        grid.add(cEMLabel2, 2, 4);
        
        Label cPLabel = new Label("Company Phone Number : ");
        grid.add(cPLabel, 1, 5);
        TextField cPLabel2 = new TextField();
        grid.add(cPLabel2, 2, 5);
        
        Label cALabel = new Label("Company Adress : ");
        grid.add(cALabel, 1, 6);
        TextField cALabel2 = new TextField();
        grid.add(cALabel2, 2, 6);
        
        
        Label pLabel = new Label("Program : ");
        grid.add(pLabel, 4, 1);
        ComboBox pLabel2 = new ComboBox();
        pLabel2.getItems().addAll("Major","Second Major");
        grid.add(pLabel2, 5, 1);
        
        
        Label iLabel = new Label("Internship Term : ");
        grid.add(iLabel, 4, 2);
        ComboBox iLabel2 = new ComboBox();
        iLabel2.getItems().addAll("1","2","3","4","5");
        grid.add(iLabel2, 5, 2);
        
        Label tLabel = new Label("Number of Days Internship : ");
        grid.add(tLabel, 4, 3);
        TextField tLabel2 = new TextField();
        grid.add(tLabel2, 5, 3);
        
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
        
        String cCC = DBOperations.checkCompanyConfirm(InternshipTrackingSystemStudent.studentNumber);
        
        String temp = "";
        
        if (cCC.equals("confirm"))
        {
            String gRI = DBOperations.getRequestInternInfo(InternshipTrackingSystemStudent.studentNumber);
            
            if (gRI.equals("1"))
            {
                cNLabel2.setEditable(false);
                cLabel2.setEditable(false);
                cFLabel2.setEditable(false);
                cEMLabel2.setEditable(false);
                cPLabel2.setEditable(false);
                cALabel2.setEditable(false);
                
                temp = DBOperations.internshipRequestUniversity.get(0).toString();
                cNLabel2.setText(temp);
                
                cLabel2.setItems(DBOperations.iRUC);
                cLabel2.setValue(DBOperations.iRUC.toString());
                
                temp = DBOperations.internshipRequestUniversity.get(1).toString();
                cFLabel2.setText(temp);
                
                temp = DBOperations.internshipRequestUniversity.get(2).toString();
                cEMLabel2.setText(temp);
                
                temp = DBOperations.internshipRequestUniversity.get(3).toString();
                cPLabel2.setText(temp);
                
                temp = DBOperations.internshipRequestUniversity.get(4).toString();
                cALabel2.setText(temp);
                
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Information of Alert");
                alert.setContentText(gRI);
                alert.show();
            }  
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Information of Alert");
            alert.setContentText(cCC);
            alert.show();
        }
        
        tLabel2.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue.length() > 0)
            {
                wroteInternshipDayNumber = Integer.parseInt(tLabel2.getText().toString());
            } 
            
        });
        
        Label wDLabel = new Label("Number of Days per Week Internship : ");
        grid.add(wDLabel, 4, 4);
        
        final ToggleGroup group = new ToggleGroup();
        RadioButton button1 = new RadioButton("5");
        button1.setToggleGroup(group);
        button1.setSelected(true);
        RadioButton button2 = new RadioButton("6");
        button2.setToggleGroup(group);
        button2.setSelected(false);
        
        HBox box = new HBox(20, button1,button2);
        grid.add(box, 5, 4);
        
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        
        group.getSelectedToggle().selectedProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1)
            {
                if (t1 != null)
                {            
                    selectedWeekDayNumber = Integer.parseInt(selectedRadioButton.getText());
                    
                    if((wroteInternshipDayNumber == 12) && (selectedWeekDayNumber == 5))
                    {
                        increasedAmount = wroteInternshipDayNumber + 2;
                    }
                    else if((wroteInternshipDayNumber == 12) && (selectedWeekDayNumber == 6))
                    {
                        increasedAmount = wroteInternshipDayNumber + 1;
                    }
                    else if((wroteInternshipDayNumber == 24) && (selectedWeekDayNumber == 5))
                    {
                        increasedAmount = wroteInternshipDayNumber + 8;
                    }
                    else if((wroteInternshipDayNumber == 24) && (selectedWeekDayNumber == 6))
                    {
                        increasedAmount = wroteInternshipDayNumber + 3;
                    }
                }
            }
        });
        
        
        Label weekLabel = new Label("Internship Start Date : ");
        grid.add(weekLabel, 4, 5);
        
        DatePicker rDatePicker = new DatePicker();
        rDatePicker.setPromptText("Year-Month-Day");
        rDatePicker.setShowWeekNumbers(true);

        Callback<DatePicker, DateCell> dayCellFactory = this.getDayCellFactory();
        rDatePicker.setDayCellFactory(dayCellFactory);
        grid.add(rDatePicker, 5, 5); 
        
        Label week2Label = new Label("Internship End Date : ");
        grid.add(week2Label, 4, 6);
        TextField week2Label2 = new TextField();
        week2Label2.setEditable(false);
        grid.add(week2Label2, 5, 6);
        
        DataCalculate dC = new DataCalculate();
        
        rDatePicker.setOnAction((event) ->
        {

            if (rDatePicker.getValue() != null)
            {
                week2Label2.setText(dC.go(rDatePicker.getValue().getDayOfMonth(), rDatePicker.getValue().getMonth().getValue(), rDatePicker.getValue().getYear(), increasedAmount));
            }

        });
        
        
        Button bButton = new Button("Back");
        Button sButton = new Button("Send");
        
        grid.add(sButton, 5, 7);
        grid.add(bButton, 2, 7);
        
        
        bButton.setOnAction((ActionEvent e)
                -> 
                {
                    StudentInterface sI = new StudentInterface();
                    sI.start(primaryStage);
                    
        });
        
        sButton.setOnAction((ActionEvent e)
                -> 
                {
                    String a = DBOperations.requestInternUniversity(InternshipTrackingSystemStudent.studentNumber, DBOperations.universityID, Integer.parseInt(iLabel2.getSelectionModel().getSelectedItem().toString()), Integer.parseInt(tLabel2.getText()), Integer.parseInt(selectedRadioButton.getText()), rDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), week2Label2.getText(),pLabel2.getSelectionModel().getSelectedItem().toString());
                    System.out.println(a);  
        });
        
        Scene scene = new Scene(grid, 959, 433);
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