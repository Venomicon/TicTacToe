package com.kodilla;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameButton extends Button {
    private int state;

    public GameButton() {
        this.setState(0);
        this.setPrefSize(200, 200);
        this.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 72));
    }

    public void setState(int x) {
        if (x == 0) {
            this.setText("");
            this.state = 0;
        } else if (x == 1) {
            this.setText("X");
            this.state = 1;
            this.setDisable(true);
        } else if (x == -1) {
            this.setText("O");
            this.state = -1;
            this.setDisable(true);
        }
    }

    public int getState() {
        return state;
    }

    /*for(int x=0; x<200; x++) {
        int random = generator.nextInt(9);
        if(buttons[random].getState() == 0) {
            buttons[random].setState(-1);
            checkScore();
            break;
        }
    }*/
}
