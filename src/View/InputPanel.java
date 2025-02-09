package View;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    private JTextField suitCodeInput;
    private JButton checkButton;

    public InputPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(new JLabel("กรอกรหัสชุด:"));
        suitCodeInput = new JTextField(10);
        add(suitCodeInput);
        checkButton = new JButton("ตรวจสอบ");
        add(checkButton);
    }
    
    public JTextField getSuitCodeInput() {
        return suitCodeInput;
    }
    
    public JButton getCheckButton() {
        return checkButton;
    }
}
