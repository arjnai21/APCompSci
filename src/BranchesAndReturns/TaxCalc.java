package BranchesAndReturns;
public class TaxCalc {

    //brackets go from highest to lowest and are the floor of that particular bracket
    //Therefore, value for bracket must be multiplied by bracket rate under it
    public static void main(String[] args){
        double income = 2000000;
        System.out.println(oldTax(income));
        System.out.println(newTax(income));
        System.out.println(taxDifference(income));
    }


    static double[] BRACKETS = {0, 17400, 70700, 142700, 217450};
    static double[] BRACKET_RATES = {.10, .15, .25, .28, .33};
    static double[] BRACKET_CONSTANTS = {0, 1740, 9735, 27735, 48665};


    public static double oldTaxD(double income){
        double taxes=0;
        if(income >= BRACKETS[5]){
            taxes+=(income-BRACKETS[4])*BRACKET_RATES[4];
            taxes+=BRACKET_CONSTANTS[4];
        }
        else if (income > BRACKETS[1]){
            for(int i=2;i<BRACKETS.length;i++){
                if(income<BRACKETS[i]){
                    taxes+=BRACKET_RATES[i-1]*(income-BRACKETS[i-1]);
                    taxes+=BRACKET_CONSTANTS[i-1];
                    break;
                }
            }
        }
        return taxes;
    }

    public static String oldTax(double income){
        return String.format("$%,.2f", oldTaxD(income));
    }


    public static double newTaxD(double income){
        double newTax;
        if (income < BRACKETS[4])
            newTax = oldTaxD(income);
        else
            newTax = BRACKET_RATES[4] * income;
        return newTax;
    }

    private static String newTax(double income){
        return String.format("$%,.2f", newTaxD(income));
    }



    public static String taxDifference(double income){
        return String.format("$%,.2f", (newTaxD(income) - oldTaxD(income)));
    }

}


