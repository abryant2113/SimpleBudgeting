package simplebudget;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;


public class Landing {


    private String userFirstName;

    // fetches the landing scene that will be switched to after a successful login attempt
    public Scene getScene()
    {
        // TODO: 2/20/2018 Redesign this landing page

        GridPane headerGrid = new GridPane();
        headerGrid.setAlignment(Pos.CENTER);

        Label welcomeLabel = new Label("Welcome, " + getUserFirstName() + "!");
        welcomeLabel.setFont(new Font("Cambria", 15));
        headerGrid.getStyleClass().add("name-header");
        headerGrid.add(welcomeLabel, 0, 0);
        headerGrid.setPrefHeight(150);

        GridPane grid = new GridPane();

        Button addButton = new Button();
        Button deleteButton = new Button();

        addButton.setText("Add");
        deleteButton.setText("Delete");

        grid.setAlignment(Pos.BOTTOM_CENTER);
        grid.setHgap(90);
        grid.setPadding(new Insets(10, 0, 10, 0));
        grid.setPrefHeight(20);
        //grid.setId("header");
        grid.add(addButton, 0, 0);
        grid.add(deleteButton, 1, 0);
        //grid.getStyleClass().add("header-grid");

        GridPane contentGrid = new GridPane();
        contentGrid.setId("header");
        contentGrid.getStyleClass().add("table-grid");
        // allows table to stretch across window
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setPercentWidth(100);
        contentGrid.getColumnConstraints().add(colConstraints);

        TableView budgetTable = new TableView();
        budgetTable.setEditable(true);

        //TODO 2/23/18 - Now that this is working, this should be redesigned for scalability and user entry
        // current dev state for the table management.
        ObservableList <ExpenseData> dataValues = FXCollections.observableArrayList(new ExpenseData("Groceries", "459.29"));

        budgetTable.setItems(dataValues);

        TableColumn expenseType = new TableColumn("Expense Type");
        TableColumn expenseAmount = new TableColumn("Amount");

        expenseType.setCellValueFactory(new PropertyValueFactory<>("expenseType"));
        expenseAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        expenseType.prefWidthProperty().bind(budgetTable.widthProperty().divide(2));
        expenseAmount.prefWidthProperty().bind(budgetTable.widthProperty().divide(2));

        // adds test values to the table
        budgetTable.getColumns().addAll(expenseType, expenseAmount);

        contentGrid.add(budgetTable, 0, 0);

        // this will segment our different nodes throughout the application
        BorderPane pane = new BorderPane();
        pane.setId("pane");
        pane.setTop(headerGrid);
        pane.setCenter(grid);
        pane.setBottom(contentGrid);


        // sets scene and applies stylesheet
        Scene scene = new Scene(pane,800,600);
        scene.getStylesheets().add("Style.css");

        return scene;
    }

    // Expense data class that is used for holding data that will be inserted into our tableview
    public static class ExpenseData {

        private final SimpleStringProperty expenseType;
        private final SimpleStringProperty amount;

        private  ExpenseData(String expenseType, String amount){
            this.expenseType = new SimpleStringProperty(expenseType);
            this.amount = new SimpleStringProperty(amount);
        }

        public String getAmount() {
            return amount.get();
        }


        public String getExpenseType() {
            return expenseType.get();
        }
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }
}
