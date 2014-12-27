/**
 * 
 */
package cl.bch.motorpagos.command;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.*;
import cl.bch.motorpagos.util.ConfigurationLoader;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MotorPagosHelper;


/**
 * @author boyanedel
 *
 */
public class MotorPagosServicesUtils {
	private static final Logger logger = LoggerFactory.getLogger(MotorPagosServicesUtils.class);
	private static final String HORA_DESDE = " 00:00:00";
	private static final String HORA_HASTA = " 23:59:59";
	
	/**
     * 
     * @param rutCliente
     * @return
     */
    public List<Cliente> findClienteByRut(String rutCliente, EntityManager em){
    	TypedQuery<Cliente> query = em.createQuery("Select c FROM Cliente c WHERE c.rutCliente = ?1 ORDER BY c.fechaHoraActivacion DESC", Cliente.class);
    	query.setParameter(1, rutCliente);
		
    	return query.getResultList();
    }
    
    /**
     * 
     * @param rutEmpresa
     * @param rutApoderado
     * @return
     */
    public List<Comercio> findIdComercioByRutEmpresaApoderado(String rutEmpresa, String rutApoderado, EntityManager em){
    	TypedQuery<Comercio> query = em.createQuery("Select c FROM Comercio c WHERE c.rutComercio = ?1 AND c.rutApoderado = ?2 ORDER BY c.fechaHoraActivacion DESC", Comercio.class);
    	query.setParameter(1, rutEmpresa);
    	query.setParameter(2, rutApoderado);
		
    	return query.getResultList();
    } 
    
    /**
     * 
     * @param idComercio
     * @param nombreConvenio
     * @param em
     * @return
     */
    public Convenio findConvenioByIdComercio(String idComercio, String nombreConvenio, EntityManager em){
    	TypedQuery<Convenio> query = em.createQuery("Select c FROM Convenio c WHERE c.comercio.idComercio = ?1 AND upper(c.nombreConveniio) = ?2", Convenio.class);
    	query.setParameter(1, idComercio);
    	query.setParameter(2, nombreConvenio.toUpperCase());
		
    	List<Convenio> convenios = query.getResultList();
    	if(convenios==null || convenios.isEmpty()){
    		return null;
    	}else{
    		return convenios.get(0);
    	}
    }
    
    /**
     * 
     * @param nroCuenta
     * @param tipoCuenta
     * @param em
     * @return
     * @throws Exception
     */
    public Cuenta findCuentaByNroTipo(String nroCuenta, String tipoCuenta, EntityManager em){
    	Cuenta cuentaDB = null;
    	
    	TypedQuery<Cuenta> query = em.createQuery("Select c FROM Cuenta c WHERE c.nroCuenta = ?1 AND c.tipoCuenta = ?2", Cuenta.class);
    	
    	query.setParameter(1, nroCuenta);
    	query.setParameter(2, tipoCuenta);
    	    
    	List<Cuenta> resultadoQuery = query.getResultList();
    	
    	if(resultadoQuery==null || resultadoQuery.isEmpty()){
    		logger.debug("No fue posible obtener el idCuenta para la cuenta nro[{}] tipo[{}]", nroCuenta, tipoCuenta);
    	}else{
    		cuentaDB = resultadoQuery.get(0);
    	}
    	return cuentaDB;
    }
    
    /**
     * 
     * @param idConvenio
     * @return
     */
    public List<DispositivosConvenio> findConvenioDispositivo(String idDispositivo, EntityManager em){
    	TypedQuery<DispositivosConvenio> query = em.createQuery("Select dc FROM DispositivosConvenio dc WHERE dc.id.idDispositivo = ?1", DispositivosConvenio.class);
    	query.setParameter(1, idDispositivo);
    	
    	return query.getResultList();
    }
    
    /**
     * 
     * @param idConvenio
     * @return
     */
    public List<DispositivosCliente> findClienteDispositivo(String idDispositivo, EntityManager em){
    	TypedQuery<DispositivosCliente> query = em.createQuery("Select dc FROM DispositivosCliente dc WHERE dc.id.idDispositivo = ?1", DispositivosCliente.class);
    	query.setParameter(1, idDispositivo);
    	
    	return query.getResultList();
    } 
    
