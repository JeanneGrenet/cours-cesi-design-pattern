package Factory;

public class MobileDialog extends Dialog {
    public Button createButton(){
        return new FingerButton();
    }
}
