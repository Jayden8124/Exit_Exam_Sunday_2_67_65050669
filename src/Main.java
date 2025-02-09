import javax.swing.SwingUtilities;
import View.MainView;
import Model.SuitRepository;
import Controller.SuitController;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainView view = new MainView();
                SuitRepository repository = new SuitRepository();
                new SuitController(repository, view);
                view.setVisible(true);
            }
        });
    }
}