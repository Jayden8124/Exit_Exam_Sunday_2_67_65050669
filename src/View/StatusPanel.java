package View;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class StatusPanel extends JPanel {
    private JLabel messageLabel;
    private JLabel repairCountLabel;
    private JButton repairButton;

    public StatusPanel() {
        setLayout(new BorderLayout());
        
        // Status
        messageLabel = new JLabel("สถานะการตรวจสอบ", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(messageLabel, BorderLayout.NORTH);
        
        // Repair Button
        JPanel repairPanel = new JPanel();
        repairButton = new JButton("ซ่อมแซมชุด");
        repairButton.setVisible(false);
        repairPanel.add(repairButton);
        add(repairPanel, BorderLayout.CENTER);
    
        // Statistic
        repairCountLabel = new JLabel("จำนวนชุดที่ซ่อมแซม: ", SwingConstants.CENTER);
        add(repairCountLabel, BorderLayout.SOUTH);
    }
    
    public void showMessage(String message) {
        messageLabel.setText(message);
    }
    
    public void showRepairOption(boolean show) {
        repairButton.setVisible(show);
    }
    
    public JButton getRepairButton() {
        return repairButton;
    }
    
    public void updateRepairCounts(Map<String, Integer> repairCounts) {
        StringBuilder counts = new StringBuilder("<html>");
        for (Map.Entry<String, Integer> entry : repairCounts.entrySet()) {
            counts.append(entry.getKey())
                  .append(": ")
                  .append(entry.getValue())
                  .append("<br/>");
        }
        counts.append("</html>");
        repairCountLabel.setText(counts.toString());
    }
}
