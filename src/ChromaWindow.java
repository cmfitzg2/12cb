import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ChromaWindow {
    private JFrame chromaWindow;
    private JLabel picLabel = new JLabel(Assets.charIcons.get(0));
    private JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel charPanel = new JPanel();

    public ChromaWindow(int windowNum) {
        charPanel.add(picLabel);
        charPanel.setBackground(new Color(0, 191, 255));
        mainPanel.setBackground(new Color(0, 191, 255));
        chromaWindow = new JFrame("Player " + windowNum);
        //chromaWindow.setUndecorated(true);
        //chromaWindow.setBackground(new Color(0, 0, 0, 0));
        updateStocks();
        chromaWindow.add(BorderLayout.WEST, charPanel);
        chromaWindow.add(BorderLayout.CENTER, mainPanel);
        chromaWindow.setSize(328, 194);
        chromaWindow.setLocationRelativeTo(null);
        if (windowNum == 1) {
            chromaWindow.setLocation(chromaWindow.getX() + 20, 20);
        } else {
            chromaWindow.setLocation(chromaWindow.getX() + 20, 220);
        }
        chromaWindow.setResizable(true);
        chromaWindow.setVisible(true);
    }

    public void updateActiveCharacter(Icon icon) {
        picLabel.setIcon(icon);
    }

    public void updateStocks() {
        //big O was never really my forte
        //gotta keep the icons in order
        for (Component component : mainPanel.getComponents()) {
            mainPanel.remove(component);
        }
        for (List<BufferedImage> charStocksList : Assets.totalStocks) {
            for (BufferedImage stockIcon : charStocksList) {
                JLabel label = new JLabel();
                label.setSize(new Dimension(16, 20));
                label.setIcon(new ImageIcon(stockIcon.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH)));
                mainPanel.add(label);
            }
        }
        chromaWindow.revalidate();
        chromaWindow.repaint();
    }
}
