import java.io.File;
import java.util.Scanner;

public class test{
    public static void main(String[] args){
        Scanner reader;
        Scanner lineCounter;
        File file;
        String fileName = "glider.txt";
        try{
            file = new File(fileName);
            reader = new Scanner(file);
            lineCounter = new Scanner(file);
            int counter = 0;
            while(lineCounter.hasNextLine()){ //https://stackoverflow.com/questions/18902706/java-scanner-count-lines-in-file
                counter++;
                lineCounter.nextLine();
            }
            char[][] text = new char[counter][];

            for(int i=0;i<text.length;i++){
                String line = reader.nextLine();
                char[] lineArray = line.toCharArray();
                text[i] = lineArray;
            }
            int x = 9;




        }catch(Exception ex){
            System.out.printf("File %s not found. \n", fileName);
        }

    }
}
