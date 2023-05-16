package AutreClass;


public class Bordure {

    private int xMin, yMin, xMax, yMax;
    
    public Bordure(int xMin, int yMin, int xMax, int yMax) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
    }

    public int getXMin() {
        return xMin;
    }

    public int getYMin() {
        return yMin;
    }

    public int getXMax() {
        return xMax;
    }

    public int getYMax() {
        return yMax;
    }

    public void restreindre(Personnage personnage) {
        // Restreint la position du personnage aux limites de la bordure
        personnage.setX(Math.max(xMin, Math.min(personnage.getX(), xMax)));
        personnage.setY(Math.max(yMin, Math.min(personnage.getY(), yMax)));
    }

	public void restreindre(Enemy enemy) {
		// TODO Auto-generated method stub
		enemy.setX(Math.max(xMin, Math.min(enemy.getX(), xMax)));
		enemy.setY(Math.max(yMin, Math.min(enemy.getY(), yMax)));
	}

}
