package lab04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ASCIIText{

    public static void main(String[] args){

        Scanner in=new Scanner(System.in);
        System.out.print("What font would you like: ");
        String fileName=in.nextLine().replace("\n","");
        System.out.print("\nText to print: ");
        String text=in.nextLine();
        File file;
        Scanner reader;

        try{
            try{
                file=new File(fileName);
                reader=new Scanner(file);
            }catch(Exception ex){
                System.out.printf("File %s not found. Please try again.",fileName);
                throw new FileNotFoundException();
            }
            int depth=0;
            char[] order;
            String line=reader.nextLine();
            while(line.length() >= 2&&line.substring(0,2).equals("//")){
                line=reader.nextLine();
            }
            String firstLine=line;
            order=line.toCharArray();
            depth=order[0]-'0';


            // read letters into three dimensional array;
            String[][] font=new String[order.length-2][];
            int count=0;
            reader.nextLine();
            while(reader.hasNextLine()){
                line=reader.nextLine();

                String[] letter=new String[depth];
                for(int i=0;i<depth;i++){
                    letter[i]=line;
                    try{
                        line=reader.nextLine();
                    }catch(Exception ex){
                    }
                }

                font[count]=letter;
                count++;
            }

            char[] inp=text.toCharArray();
            boolean no=false;
            char notFound=' ';
            ArrayList<ArrayList<String>> finalLetters=new ArrayList<>();
            for(char i : inp){
                int ind=firstLine.indexOf(i,2)-2;
                if(ind==-3){
                    no=true;
                    notFound=i;
                    break;
                }
                finalLetters.add(new ArrayList<>(Arrays.asList(font[ind])));
            }
            for(int i=0;i<depth;i++){
                for(int j=0;j<finalLetters.size();j++){
                    System.out.print(finalLetters.get(j).get(i));
                }
                System.out.println();
            }

            if(no)
                System.out.printf("Character %c not included in font. Please try again",notFound);

        }catch(Exception ex){
            ex.printStackTrace();
        }


    }
}
