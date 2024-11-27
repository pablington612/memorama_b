package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickListener implements ActionListener {
    private Game game;
    private int index;

    public ButtonClickListener(Game game, int index) {
        this.game = game;
        this.index = index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.revealButton(index);
    }
}