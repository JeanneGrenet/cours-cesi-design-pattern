package observer;

public abstract class AuthorListener {
    abstract void onBookAdded(int added);
    Authors favoriteAuthor;
    abstract Authors getFavoriteAuthor();
}
