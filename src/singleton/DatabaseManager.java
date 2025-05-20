package singleton;

public class DatabaseManager {

    private static DatabaseManager cachedManager;

    private DatabaseManager() throws InterruptedException {
        System.out.println("Démarrage du gestionnaire de BDD");
        Thread.sleep(2000);
        System.out.println("Gestionnaire démarré");
    }
    public static DatabaseManager getInstance() throws InterruptedException {
        if (cachedManager == null) {
            cachedManager = new DatabaseManager();
        }
        return cachedManager;
    }

    public void save(){
        System.out.println("Enregristrement effectué");
    }
}
