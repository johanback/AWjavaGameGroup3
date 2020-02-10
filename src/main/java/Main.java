public class Main {
    static Game game = new Game();
    static Menu menu;
    static {
        try { menu = game.createMenu(); }
        catch (Exception e) { e.printStackTrace(); }
    }
    public static boolean programRunning = true;

    public static void main(String[] args) throws Exception {
        while (programRunning){
            menu.initializeMenu();
            menu.menuLoop();
        }
    }
}
