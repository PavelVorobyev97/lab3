package lab3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

import javafx.stage.Stage;

public class Lab3 extends Application {
    //БД
    DBConn con = new DBConn();
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        TextField txt = new TextField("SELECT * FROM users WHERE id = 1");
        
        
        btn.setText("Execute!");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                con.executeMyQuerry(txt.getText());
            }
        });
        
        FlowPane root = new FlowPane();
         root.getChildren().add(txt);
        root.getChildren().add(btn);
       
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("SQL Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
