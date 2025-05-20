package Factory;

public class WebDialog extends Dialog {

    public Button createButton(){
      return new HTMLButton();
    }
}
