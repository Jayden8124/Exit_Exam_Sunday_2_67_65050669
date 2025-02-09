package View;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private InputPanel inputPanel;
    private DisplayPanel displayPanel;
    private StatusPanel statusPanel;
    
    public MainView() {
        setTitle("Superhero Suit Checker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        initComponents();
    }
    
    private void initComponents() {
        inputPanel = new InputPanel();
        displayPanel = new DisplayPanel();
        statusPanel = new StatusPanel();
        
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
    }
    
    // Getter for Controller
    public InputPanel getInputPanel() {
        return inputPanel;
    }
    
    public DisplayPanel getDisplayPanel() {
        return displayPanel;
    }
    
    public StatusPanel getStatusPanel() {
        return statusPanel;
    }
}
