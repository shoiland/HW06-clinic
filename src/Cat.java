class Cat extends Pet {
    private int miceCaught;

    public Cat(String name, double health, int painLevel, int miceCaught) {
        super(name, health, painLevel);
        if (miceCaught < 0){
            this.miceCaught = 0;
        } else {
            this.miceCaught = miceCaught;
        }
    }
    public Cat(String name, double health, int painLevel){
        this(name, health, painLevel, 0);
    }

    //Getters
    public int getMiceCaught(){
        return this.miceCaught;
    }

    public int treat(){
        double minutes;
        if (this.miceCaught < 4){
            minutes = this.getPainLevel()*2 / this.getHealth();
        } else if (this.miceCaught <= 7){
            minutes = this.getPainLevel() / this.getHealth();
        } else {
            minutes = this.getPainLevel() / (this.getHealth() * 2);
        }
        minutes = Math.ceil(minutes);
        this.heal();
        return (int) minutes;
    }
    public void speak(){
        super.speak();
        String meow = "";
        boolean painBad = this.getPainLevel() > 5;
        for (int i = 0; i < this.miceCaught; i++){
            meow += "meow ";
        }
        meow = meow.strip();
        meow = painBad ? meow.toUpperCase() : meow;
        meow += "\n";
        System.out.println(meow);
    }
    //Todo: Uses the equals() method in Pet as part of the decision-making with the additional condition of miceCaught being the same
    public boolean equals (Object o){
        return this.equals(o);
    }
}
