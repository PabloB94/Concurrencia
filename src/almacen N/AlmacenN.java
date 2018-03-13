import es.upm.babel.cclib.Producto;
import es.upm.babel.cclib.Almacen;
import es.upm.babel.cclib.Semaphore;
// TODO: importar la clase de los semáforos.

/**
 * Implementación de la clase Almacen que permite el almacenamiento
 * FIFO de hasta un determinado número de productos y el uso
 * simultáneo del almacén por varios threads.
 */
class AlmacenN implements Almacen {
   private int capacidad = 0;
   private Producto[] almacenado = null;
   private int nDatos = 0;
   private int aExtraer = 0;
   private int aInsertar = 0;

   // TODO: declaración de los semáforos necesarios
   Semaphore mutex;
   Semaphore productores;
   Semaphore consumidores;

   public AlmacenN(int n) {
      capacidad = n;
      almacenado = new Producto[capacidad];
      nDatos = 0;
      aExtraer = 0;
      aInsertar = 0;

      // TODO: inicialización de los semáforos
      mutex = new Semaphore(1);
      productores = new Semaphore(capacidad);
      consumidores = new Semaphore(0);

   }

   public void almacenar(Producto producto) {
      // TODO: protocolo de acceso a la sección crítica y código de
      // sincronización para poder almacenar.
	   productores.await();
      // Sección crítica
	   mutex.await();
	   
      almacenado[aInsertar] = producto;
      nDatos++;
      aInsertar++;
      aInsertar %= capacidad;
      
      mutex.signal();
      // TODO: protocolo de salida de la sección crítica y código de
      // sincronización para poder extraer.
      consumidores.signal();
   }

   public Producto extraer() {
      Producto result;

      // TODO: protocolo de acceso a la sección crítica y código de
      // sincronización para poder extraer.
      consumidores.await();
      // Sección crítica
      mutex.await();
      
      result = almacenado[aExtraer];
      almacenado[aExtraer] = null;
      nDatos--;
      aExtraer++;
      aExtraer %= capacidad;

      mutex.signal();
      // TODO: protocolo de salida de la sección crítica y código de
      // sincronización para poder almacenar.
      productores.signal();

      return result;
   }
}
