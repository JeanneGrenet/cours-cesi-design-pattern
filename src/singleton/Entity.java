package singleton;

public class Entity {
    public Entity() throws InterruptedException {
        System.out.println("Création de l'entité...");
        System.out.println("Sauvegarde de l'entité...");
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.save();
        System.out.println("Entité créée et sauvegardée");
    }
}
