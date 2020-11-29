import java.util.ArrayList;
import java.util.concurrent.*;

public class RainbowBridge
{
    private Semaphore sem;
    private int count;
    private boolean loop = false;
    private TimeUnit time = TimeUnit.MILLISECONDS;

    public RainbowBridge()
    {
        sem = new Semaphore(1);
    }

    public void RBridge(Farmer myfarmer) throws InterruptedException
    {

        //every one wait here
            /*
            N1, S1, N2, S2 (Wait)
            ---------------------- (Go) We do not know what Thread takes bridgeSem
            N1 (Only)
            */

        System.out.println(myfarmer.getFName() + ": Wating for bridge. Going towards " + myfarmer.getHeadingTo());
        // N1 can across the bridge

        do
        {
            try
            {
                sem.acquire(); //get permit to across the bridge

                //critical section from here

                while(myfarmer.getStep() < 20)
                {
                    myfarmer.setStep(5); //every five steps
                    time.sleep(200); //200 m/s delay

                    if(myfarmer.getStep() == 20)
                    {
                        System.out.println(myfarmer.getFName() + ": Across the bridge.");
                        if (myfarmer.getHeadingTo().equalsIgnoreCase("North"))
                        {
                            myfarmer.setHeadingTo("South"); //change the direction
                        }
                        else
                        {
                            myfarmer.setHeadingTo("North");
                        }
                    }
                    else //step 5, 10, 15 only
                    {
                        System.out.println(myfarmer.getFName() + ": Crossing bridge Step " +  myfarmer.getStep());
                    }
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            count++;
            System.out.println("NEON = " + count);

            sem.release(); //release the permit to across the bridge

            //end of the critical section
            time.sleep(200);
            myfarmer.uturn();

        }while(!loop);
    }
}
