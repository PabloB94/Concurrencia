package carrera;
public class CC_02_Carrera {
	
	static int cont = 0;
	
	static class Incrementador extends Thread {
		
		public void run() {
	        cont++;
	    }

	}
	static class Decrementador extends Thread {
		
		public void run() {
	        cont--;
	    }

	}	 
	public static void main (  ) {
		
		int maxThreads = 20;
		
		Thread ThreadArray[] = new Thread[maxThreads];
		
		for (int i = 0; i < maxThreads; i = i + 2) {
            ThreadArray[i] = new Incrementador();
            ThreadArray[i+1] = new Decrementador();
            ThreadArray[i].start();
            ThreadArray[i+1].start();
        }
		
		for (int j = 0; j < maxThreads; j++) {
            
            try {                
                ThreadArray[j].join();                
            } catch (InterruptedException e) {                
                e.printStackTrace();
            }
            
        }
		
        System.out.println(cont);
	}
}


