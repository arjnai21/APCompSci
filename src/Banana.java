public class Banana extends Fruit{
    private boolean peeled;

    public Banana(){
        // calorioes...?
        super.
        peeled = false;

    }

    public void peel(){
        peeled = true;
    }
    @Override
    public void eat(){
        if(peeled){
            super.eat();
        }
    }
}
