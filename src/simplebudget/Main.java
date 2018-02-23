package simplebudget;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.*;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        // keeps a reference to the primary stage
        this.primaryStage = primaryStage;

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        Text sceneTitle = new Text("Welcome");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        final TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        final PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button loginButton = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(loginButton);
        grid.add(hbBtn, 1, 4);

        // event handler that performs login for the user
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String user;

                // checks to make sure that credentials aren't empty -- will add check for validity later
                if(userTextField.getText().length() == 0 || pwBox.getText().length() == 0){
                    JOptionPane.showMessageDialog(null, "Invalid Credentials");
                    return;
                }
                else{
                    // grabs the current username to pass into the scene switch function
                    user = userTextField.getText();
                }
                changeScenes(user);
            }
        });
        // displays the current stage
        primaryStage.show();
    }

    // switches the scene from the login page to the main landing page
    private void changeScenes(String userName){

        Landing mainLanding = new Landing();
        mainLanding.setUserFirstName(userName);
        Scene scene = mainLanding.getScene();

        // sets up the title of the landing page and sets the scene
        primaryStage.setTitle("Welcome to your homepage!");
        primaryStage.setScene(scene);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
