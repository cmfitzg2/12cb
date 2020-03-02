import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Assets {

    public static BufferedImage stockIconSheet, charSheet, deadCharSheet;
    public static List<ImageIcon> charIcons = new ArrayList<>();
    public static List<ImageIcon> deadIcons = new ArrayList<>();
    public static List<BufferedImage> stockIcons = new ArrayList<>();
    public static List<List<BufferedImage>> totalStocks = new ArrayList<>();

    private static Integer stockIconSize = 8;
    private static String stockIconFile = "icons.png";

    private static Integer charIconWidth = 125;
    private static Integer charIconHeight = 125;
    private static String charFile = "charsheet.png";
    private static String deadCharFile = "charsheet-dead.png";

    public static void init(String style) {
        try {
            switch(style) {
                case "Sekerei":
                    stockIconSize = 8;
                    stockIconFile = "icons.png";

                    charIconWidth = 125;
                    charIconHeight = 125;
                    charFile = "charsheet-sekerei.png";
                    deadCharFile = "charsheet-sekerei-dead.png";
                    break;
                case "Original":
                    stockIconSize = 8;
                    stockIconFile = "icons.png";

                    charIconWidth = 89;
                    charIconHeight = 86;
                    charFile = "charsheet-original.png";
                    deadCharFile = "charsheet-original-dead.png";
                    break;
                default:
                    // already defined on init
                    break;
            }

            stockIconSheet = ImageIO.read(Assets.class.getResourceAsStream(stockIconFile));
            charSheet = ImageIO.read(Assets.class.getResourceAsStream(charFile));
            deadCharSheet = ImageIO.read(Assets.class.getResourceAsStream(deadCharFile));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            ConfigWindow.printError("Couldn't find one or more image assets", new Font("Verdana", Font.PLAIN, 12));
        }

        for (int i = 0, index2 = 0; i < 12; i++) {
            stockIcons.add(stockIconSheet.getSubimage(i * stockIconSize, 0, stockIconSize, 10));
            totalStocks.add(new ArrayList<>());
            if (i < 6) {
                charIcons.add(new ImageIcon(Assets.charSheet.getSubimage(i * charIconWidth, 0, charIconWidth, charIconHeight)));
                deadIcons.add(new ImageIcon(Assets.deadCharSheet.getSubimage(i * charIconWidth, 0, charIconWidth, charIconHeight)));
            } else {
                charIcons.add(new ImageIcon(Assets.charSheet.getSubimage(index2 * charIconWidth, charIconHeight , charIconWidth, charIconHeight)));
                deadIcons.add(new ImageIcon(Assets.deadCharSheet.getSubimage(index2 * charIconWidth, charIconHeight, charIconWidth, charIconHeight)));
                index2++;
            }
        }
    }

    public static void addStockIcon(int index) {
        totalStocks.get(index).add(stockIcons.get(index));
    }
}
