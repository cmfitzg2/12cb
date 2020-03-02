import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class CharactersWindow implements ActionListener {
    private int stockLimit;
    private int scale = 18;
    private int preferredX = 125, preferredY = 32;
    private CharacterButton currentlyActiveButton;
    private JFrame charactersWindow;
    private JPanel characterPanelSecondary = new JPanel();
    private JPanel characterPanelMain = new JPanel();
    private ChromaWindow chromaWindow;

    public CharactersWindow(int windowNum, int stockLimit, String imageStyle) {
        Assets.init(imageStyle);

        this.stockLimit = stockLimit;
        charactersWindow = new JFrame("P" + windowNum +" Config");
        if (windowNum == 1) {
            charactersWindow.setLocation(20, 20);
        } else {
            charactersWindow.setLocation(250, 20);
        }
        charactersWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        characterPanelMain.setLayout(new BoxLayout(characterPanelMain, BoxLayout.Y_AXIS));
        characterPanelSecondary.setLayout(new GridLayout(6, 2));

        for (int i = 0; i < 12; i++) {
            CharacterButton character = new CharacterButton(Assets.charIcons.get(i), i);
            character.setPreferredSize(new Dimension(preferredX, preferredY));
            character.setBackground(new Color(0, 191, 255));
            character.addActionListener(this);
            character.setStocks(stockLimit);
            character.setStockIcon(Assets.stockIcons.get(i));
            if (windowNum == 1) {
                for (int j = 0; j < stockLimit; j++) {
                    Assets.addStockIcon(i);
                }
            }
            characterPanelSecondary.add(character);
        }

        characterPanelMain.add(characterPanelSecondary);
        charactersWindow.add(characterPanelMain);
        charactersWindow.setSize(234, 735);
        //characterWindow.setResizable(false);
        charactersWindow.setVisible(true);
        chromaWindow = new ChromaWindow(windowNum);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        CharacterButton source = (CharacterButton) ae.getSource();
        if (!source.isActive()) {
            source.setActive(true);
            if (chromaWindow != null) {
                chromaWindow.updateActiveCharacter(source.getIcon());
            }
        } else {
            source.setStocks(source.stocks - 1);
            try {
                Assets.totalStocks.get(source.index).remove(0);
            } catch (Exception e) {
                for (int i = 0; i < stockLimit; i++) {
                    Assets.totalStocks.get(source.index).add(Assets.stockIcons.get(source.index));
                }
            } finally {
                chromaWindow.updateStocks();
            }
        }
    }

    public class CharacterButton extends JButton {
        private int index;
        private int stocks;
        private boolean active;
        private ButtonModel buttonModel = getModel();
        private BufferedImage stockIcon;
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int xDrawFrom = 0;
            int yDrawFrom = getHeight() / 125;
            for (int i = 0; i < stocks; i++) {
                //TODO: update this to work with the button height for dynamic wrapping
                if (i != 0 && i % 4 == 0) {
                    xDrawFrom += 16;
                    yDrawFrom = getHeight() / 125;
                }
                g.drawImage(stockIcon, 3 + xDrawFrom, 3 + yDrawFrom, 16, 20, null);
                yDrawFrom += scale;
            }
            if (active) {
                setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0, 200), 3));
            } else if (buttonModel.isRollover()) {
                setBorder(BorderFactory.createLineBorder(new Color(150, 200, 255)));
            } else {
                setBorder(BorderFactory.createEmptyBorder());
            }
        }

        public CharacterButton(ImageIcon icon, int index) {
            setIcon(icon);
            this.index = index;
        }

        public void setStocks(int stocks) {
            if (stocks < 0) {
                this.stocks = stockLimit;
                setIcon(Assets.charIcons.get(index));
                chromaWindow.updateActiveCharacter(getIcon());
            } else {
                if (stocks == 0) {
                    setIcon(Assets.deadIcons.get(index));
                    chromaWindow.updateActiveCharacter(getIcon());
                }
                this.stocks = stocks;
            }
        }

        public void setStockIcon(BufferedImage stockIcon) {
            this.stockIcon = stockIcon;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            if (active) {
                if (currentlyActiveButton != null) {
                    currentlyActiveButton.setActive(false);
                }
                currentlyActiveButton = this;
            }
            this.active = active;
        }
    }
}
