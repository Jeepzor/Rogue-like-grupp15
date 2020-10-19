package game.rogue;

public abstract class Character {
    private Position position;
    
    public Character(Position position){
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void move(World world, int x, int y) {
        if ((position.getX() + x) < 0 || (position.getY() + y) < 0) {
            throw new IllegalArgumentException();
        }
        int currentX = position.getX();
        int currentY = position.getY();
        position.setX(currentX += x);
        position.setY(currentY += y);

    }
}
