public abstract class Pet {
    private String name;
    private double health;
    private int painLevel;

    public Pet(String name, double health, int painLevel){
        this.name = name;
        if (health > 1.0){
            this.health = 1.0;
        } else if (health < 0.0) {
            this.health = 0.0;
        } else {
            this.health = health;
        }
        if (painLevel > 10){
            this.painLevel = 10;
        } else if (painLevel < 1){
            this.painLevel = 1;
        } else {
            this.painLevel = painLevel;
        }
    }

    //Getter Methods
    public String getName() {
        return this.name;
    }
    public double getHealth(){
        return this.health;
    }
    public int getPainLevel(){
        return this.painLevel;
    }

    //Other Methods
    public abstract int treat();

    public void speak(){
        String speakString = "Hello! My name is " + this.name;
        speakString = painLevel > 5 ? speakString.toUpperCase() : speakString;
        System.out.println(speakString);
    }
    public boolean equals(Object o){
        //Todo: Need to address this equals.  Do it after running through vorsum
        return false;
    }
    protected void heal(){
        this.health = 1.0;
        this.painLevel = 1;
    }


}
