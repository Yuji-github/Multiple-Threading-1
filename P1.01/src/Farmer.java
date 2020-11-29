import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep; //extends Thread

public class Farmer implements Runnable
{
    private String name, headingTo;
    private int step;
    private static RainbowBridge RB;
    private Thread t;

    public Farmer(int farm, char location)
    {
        if (location == 'N')
        {
            this.name = "N_Farmer" + String.valueOf(farm);
            this.headingTo = "South";
        }
        else
        {
            this.name = "S_Farmer" + String.valueOf(farm);
            this.headingTo = "North";
        }
        this.step = 0;
        this.RB = null;
    }

    //getters
    public String getFName()
    {
        return this.name;
    }

    public String getHeadingTo() { return this.headingTo; }

    public static RainbowBridge getRB() { return RB; }

    public int getStep()
    {
        return this.step;
    }


    //setters
    public void setStep(int new_step) throws InterruptedException
    {
        this.step += new_step;
    }

    public static void setRB(RainbowBridge RB)
    {
        Farmer.RB = RB;
    }


    public void setHeadingTo(String new_headingTo)
    {
        this.headingTo = new_headingTo;
    }

    // functions

    public void uturn()
    {
        System.out.println(this.name + ": Wating for bridge. Going towards " + this.headingTo);

        this.step = 0; //reset the steps
    }

    public void start()
    {
        if(t == null)
        {
            t = new Thread(this, name);
            t.start();
        }
    }

    @Override
    public void run() // this is called from start() as extends Thread
    {
        try
        {
            this.RB.RBridge(this);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
