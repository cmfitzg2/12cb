import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ConfigWindow implements ActionListener {
    private int stockLimit;
    private String imageStyle;
    private JFrame configWindow;
    private JButton startButton = new JButton("Start");
    private JPanel configPanelMain = new JPanel();
    private JPanel errorPanel = new JPanel();
    private JLabel stocksLabel = new JLabel("Stock total: ");
    public static JLabel errorLabel = new JLabel();
    private JTextField stocksTextField = new JTextField(2);
    private JLabel styleLabel = new JLabel("Image Style: ");
    private String[] styleNames = { "Lord Narwhal", "Sekerei", "Original" };
    private JComboBox styleList = new JComboBox(styleNames);

    public static void main(String[] args) {
        new ConfigWindow();
    }


    public ConfigWindow() {
        configWindow = new JFrame("12 Character Battle Tool");
        configWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        errorLabel.setForeground(Color.RED);
        startButton.addActionListener(this);
        startButton.setMnemonic(KeyEvent.VK_ENTER);
        configPanelMain.add(stocksLabel);
        configPanelMain.add(stocksTextField);
        configPanelMain.add(styleLabel);
        configPanelMain.add(styleList);
        configPanelMain.add(startButton);
        errorPanel.add(errorLabel);
        configWindow.add(configPanelMain);
        configWindow.add(errorPanel, BorderLayout.SOUTH);
        configWindow.setSize(350, 195);
        configWindow.setLocationRelativeTo(null);
        configWindow.setResizable(false);
        configWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(startButton)) {
            try {
                stockLimit = Integer.parseInt(stocksTextField.getText());
                if (null != styleList.getSelectedItem()) {
                    imageStyle = styleList.getSelectedItem().toString();
                }
            } catch (NumberFormatException e) {
                //if anyone not in smash 64's community reads this please know it's an inside joke
                //especially like if i'm applying for a job and for some reason submit this as a demo project
                //i promise i'm generally okay to work with
                printError("idiot", new Font("Comic Sans MS", Font.PLAIN, 64));
                return;
            }
            new CharactersWindow(1, stockLimit, imageStyle);
            new CharactersWindow(2, stockLimit, imageStyle);
            configWindow.dispose();
        }
    }

    public static void printError(String message, Font font) {
        errorLabel.setFont(font);
        errorLabel.setText(message);
    }

}
