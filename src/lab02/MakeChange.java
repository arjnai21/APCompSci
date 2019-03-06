package lab02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MakeChange{
    public static String makeChange(double money){
        String ans = "";
        final double[] VALUES = {100, 50, 20, 10, 5, 1, .25, .10, .05, .01};
        for(double i : VALUES){
            ans += (int) (money / i) + " ";
            money %= i;
            money = (int) (money * 100 + .5);
            money /= 100;
            List<Integer> myList = new LinkedList<Integer>();
            myList.add(5);
            int x = 5;
        }
        return ans;
    }

    public static void main(String[] args){
        System.out.println(makeChange(15767.89));
    }
}
