package simplebudget;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.geometry.Insets;


public class Landing {


    private String userFirstName;

    // fetches the landing scene that will be switched to after a successful login attempt
    public Scene getScene()
    {
        Scene scene = new Scene(new Group(),450,250);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(5);
        grid.setPadding(new Insets(10, 0, 10, 0));

        Label welcomeLabel = new Label("Welcome, " + getUserFirstName() + "!");
        welcomeLabel.setFont(new Font("Cambria", 15));
        grid.add(welcomeLabel, 0, 0);
        grid.getStyleClass().add("header-grid");


        // this will segment our different nodes throughout the application
        BorderPane pane = new BorderPane();
        pane.setTop(grid);

        Group root = (Group)scene.getRoot();
        root.getChildren().add(pane);

        return scene;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }
}
