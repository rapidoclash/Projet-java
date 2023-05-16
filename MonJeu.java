import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MonJeu extends StateBasedGame {

	public MonJeu(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		addState(new Niveau0());
		addState(new Niveau1());
		addState(new Niveau2());
		addState(new Mort());
		enterState(0);
	}

}
