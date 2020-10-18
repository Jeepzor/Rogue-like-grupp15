package game.rogue;

public abstract class Character {
    private int xPos;
    private int yPos;
    public Character(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void move(int x, int y) {
        if ((xPos + x) < 0 || (yPos + y) < 0) {
            throw new IllegalArgumentException();
        }
        xPos += x;
        yPos += y;

    }

}
