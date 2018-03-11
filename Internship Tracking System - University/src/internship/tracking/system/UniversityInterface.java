package internship.tracking.system;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Arclus
 */

public class UniversityInterface
{
    public void start(Stage primaryStage)
    {
        primaryStage.setX(650);
        primaryStage.setY(350);
        primaryStage.setTitle("Interntship Tracking System - University Interface");
        
        GridPane grid = new GridPane();
        
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Button btn1 = new Button("Show Internship Request");
        Button btn2 = new Button("Show Internship and Book");
        Button btn4 = new Button("Logout");
        
        grid.add(btn1, 1, 2);
        grid.add(btn2, 6, 2);
        grid.add(btn4, 7, 0);
        
        btn1.setOnAction((ActionEvent e)
                -> 
                {
                    ShowInternshipRequest sIR = new ShowInternshipRequest();
                    try
                    {
                        sIR.start(primaryStage);
                    }
                    catch (SQLException ex)
                    {
                        Logger.getLogger(UniversityInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
        });
        
        btn2.setOnAction((ActionEvent e)
                -> 
                {
                    ShowInternshipAndBook sIAB = new ShowInternshipAndBook();
                    try
                    {       
                        sIAB.start(primaryStage);
                    }
                    catch (SQLException ex)
                    {
                        Logger.getLogger(UniversityInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });
                
        btn4.setOnAction((ActionEvent e)
                -> 
                {
                    InternshipTrackingSystem iTS = new InternshipTrackingSystem();
                    iTS.start(primaryStage);
                    
        });
        
        
        Scene scene = new Scene(grid, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}