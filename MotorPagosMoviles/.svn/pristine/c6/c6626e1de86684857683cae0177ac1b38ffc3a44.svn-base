/**
 * 
 */
package cl.bch.motorpagos.persistencia;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author boyanedel
 *
 */
public final class ManagerFactoryInitializer {
	private final EntityManagerFactory factory;
	private static ManagerFactoryInitializer instancia;
	
	/**
	 * 
	 */
	private ManagerFactoryInitializer(){
		this.factory = Persistence.createEntityManagerFactory("pagosHibernate");
	}
	
	/**
	 * 
	 * @return
	 */
	public static ManagerFactoryInitializer getInstance(){
		synchronized (ManagerFactoryInitializer.class){
			if(instancia==null){
				instancia = new ManagerFactoryInitializer();
			}
		}	
		return instancia;
	}
	
	/**
	 * 
	 * @return
	 */
	public EntityManagerFactory getEntityManagerFactory(){
		return this.factory;
	}
}
