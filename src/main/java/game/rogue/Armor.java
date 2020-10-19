package game.rogue;

public class Armor extends Item{
    private final static int DEFAULT_WEIGHT = 200;
    private int defense;
    private ArmorType armorType;

    public enum ArmorType{
        Cloth(3),
        Plate(10);
        private int defense;
        ArmorType(int defense){
            this.defense = defense;
        }
        public int getDefense(){
            return this.defense;
        }
    }

    public Armor(String type){
        super(DEFAULT_WEIGHT);
        if(type.equalsIgnoreCase("plate")){
            this.armorType = ArmorType.valueOf("Plate");
            this.defense = armorType.getDefense();
        }
        else if(type.equalsIgnoreCase("cloth")){
            this.armorType = ArmorType.valueOf("Cloth");
            this.defense = armorType.getDefense();
        }
        else{
            throw new IllegalArgumentException("invalid armor type");
        }
    }
    public Armor(String type, int customWeight){
        super(customWeight);
        if(type.equalsIgnoreCase("plate")){
            this.armorType = ArmorType.valueOf("Plate");
            this.defense = armorType.getDefense();
        }
        else if(type.equalsIgnoreCase("cloth")){
            this.armorType = ArmorType.valueOf("Cloth");
            this.defense = armorType.getDefense();
        }
        else{
            throw new IllegalArgumentException("invalid armor type");
        }
    }


    public int getDefense() {
        return defense;
    }

}