    /**
     * 
     * @param idCliente
     * @return
     */
    public List<Dispositivo> findDispositivosCliente(String idCliente, EntityManager em){    	
    	TypedQuery<Dispositivo> query = em.createQuery("Select d FROM DispositivosCliente dc, Dispositivo d WHERE dc.id.idCliente = ?1	AND dc.id.idDispositivo = d.idDispositivo ORDER BY d.fechaHoraCreacion DESC", Dispositivo.class);
    	query.setParameter(1, idCliente);
		
    	return query.getResultList();
    }
    
    /**
     * 
     * @param idCliente
     * @param idDispositivo
     * @return List<DispositivosCliente>
     */
    public List<DispositivosCliente> findDispositivosCliente(String idCliente, String idDispositivo, EntityManager em){
    	TypedQuery<DispositivosCliente> query = em.createQuery("Select dc FROM DispositivosCliente dc WHERE dc.id.idCliente = ?1 AND dc.id.idDispositivo = ?2", DispositivosCliente.class);
    	query.setParameter(1, idCliente);
    	query.setParameter(2, idDispositivo);
    	
    	return query.getResultList();
    }
    
    /**
     * 
     * @param idConvenio
     * @return
     */
    public List<Dispositivo> findDispositivosConvenio(String idConvenio, EntityManager em){    	                                                         
    	TypedQuery<Dispositivo> query = em.createQuery("Select d FROM DispositivosConvenio dc, Dispositivo d WHERE dc.id.idConvenio = ?1 AND dc.id.idDispositivo = d.idDispositivo ORDER BY d.fechaHoraCreacion DESC", Dispositivo.class);
    	query.setParameter(1, idConvenio);
    	
    	return query.getResultList();
    }    
    
    /**
     * 
     * @param idCliente
     * @param idDispositivo
     * @return List<DispositivosConvenio>
     */
    public List<DispositivosConvenio> findDispositivosConvenio(String idConvenio, String idDispositivo, EntityManager em){
    	TypedQuery<DispositivosConvenio> query = em.createQuery("Select dc FROM DispositivosConvenio dc WHERE dc.id.idConvenio = ?1 AND dc.id.idDispositivo = ?2", DispositivosConvenio.class);
    	query.setParameter(1, idConvenio);
    	query.setParameter(2, idDispositivo);
    	
    	return query.getResultList();
    }
    
    /**
     * 
     * @param idConvenio
     * @param fechaDesde
     * @param fechaHasta
     * @return
     * @throws ParseException
     */
    public int countTrxByIdConvenioFechas(String idConvenio, String fechaDesde, String fechaHasta, EntityManager em) throws ParseException{

    	TypedQuery<Long> count = em.createQuery("Select count(trx.idTrxsPago) FROM TrxsPago trx WHERE trx.convenio.idConvenio = ?1 AND trx.fechaHoraTrx BETWEEN ?2 AND ?3", Long.class);
    	
    	Date fechDesde = MotorPagosHelper.getFechaDate(fechaDesde+MotorPagosServicesUtils.HORA_DESDE);
    	Date fechHasta = MotorPagosHelper.getFechaDate(fechaHasta+MotorPagosServicesUtils.HORA_HASTA);
    	
    	count.setParameter(1, idConvenio);
    	count.setParameter(2, fechDesde);
    	count.setParameter(3, fechHasta);    
    	    
    	return count.getSingleResult().intValue();   
    }
    
    /**
     * 
     * @param idComercio
     * @param fechaDesde
     * @param fechaHasta
     * @return
     * @throws ParseException
     */
    public int countTrxByComercioFechas(String idComercio, String fechaDesde, String fechaHasta, EntityManager em) throws ParseException{
    	    	
    	TypedQuery<Long> count = em.createQuery("Select count(trx.idTrxsPago) FROM TrxsPago trx WHERE trx.convenio.comercio.idComercio = ?1 AND trx.fechaHoraTrx BETWEEN ?2 AND ?3", Long.class);
    	
    	Date fechDesde = MotorPagosHelper.getFechaDate(fechaDesde+MotorPagosServicesUtils.HORA_DESDE);
    	Date fechHasta = MotorPagosHelper.getFechaDate(fechaHasta+MotorPagosServicesUtils.HORA_HASTA);
    	
    	count.setParameter(1, idComercio);
    	count.setParameter(2, fechDesde);
    	count.setParameter(3, fechHasta);    
    	    
    	return count.getSingleResult().intValue();   
    }
    
