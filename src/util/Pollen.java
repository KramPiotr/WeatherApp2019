package util;

public enum Pollen {
    NONE ("None"),
    LOW ("Low"),
    MEDIUM ("Medium"),
    HIGH ("High");


    private final String name;
    Pollen(String s){
        name = s;
    }

    public static Pollen getRandomPollen(){
        double rand = Math.random()*4.0;
        if(rand<1) return NONE;
        if(rand<2) return LOW;
        if(rand<3) return MEDIUM;
        return HIGH;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
