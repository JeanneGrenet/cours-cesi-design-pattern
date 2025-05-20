package observer;

public class RSSFeed extends AuthorListener{

    public RSSFeed(Authors followedAuthor) {
        this.favoriteAuthor = followedAuthor;

    }

    Authors getFavoriteAuthor() {
        return favoriteAuthor;
    }

    void onBookAdded(int added) {
        System.out.println("Je suis le feed RSS et je vois " + added + " nouveux livres de " + favoriteAuthor);
    }

}
