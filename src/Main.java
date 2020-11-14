import javax.swing.*;
import java.awt.*;

class Main extends JFrame {
    
    private static final long serialVersionUID = 1L;

    class App extends JPanel {
        
        private static final long serialVersionUID = 1L;
        Grid grid;

        public App() {

            //changed dimension so the right side of the screen would fit the info text display 
            setPreferredSize(new Dimension(1150, 720));
            grid = new Grid();
        }

        @Override
        public void paint(Graphics g) {
            grid.paint(g, getMousePosition());
        }

    }

    public static void main(String[] args) throws Exception {
        Main window = new Main();
        window.run();
    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        App canvas = new App();
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
    }

    public void run() {
        while (true) {
            this.repaint();
        }
    }
}