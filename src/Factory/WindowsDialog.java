package Factory;

public class WindowsDialog extends Dialog {

    public Button createButton(){
        return new WindowsButton();
    }
}
