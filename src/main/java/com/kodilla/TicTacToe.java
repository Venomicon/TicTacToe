package com.kodilla;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.Random;

public class TicTacToe extends Application implements EventHandler<ActionEvent> {
    private GameButton[] buttons = new GameButton[9];
    private Label label = new Label();
    private Random generator = new Random();
    private GridPane grid = new GridPane();
    private boolean singlePlayer = true;
    private String p1 = "Player";
    private String p2 = "COM";
    private boolean playerTurn = true;

    public static void main(String args[]) {
        launch(args);
    }

    private void cleanup(){
        label.setText("");
        for(int i=0; i<9; i++) {
            buttons[i].setState(0);
            buttons[i].setDisable(false);
        }
    }

    private void startGame(Stage stage) {
        HBox hBox = new HBox(label);
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.setPadding(new Insets(10, 0, 0, 200));
        label.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 20));

        grid.setGridLinesVisible(true);
        grid.setPrefWidth(200);
        grid.setPrefHeight(200);
        grid.setAlignment(Pos.BOTTOM_CENTER);

        GameButton b1 = new GameButton();
        GameButton b2 = new GameButton();
        GameButton b3 = new GameButton();
        GameButton b4 = new GameButton();
        GameButton b5 = new GameButton();
        GameButton b6 = new GameButton();
        GameButton b7 = new GameButton();
        GameButton b8 = new GameButton();
        GameButton b9 = new GameButton();

        buttons[0] = b1;
        buttons[1] = b2;
        buttons[2] = b3;
        buttons[3] = b4;
        buttons[4] = b5;
        buttons[5] = b6;
        buttons[6] = b7;
        buttons[7] = b8;
        buttons[8] = b9;

        for(int i=0; i<9; i++) {
            buttons[i].setOnAction(this);
        }

        grid.add(b1, 0, 0);
        grid.add(b2, 1, 0);
        grid.add(b3, 2, 0);
        grid.add(b4, 0, 1);
        grid.add(b5, 1, 1);
        grid.add(b6, 2, 1);
        grid.add(b7, 0, 2);
        grid.add(b8, 1, 2);
        grid.add(b9, 2, 2);

        Button newGame = new Button("New Game");
        newGame.setOnAction(event -> {
            restart(stage);
        });
        newGame.setPrefSize(200, 40);
        VBox tool = new VBox(newGame);
        tool.setPadding(new Insets(0,0,0,0));

        StackPane root = new StackPane();
        root.getChildren().addAll(tool, hBox, grid);

        Scene scene = new Scene(root, 600, 640);

        Button single = new Button();
        single.setOnAction(event -> stage.setScene(scene));
        single.setPrefSize(200,50);
        single.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        single.setText("Player VS Computer");

        Button multi = new Button();
        multi.setOnAction(event -> {
            singlePlayer = false;
            stage.setScene(scene);
        });
        multi.setPrefSize(200,50);
        multi.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        multi.setText("Player VS Player");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(single, multi);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(250,0,0,0));
        vBox.setSpacing(40);

        Label title = new Label("Tic Tac Toe");
        title.setFont(Font.font("Arial",FontPosture.ITALIC, 72));
        title.setAlignment(Pos.TOP_CENTER);
        title.setPadding(new Insets(0,0,300,0));


        StackPane mainRoot = new StackPane(title, vBox);
        Scene main = new Scene(mainRoot, 600, 600);

        stage.setTitle("Tic Tac Toe");
        stage.setResizable(false);
        stage.setScene(main);
        stage.show();
    }

    private void restart(Stage stage) {
        cleanup();
        startGame(stage);
    }

    @Override
    public void start(Stage primaryStage) {
        startGame(primaryStage);
    }

    @Override
    public void handle(ActionEvent event) {
        for(int i=0; i<9; i++) {
            if(buttons[i] == event.getSource()){
                if(singlePlayer) {
                    buttons[i].setState(1);
                    checkScore();
                    AI();
                } else if(playerTurn){
                    buttons[i].setState(1);
                    playerTurn = false;
                    checkScore();
                } else {
                    buttons[i].setState(-1);
                    playerTurn = true;
                    checkScore();
                }
            }
        }
    }

    private void checkScore() {
        if(!singlePlayer) {
            p1 = "Player 1";
            p2 = "Player 2";
        }
        //Row 1:
        if(buttons[0].getState() + buttons[1].getState() + buttons[2].getState() == 3) {
            label.setText(p1 + " won!");
            for(int i= 0; i<9; i++) {
                buttons[i].setDisable(true);
            }
        }
        if(buttons[0].getState() + buttons[1].getState() + buttons[2].getState() == -3) {
            label.setText(p2 + " won!");
            for(int i= 0; i<9; i++) {
                buttons[i].setDisable(true);
            }
        }
        //Row 2:
        if(buttons[3].getState() + buttons[4].getState() + buttons[5].getState() == 3) {
            label.setText(p1 + " won!");
            for(int i= 0; i<9; i++) {
                buttons[i].setDisable(true);
            }
        }
        if(buttons[3].getState() + buttons[4].getState() + buttons[5].getState() == -3) {
            label.setText(p2 + " won!");
            for(int i= 0; i<9; i++) {
                buttons[i].setDisable(true);
            }
        }
        //Row 3:
        if(buttons[6].getState() + buttons[7].getState() + buttons[8].getState() == 3) {
            label.setText(p1 + " won!");
            for(int i= 0; i<9; i++) {
                buttons[i].setDisable(true);
            }
        }
        if(buttons[6].getState() + buttons[7].getState() + buttons[8].getState() == -3) {
            label.setText(p2 + " won!");
            for(int i= 0; i<9; i++) {
                buttons[i].setDisable(true);
            }
        }
        //Column 1:
        if(buttons[0].getState() + buttons[3].getState() + buttons[6].getState() == 3) {
            label.setText(p1 + " won!");
            for(int i= 0; i<9; i++) {
                buttons[i].setDisable(true);
            }
        }
        if(buttons[0].getState() + buttons[3].getState() + buttons[6].getState() == -3) {
            label.setText(p2 + " won!");
            for(int i= 0; i<9; i++) {
                buttons[i].setDisable(true);
            }
        }
        //Column 2:
        if(buttons[1].getState() + buttons[4].getState() + buttons[7].getState() == 3) {
            label.setText(p1 + " won!");
            for(int i= 0; i<9; i++) {
                buttons[i].setDisable(true);
            }
        }
        if(buttons[1].getState() + buttons[4].getState() + buttons[7].getState() == -3) {
            label.setText(p2 + " won!");
            for(int i= 0; i<9; i++) {
                buttons[i].setDisable(true);
            }
        }
        //Column 3:
        if(buttons[2].getState() + buttons[5].getState() + buttons[8].getState() == 3) {
            label.setText(p1 + " won!");
            for(int i= 0; i<9; i++) {
                buttons[i].setDisable(true);
            }
        }
        if(buttons[2].getState() + buttons[5].getState() + buttons[8].getState() == -3) {
            label.setText(p2 + " won!");
            for(int i= 0; i<9; i++) {
                buttons[i].setDisable(true);
            }
        }
        //Diagonal 1:
        if(buttons[0].getState() + buttons[4].getState() + buttons[8].getState() == 3) {
            label.setText(p1 + " won!");
            for(int i= 0; i<9; i++) {
                buttons[i].setDisable(true);
            }
        }
        if(buttons[0].getState() + buttons[4].getState() + buttons[8].getState() == -3) {
            label.setText(p2 + " won!");
            for (int i = 0; i < 9; i++) {
                buttons[i].setDisable(true);
            }
        }
        //Diagonal 2:
        if(buttons[2].getState() + buttons[4].getState() + buttons[6].getState() == 3) {
            label.setText(p1 + " won!");
            for(int i= 0; i<9; i++) {
                buttons[i].setDisable(true);
            }
        }
        if(buttons[2].getState() + buttons[4].getState() + buttons[6].getState() == -3) {
            label.setText(p2 + " won!");
            for(int i= 0; i<9; i++) {
                buttons[i].setDisable(true);
            }
        }
        //Tie:
    }

    private void AI() {
        if(buttons[0].getState() + buttons[1].getState() == -2 || buttons[1].getState() + buttons[2].getState() == -2 || buttons[0].getState() + buttons[2].getState() == -2) {
            if(buttons[0].getState() == 0) {
                buttons[0].setState(-1);
                checkScore();
            } else if(buttons[1].getState() == 0) {
                buttons[1].setState(-1);
                checkScore();
            } else if(buttons[2].getState() == 0) {
                buttons[2].setState(-1);
                checkScore();
            }
        } else if(buttons[3].getState() + buttons[4].getState() == -2 || buttons[4].getState() + buttons[5].getState() == -2 || buttons[3].getState() + buttons[5].getState() == -2) {
            if(buttons[3].getState() == 0) {
                buttons[3].setState(-1);
                checkScore();
            } else if(buttons[4].getState() == 0) {
                buttons[4].setState(-1);
                checkScore();
            } else if(buttons[5].getState() == 0) {
                buttons[5].setState(-1);
                checkScore();
            }
        } else if(buttons[6].getState() + buttons[7].getState() == -2 || buttons[7].getState() + buttons[8].getState() == -2 || buttons[6].getState() + buttons[8].getState() == -2) {
            if(buttons[6].getState() == 0) {
                buttons[6].setState(-1);
                checkScore();
            } else if(buttons[7].getState() == 0) {
                buttons[7].setState(-1);
                checkScore();
            } else if(buttons[8].getState() == 0) {
                buttons[8].setState(-1);
                checkScore();
            }
        } else if(buttons[0].getState() + buttons[3].getState() == -2 || buttons[3].getState() + buttons[6].getState() == -2 || buttons[0].getState() + buttons[6].getState() == -2) {
            if(buttons[0].getState() == 0) {
                buttons[0].setState(-1);
                checkScore();
            } else if(buttons[3].getState() == 0) {
                buttons[3].setState(-1);
                checkScore();
            } else if(buttons[6].getState() == 0) {
                buttons[6].setState(-1);
                checkScore();
            }
        } else if(buttons[1].getState() + buttons[4].getState() == -2 || buttons[4].getState() + buttons[7].getState() == -2 || buttons[1].getState() + buttons[7].getState() == -2) {
            if(buttons[1].getState() == 0) {
                buttons[1].setState(-1);
                checkScore();
            } else if(buttons[4].getState() == 0) {
                buttons[4].setState(-1);
                checkScore();
            } else if(buttons[7].getState() == 0) {
                buttons[7].setState(-1);
                checkScore();
            }
        } else if(buttons[2].getState() + buttons[5].getState() == -2 || buttons[5].getState() + buttons[8].getState() == -2 || buttons[2].getState() + buttons[8].getState() == -2) {
            if(buttons[2].getState() == 0) {
                buttons[2].setState(-1);
                checkScore();
            } else if(buttons[5].getState() == 0) {
                buttons[5].setState(-1);
                checkScore();
            } else if(buttons[8].getState() == 0) {
                buttons[8].setState(-1);
                checkScore();
            }
        } else if(buttons[0].getState() + buttons[4].getState() == -2 || buttons[4].getState() + buttons[8].getState() == -2 || buttons[0].getState() + buttons[8].getState() == -2) {
            if(buttons[0].getState() == 0) {
                buttons[0].setState(-1);
                checkScore();
            } else if(buttons[4].getState() == 0) {
                buttons[4].setState(-1);
                checkScore();
            } else if(buttons[8].getState() == 0) {
                buttons[8].setState(-1);
                checkScore();
            }
        } else if(buttons[2].getState() + buttons[4].getState() == -2 || buttons[4].getState() + buttons[6].getState() == -2 || buttons[2].getState() + buttons[6].getState() == -2) {
            if (buttons[2].getState() == 0) {
                buttons[2].setState(-1);
                checkScore();
            } else if (buttons[4].getState() == 0) {
                buttons[4].setState(-1);
                checkScore();
            } else if (buttons[6].getState() == 0) {
                buttons[6].setState(-1);
                checkScore();
            }
        }else if(buttons[0].getState() + buttons[1].getState() == 2 || buttons[1].getState() + buttons[2].getState() == 2 || buttons[0].getState() + buttons[2].getState() == 2) {
            if(buttons[0].getState() == 0) {
                buttons[0].setState(-1);
                checkScore();
            } else if(buttons[1].getState() == 0) {
                buttons[1].setState(-1);
                checkScore();
            } else if(buttons[2].getState() == 0) {
                buttons[2].setState(-1);
                checkScore();
            } else {
                for(int x=0; x<100; x++) {
                    int random = generator.nextInt(9);
                    if(buttons[random].getState() == 0) {
                        buttons[random].setState(-1);
                        checkScore();
                        break;
                    }
                }
            }
        } else if(buttons[3].getState() + buttons[4].getState() == 2 || buttons[4].getState() + buttons[5].getState() == 2 || buttons[3].getState() + buttons[5].getState() == 2) {
            if(buttons[3].getState() == 0) {
                buttons[3].setState(-1);
                checkScore();
            } else if(buttons[4].getState() == 0) {
                buttons[4].setState(-1);
                checkScore();
            } else if(buttons[5].getState() == 0) {
                buttons[5].setState(-1);
                checkScore();
            } else {
                for(int x=0; x<100; x++) {
                    int random = generator.nextInt(9);
                    if(buttons[random].getState() == 0) {
                        buttons[random].setState(-1);
                        checkScore();
                        break;
                    }
                }
            }
        } else if(buttons[6].getState() + buttons[7].getState() == 2 || buttons[7].getState() + buttons[8].getState() == 2 || buttons[6].getState() + buttons[8].getState() == 2) {
            if(buttons[6].getState() == 0) {
                buttons[6].setState(-1);
                checkScore();
            } else if(buttons[7].getState() == 0) {
                buttons[7].setState(-1);
                checkScore();
            } else if(buttons[8].getState() == 0) {
                buttons[8].setState(-1);
                checkScore();
            } else {
                for(int x=0; x<100; x++) {
                    int random = generator.nextInt(9);
                    if(buttons[random].getState() == 0) {
                        buttons[random].setState(-1);
                        checkScore();
                        break;
                    }
                }
            }
        } else if(buttons[0].getState() + buttons[3].getState() == 2 || buttons[3].getState() + buttons[6].getState() == 2 || buttons[0].getState() + buttons[6].getState() == 2) {
            if(buttons[0].getState() == 0) {
                buttons[0].setState(-1);
                checkScore();
            } else if(buttons[3].getState() == 0) {
                buttons[3].setState(-1);
                checkScore();
            } else if(buttons[6].getState() == 0) {
                buttons[6].setState(-1);
                checkScore();
            } else {
                for(int x=0; x<100; x++) {
                    int random = generator.nextInt(9);
                    if(buttons[random].getState() == 0) {
                        buttons[random].setState(-1);
                        checkScore();
                        break;
                    }
                }
            }
        } else if(buttons[1].getState() + buttons[4].getState() == 2 || buttons[4].getState() + buttons[7].getState() == 2 || buttons[1].getState() + buttons[7].getState() == 2) {
            if(buttons[1].getState() == 0) {
                buttons[1].setState(-1);
                checkScore();
            } else if(buttons[4].getState() == 0) {
                buttons[4].setState(-1);
                checkScore();
            } else if(buttons[7].getState() == 0) {
                buttons[7].setState(-1);
                checkScore();
            } else {
                for(int x=0; x<100; x++) {
                    int random = generator.nextInt(9);
                    if(buttons[random].getState() == 0) {
                        buttons[random].setState(-1);
                        checkScore();
                        break;
                    }
                }
            }
        } else if(buttons[2].getState() + buttons[5].getState() == 2 || buttons[5].getState() + buttons[8].getState() == 2 || buttons[2].getState() + buttons[8].getState() == 2) {
            if(buttons[2].getState() == 0) {
                buttons[2].setState(-1);
                checkScore();
            } else if(buttons[5].getState() == 0) {
                buttons[5].setState(-1);
                checkScore();
            } else if(buttons[8].getState() == 0) {
                buttons[8].setState(-1);
                checkScore();
            } else {
                for(int x=0; x<100; x++) {
                    int random = generator.nextInt(9);
                    if(buttons[random].getState() == 0) {
                        buttons[random].setState(-1);
                        checkScore();
                        break;
                    }
                }
            }
        } else if(buttons[0].getState() + buttons[4].getState() == 2 || buttons[4].getState() + buttons[8].getState() == 2 || buttons[0].getState() + buttons[8].getState() == 2) {
            if(buttons[0].getState() == 0) {
                buttons[0].setState(-1);
                checkScore();
            } else if(buttons[4].getState() == 0) {
                buttons[4].setState(-1);
                checkScore();
            } else if(buttons[8].getState() == 0) {
                buttons[8].setState(-1);
                checkScore();
            } else {
                for(int x=0; x<100; x++) {
                    int random = generator.nextInt(9);
                    if(buttons[random].getState() == 0) {
                        buttons[random].setState(-1);
                        checkScore();
                        break;
                    }
                }
            }
        } else if(buttons[2].getState() + buttons[4].getState() == 2 || buttons[4].getState() + buttons[6].getState() == 2 || buttons[2].getState() + buttons[6].getState() == 2) {
            if(buttons[2].getState() == 0) {
                buttons[2].setState(-1);
                checkScore();
            } else if(buttons[4].getState() == 0) {
                buttons[4].setState(-1);
                checkScore();
            } else if(buttons[6].getState() == 0) {
                buttons[6].setState(-1);
                checkScore();
            } else {
                for(int x=0; x<100; x++) {
                    int random = generator.nextInt(9);
                    if(buttons[random].getState() == 0) {
                        buttons[random].setState(-1);
                        checkScore();
                        break;
                    }
                }
            }
        } else {
            for(int x=0; x<100; x++) {
                int random = generator.nextInt(9);
                if(buttons[random].getState() == 0) {
                    buttons[random].setState(-1);
                    checkScore();
                    break;
                }
            }
        }
    }
}

