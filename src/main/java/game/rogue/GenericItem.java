package game.rogue;

public class GenericItem extends Item {
    private static final int DEFAULT_WEIGHT = 200;

    public GenericItem(){
        super(DEFAULT_WEIGHT);
    }
    public GenericItem(int nonDefaultWeight){
        super(nonDefaultWeight);
    }

    @Override
    public String toString(){
        return "Rock";
    }
}
