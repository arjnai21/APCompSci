package BranchesAndReturns;

public class TwelveDays {
    public String singThatSong(){
        String ans = "";
        for (int i = 1; i < 13; i++) {
            ans += "On the ";
            switch (i){
                case 1:
                    ans += "first";
                    break;
                case 2:
                    ans += "second";
                    break;
                case 3:
                    ans += "third";
                    break;
                case 4:
                    ans +="fourth";
                    break;
                case 5:
                    ans+="fifth";
                    break;
                case 6:
                    ans += "sixth";
                    break;
                case 7:
                    ans += "seventh";
                    break;
                case 8:
                    ans +="eighth";
                    break;
                case 9:
                    ans += "ninth";
                    break;
                case 10:
                    ans += "tenth";
                    break;
                case 11:
                    ans+= "eleventh";
                    break;
                case 12:
                    ans += "twelfth";
                    break;
            }
            ans += " day of high school, my teacher gave to me\n";
            switch (i){
                case 12:
                    ans += "Twelve lunch detentions\n";
                case 11:
                    ans+= "Eleven vocab lists\n";
                case 10:
                    ans += "Ten Practice Problems\n";
                case 9:
                    ans += "Nine performance skits\n";
                case 8:
                        ans +="Eight examinations\n";
                case 7:
                    ans+= "Seven lab reports\n";
                case 6:ans += "Six chapters' reading\n";
                case 5: ans += "Five Shakespeare plays!\n";
                case 4: ans += "Four hundred emails\n";
                case 3:  ans += "Three rough drafts\n";
                case 2: ans += "Two problem sets\n";



            }
            if (i!=1) ans+="And a";
            else ans+= "A";
            ans +=" lecture about my workload.\n\n";

        }
        return ans;
    }


}