    /**
     * 
     * @param idComercio
     * @param fechaDesde
     * @param fechaHasta
     * @return
     * @throws ParseException
     */
    public int countTrxByClienteFechas(String idCliente, String fechaDesde, String fechaHasta, EntityManager em) throws ParseException{
    	    	
    	TypedQuery<Long> count = em.createQuery("Select count(trx.idTrxsPago) FROM TrxsPago trx WHERE trx.cliente.idCliente = ?1 AND trx.fechaHoraTrx BETWEEN ?2 AND ?3", Long.class);
    	
    	Date fechDesde = MotorPagosHelper.getFechaDate(fechaDesde+MotorPagosServicesUtils.HORA_DESDE);
    	Date fechHasta = MotorPagosHelper.getFechaDate(fechaHasta+MotorPagosServicesUtils.HORA_HASTA);
    	
    	count.setParameter(1, idCliente);
    	count.setParameter(2, fechDesde);
    	count.setParameter(3, fechHasta);    
    	    
    	return count.getSingleResult().intValue();   
    }
    
    /**
     * 
     * @param idConvenio
     * @param fechaDesde
     * @param fechaHasta
     * @return List<TrxsPago>
     * @throws ParseException 
     */
    public List<TrxsPago> findTrxByIdConvenioFechas(String idConvenio, String fechaDesde, String fechaHasta, int numeroPagina, EntityManager em) throws ParseException{
    	
    	TypedQuery<TrxsPago> query = em.createQuery("Select trx FROM TrxsPago trx WHERE trx.convenio.idConvenio = ?1 AND trx.fechaHoraTrx BETWEEN ?2 AND ?3 ORDER BY trx.fechaHoraTrx DESC", TrxsPago.class);
    	
    	if(numeroPagina!=0){
    		query.setFirstResult(Integer.parseInt(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.REGISTROS_X_PAGINA))*(numeroPagina-1));
    		query.setMaxResults(Integer.parseInt(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.REGISTROS_X_PAGINA)));
    	}
    	
    	Date fechDesde = MotorPagosHelper.getFechaDate(fechaDesde+MotorPagosServicesUtils.HORA_DESDE);
    	Date fechHasta = MotorPagosHelper.getFechaDate(fechaHasta+MotorPagosServicesUtils.HORA_HASTA);
    	
    	query.setParameter(1, idConvenio);
    	query.setParameter(2, fechDesde);
    	query.setParameter(3, fechHasta);    
    	
    	return query.getResultList();    
    }
    
    /**
     * 
     * @param idComercio
     * @param fechaDesde
     * @param fechaHasta
     * @return List<TrxsPago>
     * @throws ParseException 
     */
    public List<TrxsPago> findTrxByIdComercioFechas(String idComercio, String fechaDesde, String fechaHasta, int numeroPagina, EntityManager em) throws ParseException{
    	
    	TypedQuery<TrxsPago> query = em.createQuery("Select trx FROM TrxsPago trx WHERE trx.convenio.comercio.idComercio = ?1 AND trx.fechaHoraTrx BETWEEN ?2 AND ?3 ORDER BY trx.fechaHoraTrx DESC", TrxsPago.class);
    	
    	if(numeroPagina!=0){
    		query.setFirstResult(Integer.parseInt(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.REGISTROS_X_PAGINA))*(numeroPagina-1));
    		query.setMaxResults(Integer.parseInt(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.REGISTROS_X_PAGINA)));
    	}
    	
    	Date fechDesde = MotorPagosHelper.getFechaDate(fechaDesde+MotorPagosServicesUtils.HORA_DESDE);
    	Date fechHasta = MotorPagosHelper.getFechaDate(fechaHasta+MotorPagosServicesUtils.HORA_HASTA);
    	
    	query.setParameter(1, idComercio);
    	query.setParameter(2, fechDesde);
    	query.setParameter(3, fechHasta);    
    	
    	return query.getResultList();    
    }
    
    /**
     * 
     * @param rutEmpresa
     * @param rutApoderado
     * @return Cliente
     */
    public Cliente findIdClienteByRut(String rutCliente, EntityManager em){
    	TypedQuery<Cliente> query = em.createQuery("Select c FROM Cliente c WHERE c.rutCliente = ?1 ORDER BY c.fechaHoraActivacion DESC", Cliente.class);
    	query.setParameter(1, rutCliente);
    	
    	return query.getSingleResult();
    }
    
    /**
     * 
     * @param idComercio
     * @param fechaDesde
     * @param fechaHasta
     * @return List<TrxsPago>
     * @throws ParseException 
     */
    public List<TrxsPago> findTrxByIdClienteFechas(String idCliente, String fechaDesde, String fechaHasta, int numeroPagina, EntityManager em) throws ParseException{
    	
    	TypedQuery<TrxsPago> query = em.createQuery("Select trx FROM TrxsPago trx WHERE trx.cliente.idCliente = ?1 AND trx.fechaHoraTrx BETWEEN ?2 AND ?3 ORDER BY trx.fechaHoraTrx DESC", TrxsPago.class);
    	
    	if(numeroPagina!=0){
    		query.setFirstResult(Integer.parseInt(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.REGISTROS_X_PAGINA))*(numeroPagina-1));
    		query.setMaxResults(Integer.parseInt(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.REGISTROS_X_PAGINA)));
    	}
    	
    	Date fechDesde = MotorPagosHelper.getFechaDate(fechaDesde+MotorPagosServicesUtils.HORA_DESDE);
    	Date fechHasta = MotorPagosHelper.getFechaDate(fechaHasta+MotorPagosServicesUtils.HORA_HASTA);
    	
    	query.setParameter(1, idCliente);
    	query.setParameter(2, fechDesde);
    	query.setParameter(3, fechHasta);    
    	
    	return query.getResultList();    
    }
    
    /**
     * 
     * @param idDispositivo
     * @param tipoConsulta
     * @param em
     * @return
     * @throws ParseException
     */
    public List<TrxsPago> findTrxRecientesByDispositivo(String idDispositivo, String tipoConsulta, EntityManager em) throws ParseException{
    	
    	TypedQuery<TrxsPago> query = null;
    	
    	if(tipoConsulta.equals(ConstantesMotorPagos.COMERCIO)){
    		query = em.createQuery("Select trx FROM TrxsPago trx WHERE trx.idDispositivoComercio = ?1 ORDER BY trx.fechaHoraTrx DESC", TrxsPago.class);
		}else if(tipoConsulta.equals(ConstantesMotorPagos.CLIENTE)){
			query = em.createQuery("Select trx FROM TrxsPago trx WHERE trx.idDispositivoCliente = ?1 ORDER BY trx.fechaHoraTrx DESC", TrxsPago.class);
		}
    	    	  	
    	query.setFirstResult(0);
    	query.setMaxResults(Integer.parseInt(ConfigurationLoader.getInstance().getProperty(ConfigurationLoader.REGISTROS_X_PAGINA)));
    	
    	query.setParameter(1, idDispositivo);
    	    
    	return query.getResultList();    
    }  
    
    /**
     * 
     * @param idComercio
     * @param fechaDesde
     * @param fechaHasta
     * @return List<TrxsPago>
     * @throws ParseException 
     */
    public List<TrxsPago> findTrxByIdDispositivoFechas(String idVendedor, String idDispositivo, String fechaDesde, String fechaHasta, EntityManager em) throws ParseException{
    	
    	TypedQuery<TrxsPago> query;
    	
    	if(idVendedor!=null && idVendedor.length()>0){
    		query = em.createQuery("Select trx FROM TrxsPago trx WHERE trx.estadoTrxl = '"+ConstantesMotorPagos.TRX_PAGADA+"' AND trx.idVendedor = '"+idVendedor+"' AND trx.idDispositivoComercio = ?1 AND trx.fechaHoraTrx BETWEEN ?2 AND ?3 ORDER BY trx.idVendedor, trx.fechaHoraTrx ASC", TrxsPago.class);    		
    	}else{
    		query = em.createQuery("Select trx FROM TrxsPago trx WHERE trx.estadoTrxl = '"+ConstantesMotorPagos.TRX_PAGADA+"' AND trx.idDispositivoComercio = ?1 AND trx.fechaHoraTrx BETWEEN ?2 AND ?3 ORDER BY trx.idVendedor, trx.fechaHoraTrx ASC", TrxsPago.class);
    	}
    	
    	Date fechDesde = MotorPagosHelper.getFechaDateddMMyyyy(fechaDesde+MotorPagosServicesUtils.HORA_DESDE);
    	Date fechHasta = MotorPagosHelper.getFechaDateddMMyyyy(fechaHasta+MotorPagosServicesUtils.HORA_HASTA);
    	
    	query.setParameter(1, idDispositivo);
    	query.setParameter(2, fechDesde);
    	query.setParameter(3, fechHasta);    
    	    
    	return query.getResultList();    
    }
}
