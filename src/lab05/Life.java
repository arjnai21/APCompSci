package lab05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Life{
    public static final int MAX_ROWS=40;
    public static final int MAX_COLUMNS=60;
    private boolean[][] grid;     // MAX_ROWS by MAX_COLUMNS
    private int generation=0;   // "generation" counts the number of times passTime() has been called

    /**
     * Default constructor that creates an empty grid.
     */
    public Life(){
        // TODO
        grid=new boolean[MAX_ROWS][MAX_COLUMNS];
    }

    /**
     * This constructor loads the starting configuration of the grid from "filename".
     * A "*" in the file indicates a "true" location in
     * the grid.  Any other character indicates a "false" location.
     * The file should load so that it is centered horizontally
     * and vertically in the grid.
     * <p>
     * This method should not throw an exception.  If the file is not found,
     * the method should print something like: File, xxxxxx, not found.
     */
    public Life(String fileName){
        Scanner reader;
        Scanner lineCounter;
        File file;
        grid=new boolean[MAX_ROWS][MAX_COLUMNS];

        try{
            file=new File(fileName);
            reader=new Scanner(file);
            lineCounter=new Scanner(file);
            int counter=0;
            while(lineCounter.hasNextLine()){ //https://stackoverflow.com/questions/18902706/java-scanner-count-lines-in-file
                counter++;
                lineCounter.nextLine();
            }
            //convert file to array of char arrays
            char[][] text=new char[counter][];

            for(int i=0;i<text.length;i++){
                String line=reader.nextLine();
                char[] lineArray=line.toCharArray();
                text[i]=lineArray;
            }
            int startRow=getStartRow(text);
            int startColumn=getStartColumn(text);
            for(int i=0;i<text.length;i++){
                for(int j=0;j<text[i].length;j++){
                    if(text[i][j]=='*')
                        grid[i+startRow][j+startColumn]=true;
                }
            }


        }catch(Exception ex){
            System.out.printf("File %s not found. \n",fileName);
        }


    }


    /**
     * Pass time by 1 generation:
     * <p>
     * For a given cell, determine the number of neighbors that are alive. (All 8 cells
     * touching the given cell are its neighbors.) Note that the grid wraps around the edge
     * of the screen, so cells on the edges of the grid have neighbors on the far side of
     * the grid.
     * <p>
     * For a given cell that is currently alive(true) and has exactly 2 or 3 neighbors that
     * are alive, the cell will live in the next generation.  Otherwise, it will die.
     * <p>
     * For a given cell that is currently dead(false) if it has exactly 3 neighbors that are
     * alive, it will come alive in the next generation.  Otherwise, it remains dead.
     * <p>
     * It is important to note that all changes from time = t to time = t + 1 are
     * considered to occur simultaneously, so that, for instance, if a cell is going
     * to die at the next time step, it still counts as "alive" when computing
     * any of its neighbors� state changes.
     */
    public void passTime(){
        boolean[][] copiedGrid=copyGrid(grid);
        for(int i=0;i<MAX_ROWS;i++){
            for(int j=0;j<MAX_COLUMNS;j++){
                boolean[] neighbors=getNeighbors(i,j);
                int aliveCount=0;
                for(boolean x : neighbors){
                    if(x)
                        aliveCount++;
                }
                if(grid[i][j]){
                    if(!(aliveCount==2||aliveCount==3))
                        copiedGrid[i][j]=false;
                }else{
                    if(aliveCount==3)
                        copiedGrid[i][j]=true;
                }
            }

        }
        grid=copiedGrid;
        generation++;
    }

    /**
     * Accessor method for locations on the grid.  LifeController uses this method to
     * determine if a current cell is alive or dead.
     */
    public boolean isAlive(int row,int column){
        return grid[row][column];
    }

    /**
     * Accessor method -- returns the number of times passTime has been called.
     */
    public int getGeneration(){

        return generation;
    }

    /**
     * Resets the generation count to 0.
     */
    public void resetGeneration(){

        generation=0;
    }

    /**
     * Inverts the value of the grid at the given location.
     */
    public void toggleGridValue(int row,int column){
        grid[row][column]=!grid[row][column];
    }

    private int getWidthOfFile(char[][] file){
        int length=file[0].length;
        for(int i=1;i<file.length;i++){
            if(file[i].length>length)
                length=file[i].length;
        }

        return length;
    }

    private int getHeightOfFile(char[][] file){
        return file.length;
    }

    private int getStartRow(char[][] file){
        int rows=getHeightOfFile(file);
        return MAX_ROWS/2-rows/2;
    }

    private int getStartColumn(char[][] file){
        int columns=getWidthOfFile(file);
        return MAX_COLUMNS/2-columns/2;
    }

    private boolean[] getNeighbors(int i,int j){
        boolean[] neighbors=new boolean[8];
        neighbors[0]=grid[(i+1)%MAX_ROWS][j]; // one below
        neighbors[1]=grid[i][(j+1)%MAX_COLUMNS]; // one right
        neighbors[2]=grid[(i-1+MAX_ROWS)%MAX_ROWS][j]; // one above
        neighbors[3]=grid[i][(j-1+MAX_COLUMNS)%MAX_COLUMNS]; // one left
        neighbors[4]=grid[(i+1)%MAX_ROWS][(j+1)%MAX_COLUMNS]; // one below and one right
        neighbors[5]=grid[(i+1)%MAX_ROWS][(j-1+MAX_COLUMNS)%MAX_COLUMNS]; // one below and one left
        neighbors[6]=grid[(i-1+MAX_ROWS)%MAX_ROWS][(j+1)%MAX_COLUMNS]; // one above and one right
        neighbors[7]=grid[(i-1+MAX_ROWS)%MAX_ROWS][(j-1+MAX_COLUMNS)%MAX_COLUMNS]; //one above and one left

        return neighbors;
    }

    private boolean[][] copyGrid(boolean[][] grid){
        boolean[][] newGrid=new boolean[MAX_ROWS][MAX_COLUMNS];
        for(int i=0;i<MAX_ROWS;i++){
            /*for(int j=0;j<MAX_COLUMNS;j++){
                newGrid[i][j] = grid[i][j];
            }*/
            System.arraycopy(grid[i],0,newGrid[i],0,MAX_COLUMNS);
        }
        return newGrid;
    }
}
