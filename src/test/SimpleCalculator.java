package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {

    private JTextField num1Field;
    private JTextField num2Field;
    // Removed resultField as result will be in a dialog
    private JButton addBtn;
    private JButton subBtn;
    private JButton mulBtn;
    private JButton divBtn;

    public SimpleCalculator() {
        // Frame setup
        setTitle("Enhanced Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        getContentPane().setBackground(new Color(230, 230, 250)); // Lavender background for frame

        // Panel for layout and coloring
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10)); // Use BorderLayout for the main panel
        panel.setBackground(new Color(240, 250, 240)); // Light green background for panel
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Calculator Operations", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, null, Color.DARK_GRAY));
        
        // Input fields and labels panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBackground(panel.getBackground()); // Match panel background

        JLabel num1Label = new JLabel("Number 1:");
        num1Label.setHorizontalAlignment(SwingConstants.RIGHT);
        num1Label.setForeground(new Color(70, 130, 180)); // Steel Blue
        num1Field = new JTextField();
        num1Field.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237))); // Cornflower Blue border

        JLabel num2Label = new JLabel("Number 2:");
        num2Label.setHorizontalAlignment(SwingConstants.RIGHT);
        num2Label.setForeground(new Color(70, 130, 180)); // Steel Blue
        num2Field = new JTextField();
        num2Field.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237))); // Cornflower Blue border

        inputPanel.add(num1Label);
        inputPanel.add(num1Field);
        inputPanel.add(num2Label);
        inputPanel.add(num2Field);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 5, 5)); // 2x2 grid for buttons
        buttonPanel.setBackground(panel.getBackground()); // Match panel background

        addBtn = new JButton("+");
        subBtn = new JButton("-");
        mulBtn = new JButton("*");
        divBtn = new JButton("/");

        // Styling buttons with valid Color definitions
        customizeButton(addBtn, new Color(144, 238, 144), new Color(0, 100, 0)); // Light green, Dark Green text
        customizeButton(subBtn, new Color(255, 182, 193), new Color(139, 0, 0));   // Light pink, Dark Red text
        customizeButton(mulBtn, new Color(173, 216, 230), new Color(0, 0, 139));  // Light blue, Dark Blue text
        customizeButton(divBtn, new Color(255, 255, 153), Color.ORANGE);   // Light yellow, Orange text (Orange is a valid constant)

        buttonPanel.add(addBtn);
        buttonPanel.add(subBtn);
        buttonPanel.add(mulBtn);
        buttonPanel.add(divBtn);

        // Add input and button panels to the main panel
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Add main panel to the frame
        add(panel, BorderLayout.CENTER);
    }

    // Helper method to customize buttons
    private void customizeButton(JButton button, Color bgColor, Color fgColor) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBorder(BorderFactory.createLineBorder(fgColor.darker(), 1));
    }

    // Helper method to format result
    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%.2f", result);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            double result = 0;
            String message = "";

            if (e.getSource() == addBtn) {
                result = num1 + num2;
                message = String.format("Result: %.2f + %.2f = %s", num1, num2, formatResult(result));
            } else if (e.getSource() == subBtn) {
                result = num1 - num2;
                message = String.format("Result: %.2f - %.2f = %s", num1, num2, formatResult(result));
            } else if (e.getSource() == mulBtn) {
                result = num1 * num2;
                message = String.format("Result: %.2f * %.2f = %s", num1, num2, formatResult(result));
            } else if (e.getSource() == divBtn) {
                if (num2 == 0) {
                    message = "Error: Division by zero";
                    JOptionPane.showMessageDialog(this, message, "Calculation Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit early for division by zero error
                } else {
                    result = num1 / num2;
                    message = String.format("Result: %.2f / %.2f = %s", num1, num2, formatResult(result));
                }
            }
            
            // Show result in a dialog if no division by zero error
            if (!message.startsWith("Error:")) {
                JOptionPane.showMessageDialog(this, message, "Calculation Result", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Invalid input. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Run the GUI creation on the event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleCalculator().setVisible(true);
            }
        });
    }
}
