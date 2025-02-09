package View;

import Model.SuperheroSuit;
import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {
    private JLabel suitDetailsLabel;
    
    public DisplayPanel() {
        setLayout(new BorderLayout());
        suitDetailsLabel = new JLabel("รายละเอียดชุดจะปรากฎที่นี่", SwingConstants.CENTER);
        suitDetailsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(suitDetailsLabel, BorderLayout.CENTER);
    }
    
    public void displaySuitDetails(SuperheroSuit suit) {
        String details = "<html>รหัสชุด: " + suit.getCode() + "<br/>" +
                         "ประเภท: " + suit.getType() + "<br/>" +
                         "ความทนทาน: " + suit.getDurability() + "</html>";
        suitDetailsLabel.setText(details);
    }
}
