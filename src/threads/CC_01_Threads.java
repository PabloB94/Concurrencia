package threads;
public class CC_01_Threads implements Runnable {
    
    final int SLEEP_TIME = 4000;
    
    int i;

    
    public CC_01_Threads(int i){
        this.i = i;
    }

    public void run() {
        System.out.println("Hello from thread " + i + "!");
        try {
            Thread.sleep(SLEEP_TIME);
			        
		} catch (InterruptedException e) {
			        
		    System.out.println("Thread " + i + " has been interrupted from sleep");
		}
			 
		System.out.println( "Thread " + i + " has ended" );
			   
	}
    
    public static void main(String args[]) {

        int maxThreads = 20;
        
        Thread ThreadArray[] = new Thread[maxThreads];
        
        for (int i = 0; i < maxThreads; i++) {
            ThreadArray[i] = new Thread(new CC_01_Threads(i));
            ThreadArray[i].start();
        }
        
        for (int j = 0; j < maxThreads; j++) {
            
            try {                
                ThreadArray[j].join();                
            } catch (InterruptedException e) {                
                e.printStackTrace();
            }
        }
       
        System.out.println("MAIN: All threads are done");
    }

}