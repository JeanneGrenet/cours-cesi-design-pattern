package Factory;

public abstract class Dialog {
    public abstract Button createButton();

    public void render(){
        Button button = createButton();
        button.render();
        System.out.println("Affichage d'une boite de dialogue");
    }

}
