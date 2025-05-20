package observer;

public class BookShelf {
    private int count = 0;

    public void addBook(){
        count++;
    }
    public int getCount(){
        return count;
    }
}
