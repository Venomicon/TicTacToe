package com.kodilla;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class TicTacToe extends Application {
    //private Image imageback = new Image("file:src/main/resources/TicTacToe.png");
    //BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
    //BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    //Background background = new Background(backgroundImage);
    boolean PlayerTurn = true;

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label();
        HBox hBox = new HBox(label);
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.setPadding(new Insets(10, 0, 0, 0));
        label.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 20));

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.setPrefWidth(200);
        grid.setPrefHeight(200);
        grid.setAlignment(Pos.BOTTOM_CENTER);
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPrefWidth(200);
        grid.getColumnConstraints().addAll(cc);
        RowConstraints rc = new RowConstraints();
        rc.setPrefHeight(200);
        grid.getRowConstraints().addAll(rc);


        GameButton b1 = new GameButton();
        GameButton b2 = new GameButton();
        GameButton b3 = new GameButton();
        GameButton b4 = new GameButton();
        GameButton b5 = new GameButton();
        GameButton b6 = new GameButton();
        GameButton b7 = new GameButton();
        GameButton b8 = new GameButton();
        GameButton b9 = new GameButton();

        grid.add(b1, 0, 0);
        grid.add(b2, 1, 0);
        grid.add(b3, 2, 0);
        grid.add(b4, 0, 1);
        grid.add(b5, 1, 1);
        grid.add(b6, 2, 1);
        grid.add(b7, 0, 2);
        grid.add(b8, 1, 2);
        grid.add(b9, 2, 2);


        StackPane root = new StackPane();
        root.getChildren().addAll(hBox, grid);

        if (b1.getText().equals("X") && b2.getText().equals("X") && b3.getText().equals("X")) {
            label.setText("Player wins!");
        }


        Scene scene = new Scene(root, 600, 640);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public class GameButton extends Button {
        public GameButton() {
            Button b = new Button();
            b.setOnAction(event -> {
                if (PlayerTurn) {
                    b.setText("X");
                    b.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 72));
                    PlayerTurn = false;
                    b.setDisable(true);
                } else {
                    b.setText("O");
                    b.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 72));
                    PlayerTurn = true;
                    b.setDisable(true);
                }
            });
            b.setMinSize(200,200);
            b.setPrefSize(200,200);
            b.setOnMouseEntered(event -> {
                b.setCursor(Cursor.HAND);
            });
            b.setOnMouseExited(event -> {
                b.setCursor(Cursor.OPEN_HAND);
            });
        }
    }
}
