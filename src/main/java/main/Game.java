package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Game {
    private int[] valores;
    private boolean[] isFlipped;
    private int primerSeleccion = -1;
    private int segundaSeleccion = -1;
    private boolean esperarCombinacion = false;
    private GameUI gameUI;

    public Game(GameUI gameUI) {
        this.gameUI = gameUI;
        valores = new int[16];
        isFlipped = new boolean[16];

        // Initialize values and shuffle them
        for (int i = 0; i < 8; i++) {
            valores[i] = i;
            valores[i + 8] = i;
        }
        shuffleArray(valores);
    }

    public void setGameUI(GameUI gameUI) {
        this.gameUI = gameUI;
    }

    public ActionListener createButtonClickListener(int index) {
        return new ButtonClickListener(this, index);
    }

    public void revealButton(int index) {
        if (isFlipped[index] || esperarCombinacion) return;

        gameUI.revealButton(index, valores[index]);
        isFlipped[index] = true;

        if (primerSeleccion == -1) {
            primerSeleccion = index;
        } else if (segundaSeleccion == -1) {
            segundaSeleccion = index;
            esperarCombinacion = true;
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkMatch();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void checkMatch() {
        if (valores[primerSeleccion] == valores[segundaSeleccion]) {
            gameUI.disableButton(primerSeleccion);
            gameUI.disableButton(segundaSeleccion);
        } else {
            gameUI.hideButton(primerSeleccion);
            gameUI.hideButton(segundaSeleccion);
            isFlipped[primerSeleccion] = false;
            isFlipped[segundaSeleccion] = false;
        }
        primerSeleccion = -1;
        segundaSeleccion = -1;
        esperarCombinacion = false;
    }

    private void shuffleArray(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = (int) (Math.random() * (i + 1));
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}