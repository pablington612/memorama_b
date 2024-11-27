package main;
import javax.swing.*;
import java.awt.*;

public class GameUI extends JFrame {
    private JButton[] botones;
    private Game game;

    public GameUI() {
        setTitle("Memorama");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 4));

        botones = new JButton[16];

        // Create buttons and add them to the frame
        for (int i = 0; i < 16; i++) {
            botones[i] = new JButton();
            botones[i].setFont(new Font("Arial", Font.PLAIN, 24));
            add(botones[i]);
        }
    }

    public void setGame(Game game) {
        this.game = game;
        for (int i = 0; i < 16; i++) {
            botones[i].addActionListener(game.createButtonClickListener(i));
        }
    }

    public void revealButton(int index, int value) {
        botones[index].setText(String.valueOf(value));
    }

    public void hideButton(int index) {
        botones[index].setText("");
    }

    public void disableButton(int index) {
        botones[index].setEnabled(false);
    }
}