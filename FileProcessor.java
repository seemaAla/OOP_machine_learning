import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileProcessor 
{
    String filename;
    File file;
    Scanner myScanner;

    //constructor
    FileProcessor(String filename) 
    {
        this.filename = filename;
        this.file = new File(filename); 
    }

    //method that reads the file and return its contents in an ArrayList of String arrays
    ArrayList<String[]> readFile() 
    {
        //holds each row as a String array
        ArrayList<String[]> file_data = new ArrayList<>(); 
        
        try 
        {
            //opens file for reading
            myScanner = new Scanner(file); 

            //loops over each row in file
            while (myScanner.hasNextLine()) 
            {
                String line = myScanner.nextLine(); 
                //split line into array of string whenever it sees a comma
                String[] values = line.split(",");  
                //adds those values into the arraylist 
                file_data.add(values); 
            }
            myScanner.close(); 
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("Error: File not found - " + e.getMessage());
        }

        return file_data; 
    }



    public void addRow(String age, String health, String medical, String location, String label) 
    {
        //creating objects
        FileWriter fw = null;
        PrintWriter pw = null;

        try 
        {
            //opens the file in append mode 'true'
            fw = new FileWriter(filename, true);
            pw = new PrintWriter(fw);

            //writes that row to file 
            pw.println(age + "," + health + "," + medical + "," + location + "," + label);

        } 
        catch (IOException e) 
        {
            System.err.println("Error writing to file: " + e.getMessage());

        } 
        finally 
        {
            //closing print writer and File writer
            if (pw != null) 
            {
                pw.close();  
            }
        }
    }
}
