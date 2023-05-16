package AutreClass;

public class Camera {

    private float x, y;
    private int width, height;

    public Camera(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void centerOn(float x, float y) {
        this.x = x - width / 2;
        this.y = y - height / 2;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}

