import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import AutreClass.Bordure;
import AutreClass.Personnage;
import AutreClass.Zonne;

public class Mort extends BasicGameState {
    private Personnage J1;
    private Zonne Replay;
    public Mort() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
        Replay=new Zonne(450,420,570,440);
        Personnage J1 = Niveau0.getJ1();
        
    }

    @Override
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
        g.setColor(Color.black); // Définit la couleur de fond de l'écran à noir
        g.fillRect(0, 0, 1024, 720); // Dessine un rectangle noir pour remplir l'écran
        g.setColor(Color.red); // Définit la couleur du texte à blanc
        g.drawString("Vous êtes mort !", 1024 / 2 - g.getFont().getWidth("Vous êtes mort !") / 2, 720 / 2 - g.getFont().getHeight("Vous êtes mort !") / 2); // Dessine le texte au milieu de l'écran
// Replay button 
        g.setColor(Color.white);
        g.fillRect(450, 420, 570-450, 440-420);
        g.setColor(Color.red);
        g.drawString("Rejouer", 475, 420);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Bordure bordure = new Bordure(300, 300, 1080-(int)(Niveau0.getJ1().getImage().getWidth()*1.5), 720);
        org.newdawn.slick.Input input = gc.getInput();
        
        Zonne Zsouris = new Zonne(input.getMouseX(), input.getMouseY(), input.getMouseX()+1, input.getMouseY()+1);

        
        if(Zsouris.intersect(Replay)&& input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            sbg.enterState(0);
            Reanimation();
            Niveau0.getJ1().ajouterBordure(bordure);
        }
        //System.out.println(input.getMouseX() + " "+ input.getMouseY());

    }
    public void Reanimation() {
        Niveau0.getJ1().setHP(Niveau0.getJ1().getHPmax());
        Niveau0.getJ1().setX(900);
        Niveau0.getJ1().setY(550);
    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return (-1);
    }

}