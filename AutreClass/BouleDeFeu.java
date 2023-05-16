package AutreClass;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class BouleDeFeu {
    private float x, y, vx, vy; // position et vitesse
    private Image image;
    private boolean isAlive; // indique si la boule de feu est en vie
    private int dgt ;
    
    public BouleDeFeu(float x, float y, float speed, String image, Input input) throws SlickException {
        this.x = x;
        this.y = y;
        this.vx = speed;
        this.vy = 0;
        this.image = new Image("image/"+image+".png");;
        this.isAlive = true;
        this.dgt = 10;
        
        // Calcul de la direction de la boule de feu en fonction du pointeur de la souris
        float mouseX = input.getMouseX();
        float mouseY = input.getMouseY();
        float dx = mouseX - x;
        float dy = mouseY - y;
        float norm = (float) Math.sqrt(dx * dx + dy * dy);
        if (norm != 0) {	
            vx = speed * dx / norm;
            vy = speed * dy / norm;
        }
    }
    
    public void update(GameContainer gc, int delta) {
        if (isAlive) {
            x += vx * delta / 1000f;
            y += vy * delta / 1000f;
            if (x < -(int) (image.getWidth()* 0.05) || x > gc.getWidth() || y < -(int) (image.getHeight() * 0.05) || y > gc.getHeight()) {
                // si la boule de feu sort de l'écran, elle meurt
                isAlive = false;
            }
            
           
        }
    }
    
    public void render(Graphics g) {
        if (isAlive) {
        	image.draw(x, y, (int) (image.getWidth()* 0.05), (int) (image.getHeight() * 0.05));
        }

    }
    
    public void hit(Enemy enemy) {

    	    	
        if (isAlive && enemy.getBounds().intersect(this.getBounds()) ) {
            // si la boule de feu touche un ennemi, elle lui inflige des dégâts et meurt
        	enemy.setHP(enemy.getHP()-dgt);
            isAlive = false;
        }

    }
    
    public Zonne getBounds() {
        Zonne zonne = new Zonne(x, y, x + image.getWidth()*0.05f, y + image.getHeight()*0.05f);
        return zonne;
    }
    
    public boolean isAlive() {
        return isAlive;
    }
}
