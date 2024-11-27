package main;
public class Main {
    public static void main(String[] args) {
        GameUI gameUI = new GameUI();
        Game game = new Game(gameUI);
        gameUI.setGame(game);
        gameUI.setVisible(true);
    }
}