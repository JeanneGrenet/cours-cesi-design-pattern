package observer;

public class Client extends AuthorListener {
    private final String name;

    public Client(String name, Authors favoriteAuthor) {
        this.name = name;
        this.favoriteAuthor = favoriteAuthor;
    }

    Authors getFavoriteAuthor() {
        return favoriteAuthor;
    }

    public String getName() {
        return name;
    }

    public void onBookAdded(int added) {
        System.out.println("Mon nom est " + name + ", j’aime " + favoriteAuthor +
                " et je suis content d’apprendre que j’ai " + added + " nouveaux livres à lire !");
    }



}
