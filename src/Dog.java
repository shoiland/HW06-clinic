public class Dog extends Pet {
    double droolRate;

    //Constructors
    public Dog(String name, double health, int painLevel, double droolRate){
        super(name, health, painLevel);
        this.droolRate = droolRate;

    }
    public Dog(String name, double health, int painLevel){
        this(name, health, painLevel, 5.0);
    }

    //Getter Methods
    public double getDroolRate(){
        return this.droolRate;
    }

    //Other methods
    public int treat(){
        double minutes;
        if (this.droolRate < 3.5){
            minutes = this.getPainLevel()*2 / this.getHealth();
        } else if (this.droolRate >= 3.5 && this.droolRate <= 7.5){
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
        String barkTimes = "";
        boolean painBad = this.getPainLevel() > 5;
        for (int i = 0; i < this.getPainLevel(); i++){
            barkTimes += "bark ";
        }
        barkTimes = barkTimes.strip();
        barkTimes = painBad ? barkTimes.toUpperCase() : barkTimes;
        barkTimes += "\n";
        System.out.println(barkTimes);
    }
    //Todo: Uses the equals() method in Pet as part of the decision-making with the additional condition of droolRate being the same
    public boolean equals (Object o){
        return this.equals(o);
    }
}
