package app;

import Factory.Dialog;
import Factory.MobileDialog;
import Factory.WebDialog;
import Factory.WindowsDialog;
import observer.*;
import proxy.IYoutubeLib;
import proxy.ProxyYoutubeLib;
import proxy.YoutubeLib;
import singleton.Entity;
import strategy.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) throws InterruptedException {
        // Singleton Design Pattern
        // testSingleton();

        // Factory Design Pattern
        // testFactory();

        // Observer Design Pattern
        // testObserver();

        // Proxy
        // testProxy();

        Game game = new Game();
    }

    private static ArrayList<Entity> testSingleton() throws InterruptedException {
        ArrayList<Entity> entities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Entity entity = new Entity();
            entities.add(entity);
        }
        return entities;
    }

    private static void testFactory(){
//      Dialog dialog = new WindowsDialog();
//      Dialog dialog = new WebDialog();
        Dialog dialog = new MobileDialog();

        dialog.render();
    }

    private static void testObserver(){
        Map<BookShelf, Authors> initialInventory = new HashMap<>();
        BookShop bookShop = new BookShop(initialInventory);

        for (int i = 1; i <= 10; i++) {
            bookShop.addListener(new Client("FanMarc_" + i, Authors.LEVY));
        }

        for (int i = 1; i <= 10; i++) {
            bookShop.addListener(new Client("FanGroucho_" + i, Authors.MARX));
        }
        RSSFeed feed = new RSSFeed(Authors.LEVY);
        bookShop.addListener(feed);
        bookShop.supply(Authors.LEVY, 6);
    }

    public static void testProxy() throws InterruptedException {
        IYoutubeLib parser = new ProxyYoutubeLib();
        for (int i = 0; i < 3; i++) {
            System.out.println(parser.getMP4FromUrl("https://t.ly/hrooY"));
        }
        for (int i = 0; i < 2; i++) {
            System.out.println(parser.getMP4FromUrl("https://t.ly/4nwE8"));
        }
        for (int i = 0; i < 2; i++) {
            System.out.println(parser.getMP4FromUrl("https://t.ly/hrooY"));
        }
    }
}