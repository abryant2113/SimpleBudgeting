package simplebudget;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.util.Callback;
import java.util.Optional;


public class Landing {


    private String userFirstName;
    private TableView budgetTable;
    private ObservableList <ExpenseData> dataValues;

    // fetches the landing scene that will be switched to after a successful login attempt
    public Scene getScene()
    {
        // TODO: 2/20/2018 Redesign this landing page

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        Label welcomeLabel = new Label("Welcome, " + getUserFirstName() + "!");
        welcomeLabel.getStyleClass().add("header-text");

        grid.getStyleClass().add("name-header");
        grid.add(welcomeLabel, 0, 0);
        grid.setPrefHeight(150);

        GridPane headerGrid = new GridPane();

        Button addButton = new Button();
        addButton.getStyleClass().add("option-button");

        Button deleteButton = new Button();
        deleteButton.getStyleClass().add("option-button");

        Button homeButton = new Button();
        homeButton.getStyleClass().add("option-button");

        Button settingsButton = new Button();
        settingsButton.getStyleClass().add("option-button");

        homeButton.setText("Home");
        settingsButton.setText("Settings");
        addButton.setText("Add");
        deleteButton.setText("Delete");

        headerGrid.setAlignment(Pos.BOTTOM_CENTER);
        headerGrid.setHgap(90);
        headerGrid.setPadding(new Insets(10, 0, 10, 0));
        headerGrid.setPrefHeight(40);

        headerGrid.add(homeButton, 0, 0);
        headerGrid.add(settingsButton, 1, 0);
        headerGrid.add(addButton, 2, 0);
        headerGrid.add(deleteButton, 3, 0);

        headerGrid.getStyleClass().add("header-grid");

        // allows the user to add a new expense to the table
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Dialog <ExpenseData> dialog = new Dialog<>();
                dialog.setTitle("Add Expense");
                dialog.setResizable(true);

                Label expenseLabel = new Label("Expense: ");
                Label amountLabel = new Label("Amount: ");

                TextField expenseField = new TextField();
                TextField amountField = new TextField();

                expenseField.requestFocus();

                GridPane dialogGrid = new GridPane();
                dialogGrid.setHgap(10);
                dialogGrid.setVgap(10);
                dialogGrid.add(expenseLabel, 1, 1);
                dialogGrid.add(expenseField, 2, 1);
                dialogGrid.add(amountLabel, 1, 2);
                dialogGrid.add(amountField, 2, 2);

                dialog.getDialogPane().setContent(dialogGrid);

                ButtonType buttonTypeOk = new ButtonType("Submit", ButtonBar.ButtonData.CANCEL_CLOSE);
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                dialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk, buttonTypeCancel);

                Button buttonOk = (Button) dialog.getDialogPane().lookupButton(buttonTypeOk);

                // properly aligns submit button on the dialog
                buttonOk.translateXProperty().bind(buttonOk.prefWidthProperty().divide(-2));

                dialog.setResultConverter(new Callback<ButtonType, ExpenseData>() {
                    @Override
                    public ExpenseData call(ButtonType b) {

                        if(b == buttonTypeOk){
                            return new ExpenseData(expenseField.getText(), amountField.getText());
                        }
                        return null;
                    }
                });

                Optional<ExpenseData> result = dialog.showAndWait();

                if(result.isPresent()){
                    ExpenseData newExpense = result.get();
                    dataValues.add(newExpense);
                    budgetTable.setItems(dataValues);

                }
                else{
                    // data wasn't retrieved correctly or was empty -- break out of function
                    return;
                }


            }
        });

        GridPane contentGrid = new GridPane();
        contentGrid.setId("header");
        contentGrid.getStyleClass().add("table-grid");

        // allows table to stretch across window
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setPercentWidth(100);
        contentGrid.getColumnConstraints().add(colConstraints);

        budgetTable = new TableView();
        budgetTable.setEditable(true);

        //TODO 2/23/18 - Now that this is working, this should be redesigned for scalability and user entry
        // creates the datavalues list that will hold each user's expense types and amounts
        dataValues = FXCollections.observableArrayList();

        budgetTable.setItems(dataValues);

        TableColumn expenseType = new TableColumn("Expense Type");
        TableColumn expenseAmount = new TableColumn("Amount");

        // sets the property values for the table
        expenseType.setCellValueFactory(new PropertyValueFactory<>("expenseType"));
        expenseAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        // splits width evenly between the two table columns
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
