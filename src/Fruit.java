public class Fruit{

    private double calories;
    private boolean eaten;

    public Fruit(){
        calories=50;
        eaten=false;
    }

    public Fruit(int numCalories){
        calories=numCalories;

    }

    public void eat(){


        eaten=true;
    }

    public boolean isEaten(){
        return eaten;
    }
}
