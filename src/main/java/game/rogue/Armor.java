package game.rogue;

public class Armor extends Item{
    private final static int DEFAULT_WEIGHT = 200;
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
        this.armorType = ArmorType.valueOf(type.substring(0,1).toUpperCase() + type.substring(1).toLowerCase());
    }
    public Armor(String type, int customWeight){
        super(customWeight);
        this.armorType = ArmorType.valueOf(type.substring(0,1).toUpperCase() + type.substring(1).toLowerCase());
    }


    public int getDefense() {
        return armorType.getDefense();
    }

    public boolean isPlate(){
        if (armorType.name().equalsIgnoreCase("Plate")){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isCloth(){
        if(armorType.name().equalsIgnoreCase("Cloth")){
            return true;
        }
        else{
            return false;
        }
    }

}
