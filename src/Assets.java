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

    public static void init() {
        try {
            stockIconSheet = ImageIO.read(Assets.class.getResourceAsStream("icons.png"));
            charSheet = ImageIO.read(Assets.class.getResourceAsStream("charsheet.png"));
            deadCharSheet = ImageIO.read(Assets.class.getResourceAsStream("charsheet-dead.png"));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            ConfigWindow.printError("Couldn't find one or more image assets", new Font("Verdana", Font.PLAIN, 12));
        }

        for (int i = 0, index2 = 0; i < 12; i++) {
            stockIcons.add(stockIconSheet.getSubimage(i * 8, 0, 8, 10));
            totalStocks.add(new ArrayList<>());
            if (i < 6) {
                charIcons.add(new ImageIcon(Assets.charSheet.getSubimage(i * 125, 0, 125, 125)));
                deadIcons.add(new ImageIcon(Assets.deadCharSheet.getSubimage(i * 125, 0, 125, 125)));
            } else {
                charIcons.add(new ImageIcon(Assets.charSheet.getSubimage(index2 * 125, 125 , 125, 125)));
                deadIcons.add(new ImageIcon(Assets.deadCharSheet.getSubimage(index2 * 125, 125, 125, 125)));
                index2++;
            }
        }
    }

    public static void addStockIcon(int index) {
        totalStocks.get(index).add(stockIcons.get(index));
    }
}
