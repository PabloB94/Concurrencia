import es.upm.babel.cclib.Producto;
import es.upm.babel.cclib.Almacen;
import es.upm.babel.cclib.Semaphore;

class Almacen1 implements Almacen {
   // Producto a almacenar: null representa que no hay producto
   private Producto almacenado = null;

   private static Semaphore productores = new Semaphore (1);
   private static Semaphore consumidores = new Semaphore (0);
   //private static Semaphore semaforo3 = new Semaphore (1);
   private static volatile int turno = 0;

   public Almacen1() {
   }

   public void almacenar(Producto producto) {
      // TODO: protocolo de acceso a la sección crítica y código de
      // sincronización para poder almacenar.
			  productores.await();
			
      // Sección crítica  
			  almacenado = producto;
			
			  consumidores.signal();
	  
	 
      // TODO: protocolo de salida de la sección crítica y código de
      // sincronización para poder extraer.
   }

   public Producto extraer() {
      Producto result;

      // TODO: protocolo de acceso a la sección crítica y código de
      // sincronización para poder extraer.
		 
		 consumidores.await();
      // Sección crítica
		  
      // Sección crítica
			  result = almacenado;
			  almacenado = null;
		
		productores.signal();
		
      // TODO: protocolo de salida de la sección crítica y código de
      // sincronización para poder almacenar.
      return result;
   }
}
