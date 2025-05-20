package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookShop {
    public final Map<BookShelf, Authors> inventory;

    public List<AuthorListener> subscribers = new ArrayList<>();

    public void addListener(AuthorListener authorListener) {
        subscribers.add(authorListener);
    }
    public void removeListener(AuthorListener authorListener) {
        subscribers.remove(authorListener);
    }

    public BookShop(Map<BookShelf, Authors> inventory) {
        this.inventory = inventory;
    }
    private BookShelf findBookShelfByAuthor(Authors author) {
        return inventory.entrySet().stream()
                .filter(entry -> entry.getValue().equals(author))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public BookShelf getBookShelfByAuthor(Authors author) {
        BookShelf bookShelf = findBookShelfByAuthor(author);
        if (bookShelf == null) {
            bookShelf = new BookShelf();
            inventory.put(bookShelf, author);
        }
        return bookShelf;
    }

    public void supply(Authors author, int quantity) {
        BookShelf bookShelf = getBookShelfByAuthor(author);
        for (int i = 0; i < quantity; i++) {
            bookShelf.addBook();
        }

        for (AuthorListener subscriber : subscribers) {
            if (subscriber.getFavoriteAuthor().equals(author)) {
                subscriber.onBookAdded(quantity);
            }

        }
    }

    public int getCount(Authors author) {
        BookShelf bookShelf = findBookShelfByAuthor(author);
       return bookShelf.getCount();
    }
}
