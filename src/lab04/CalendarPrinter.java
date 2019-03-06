package lab04;

public class CalendarPrinter{
    private final static String[] months={"","January","February","March","April","May","June","July","August","September","October","November","December"};
    private static int[] monthLengths={-1,31,28,31,30,31,30,31,31,30,31,30,31};

    static void printMonth(int year,int month,int janFirst,boolean leapYear){
        String spacing=Integer.toString(12+(months[month].length()+Integer.toString(year).length())/2);
        System.out.printf("%"+spacing+"s\n",months[month]+" "+year,"");
        System.out.println(" Su Mo Tu We Th Fr Sa");
        int[][] days=getMonthIntArray(month,janFirst,leapYear);
        //at this point, should have a 5 by 7 array with the days in it, and 0 if thers no day
        String[][] printArray=convertIntMonthToStringMonth(days,month,false);

        //print the array
        for(int i=0;i<printArray.length;i++){
            for(int j=0;j<printArray[i].length;j++){
                System.out.printf("%3s",printArray[i][j]);
            }
            System.out.println();

        }
    }

    static String[] getOneDMonth(String[][] month){
        String[] ret=new String[5];
        String week="";

        for(int i=0;i<month.length;i++){
            for(int j=0;j<month[i].length;j++){
                //join elements of month[i]
                week+=String.format("%3s",month[i][j]);
            }
            ret[i]=week+"    ";
            week="";
        }
        return ret;
    }

    private static String[] chainThreeMonth(int startMonth,int janFirst,boolean leapYear){
        String[][] threeMonths=new String[3][];
        String[] ret=new String[5];
        for(int i=startMonth;i<startMonth+3;i++){
            threeMonths[i-startMonth]=getOneDMonth(getMonthStringArray(i,janFirst,leapYear));
        }

        for(int i=0;i<threeMonths[0].length;i++){
            String line="";
            for(int j=0;j<threeMonths.length;j++){
                line+=threeMonths[j][i];
            }
            ret[i]=line;
        }
        return ret;
    }

    private static String[][] getMonthStringArray(int month,int janFirst,boolean leapYear){ //returns two d array of nums with strs
        return convertIntMonthToStringMonth(getMonthIntArray(month,janFirst,leapYear),month,true);
    }

    private static int[][] getMonthIntArray(int month,int janFirst,boolean leapYear){
        if(leapYear)
            monthLengths[2]++;
        int firstDay=getFirstDay(month,janFirst);
        int[][] days=new int[5][7];
        int currentDay=firstDay;
        int dayCount=1;

        for(int i=0;i<5;i++){
            for(int j=0;j<7;j++){
                if(dayCount==0)
                    continue;
                days[i][currentDay]=dayCount;

                if(dayCount==monthLengths[month]){
                    dayCount=0;
                }else
                    dayCount++;
                if(currentDay==6){
                    currentDay=0;
                    break;
                }
                currentDay=(currentDay+1)%7;
            }
        }
        if(leapYear)
            monthLengths[2]--;
        return days;
    }

    static void printYear(int year,int janFirst,boolean leapYear){
        String yearSpace=Integer.toString(34+(Integer.toString(year).length()+Integer.toString(year).length())/2);
        System.out.printf("%"+yearSpace+"d\n\n",year);
        for(int i=1;i<11;i+=3){
            //print header
            int month1Space=(10+(months[i].length()+Integer.toString(year).length())/2);
            int month2Space=(10+(months[i+1].length()+Integer.toString(year).length())/2);
            int month3Space=(10+(months[i+2].length()+Integer.toString(year).length())/2);

            System.out.printf("%"+Integer.toString(month1Space)+"s"+"%"+Integer.toString(25-month1Space)+"s",months[i],"");
            System.out.printf("%"+Integer.toString(month2Space)+"s"+"%"+Integer.toString(25-month2Space)+"s",months[i+1],"");
            System.out.printf("%"+Integer.toString(month3Space)+"s\n",months[i+2]);
            System.out.printf("%21s","Su Mo Tu We Th Fr Sa");
            System.out.printf("%25s","Su Mo Tu We Th Fr Sa");
            System.out.printf("%25s\n","Su Mo Tu We Th Fr Sa");

            String[] threeMonths=chainThreeMonth(i,janFirst,leapYear);
            for(String j : threeMonths){
                System.out.println(j);
            }
            System.out.println("\n");
        }
    }

    private static String[][] convertIntMonthToStringMonth(int[][] days,int month,boolean printYear){
        String[][] ret=new String[5][7];
        for(int i=0;i<days.length;i++){
            for(int j=0;j<days[i].length;j++){
                if(printYear)
                    ret[i][j]=getDayVal(days[i][j],j,month);
                else if(days[i][j]==0)
                    ret[i][j]=" ";
                else
                    ret[i][j]=Integer.toString(days[i][j]);
            }
        }
        return ret;
    }

    private static String getDayVal(int date,int day,int month){
        final String RED_COLOR=" \u001B[31m";
        final String RESET="\u001B[0m";
        String ret="";

        if(date==0)
            return " ";
            //President's Day
        else if(month==2&&date >= 15&&date<=21&&day==1)
            ret="p";

            //Veteran's Day
        else if(month==11&&date==11)
            ret="v";

            //Thanksgiving
        else if(month==11&&date >= 22&&date<=28&&day==4)
            ret="t";

            //Memorial Day
        else if(month==5&&date >= 25&&date<=31&&day==1)
            ret="m";

            //Christmas
        else if(month==12&&date==25)
            ret="c";

            //New Years
        else if(month==1&&date==1)
            ret="y";

            //4th of July
        else if(month==7&&date==4)
            ret="4";

            //Labor Day
        else if(month==9&&date >= 1&&date<=7&&day==1)
            ret="L";

            //Tax Day
        else if(month==4&&date==15&&day!=0&&day!=6)
            ret="x";
        else if(month==4&&date==16&&day==1)
            ret="x";
        else if(month==4&&date==17&&day==1)
            ret="x";


        else
            return Integer.toString(date);
        return RED_COLOR+"*"+ret+RESET;


    }


    public static void main(String[] args){
        printMonth(2016,2,5,true);
        printYear(2013,2,false);
    }

    static int getFirstDay(int month,int janFirst){ //returns first day of month
        int firstDay=janFirst;
        for(int i=2;i<month+1;i++){
            firstDay=(firstDay+monthLengths[i-1])%7;
        }
        return firstDay;
    }
}
