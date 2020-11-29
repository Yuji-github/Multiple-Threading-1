import java.util.*;
import java.io.*;

public class P1
{
    private RainbowBridge RB = new RainbowBridge();
    private ArrayList<Farmer> myFarmer = new ArrayList<>();
    private int northFarmer = -999, southFarmer = -999; //ready to store the value into the array
    private char north, south;

    private void importFile() //given file name comes here    String fileName
    {
        String importName = null;
        Scanner importStream = null;

        try //this try is try to access the file
        {
            System.out.println("Looks Like You Gave Me a File Name.");
            System.out.println("...Importing...");
            //importName = fileName;
            importName = "/Users/Yugi/IdeaProjects/P1/src/P1-1in.txt"; //test
            importStream = new Scanner(new File(importName));

            while(importStream.hasNextLine())
            {
                try //this try is try to scan the contexts
                {
                    String line = importStream.nextLine(); //reading lines
                    if(line.equals("")) //to preventing to stop run when the begining of the line is nothing
                    {
                        continue;
                    }

                    String[] parts = line.split(" "); //sprit the lines by space

                    if(parts[0].substring(0,1).equalsIgnoreCase("N")) // "N=2," is imported
                    {
                        northFarmer = Integer.parseInt(parts[0].substring(2,3)); //updating north farmers value
                        north = 'N';
                        for(int i = 0; i < northFarmer; i++)
                        {
                            myFarmer.add(new Farmer(i+1, north)); //, brideSem, acrossSem));
                        }

                        if(parts[1].substring(0,1).equalsIgnoreCase("S")) //S=2
                        {
                            southFarmer = Integer.parseInt(parts[1].substring(2,3));
                            south = 'S';
                            for(int i = 0; i < northFarmer; i++)
                            {
                                myFarmer.add(new Farmer(i+1, south)); //, brideSem, acrossSem));
                            }
                        }
                    }// end of if(parts[0].substring(0,1).equalsIgnoreCase("N")), if there is something issues about importing fix conditions or if orders
                } //end of try to read

                catch(ArrayIndexOutOfBoundsException a)
                {
                    System.out.println("Invalid Line Format: Not Enough Information");
                }
                catch(NoSuchElementException | NullPointerException n)
                {
                    System.out.println(n.getMessage());
                }
            } // end of while loop
        } // end of try to access the files

        catch(FileNotFoundException e) //catch for access files' errors
        {
            System.out.println("!!! Really !!! ");
            System.out.println("!!! Don't you know how to manage your file name !!! ");
            System.out.println("After I count to 10, I'll be a nice guy");
            System.out.println("10, 9, 8, ... , 2, 1 ...");
            System.out.println("Error Opening The File " + importName);
        }
        finally //finally done to store the values from the text file
        {
            if(importStream !=null)
            {
                System.out.println("Importing is done. Let simulate the results =) "); //not necessary to show the nessage
                importStream.close(); //closing import stream for the next
            }
        }
    }

    public void run() throws InterruptedException //here is actual main runs for security reasons       String fileName
    {
        System.out.println("G'day, I'm Haida, How are you?");
        System.out.println("I will show you how to handle stubborn Newzy Farmers.");
        System.out.println("Follow me!!");
        importFile(); //given name from Java cmd goes to importing function    fileName

        int size = myFarmer.size();

        Farmer.setRB(RB);

        for(int i = 0; i < size; i++)
        {
            myFarmer.get(i).start();
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        P1 sim = new P1();
        //String fileName = args[0]; //this is for java cmd and any file name can take and user can gives any file from the cmd
        //sim.run(fileName); //passing the file name to the run
        sim.run(); //do not submit with this
    }

}
