/**
 * 
 */
package cl.bch.motorpagos.command;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bch.motorpagos.persistencia.TrxsPago;
import cl.bch.motorpagos.util.ConstantesMotorPagos;
import cl.bch.motorpagos.util.MapperVO;
import cl.bch.motorpagos.util.MensajesError;
import cl.bch.motorpagos.util.MotorPagosHelper;
import cl.bch.motorpagos.vo.CierreDiarioVO;
import cl.bch.motorpagos.vo.MovimientoCierreVO;
import cl.bch.motorpagos.vo.MovimientosDispositivoVO;
import cl.bch.motorpagos.vo.MovimientosVendedorVO;
import cl.bch.motorpagos.vo.RespuestaCierreDiarioVO;

/**
 * @author boyanedel
 *
 */
public class CierreDispCommand extends IServiceCommand{
	private static final Logger logger = LoggerFactory.getLogger(CierreDispCommand.class);

    /**
     * 
     * @param cierreDiario
     * @return
     */
    public RespuestaCierreDiarioVO cierreDiarioDispositivo(CierreDiarioVO cierreDiario){
    	EntityManager em = this.factory.createEntityManager();
    	RespuestaCierreDiarioVO respuesta = new RespuestaCierreDiarioVO();
    	MotorPagosServicesUtils utilServices = new MotorPagosServicesUtils();
    	try{
    		
    		logger.debug("Se calcula el total de ventas para el dispositivo ["+cierreDiario.getIdDispositivo()+"] en el dia ["+cierreDiario.getFechaCierre()+"].");
    		List<TrxsPago> listaPagos = utilServices.findTrxByIdDispositivoFechas(cierreDiario.getIdVendedor(), cierreDiario.getIdDispositivo(), cierreDiario.getFechaCierre(), cierreDiario.getFechaCierre(), em);
	    	
    		if(listaPagos==null || listaPagos.isEmpty()){
    			respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, "El dispositivo no posee movimientos."));
    		}else{    			
    			HashMap<String, MovimientosDispositivoVO> tablaVendedores = new HashMap<String, MovimientosDispositivoVO>();
    			long totalRecaudado = 0;
    			long totalPropinas = 0;
    			long totalSubtotal = 0;
    			long cantidadTrx = listaPagos.size();
    			
    			String nombreComercio = null;
    			String nombreConvenio = null;
    			String rutComercio = null;
    			boolean primerMov = true;
    			
    			for(TrxsPago pago:listaPagos){
    				
    				if(primerMov){
    					nombreComercio = pago.getConvenio().getComercio().getRazonSocialComercio();
    	    			nombreConvenio = pago.getConvenio().getNombreConveniio();
    	    			rutComercio = pago.getConvenio().getComercio().getRutComercio();
    	    			primerMov = false;
    				}
    				
    				totalRecaudado = totalRecaudado + pago.getMontoTrx();
    				totalPropinas = totalPropinas + pago.getPropinaTrx();
    				totalSubtotal = totalSubtotal + pago.getSubTotalTrx();
    				
    				if(tablaVendedores.containsKey(pago.getIdVendedor())){
    					MovimientosDispositivoVO movVendedor = tablaVendedores.get(pago.getIdVendedor());    					   					
    					MovimientoCierreVO movimiento = MapperVO.getMovimientoCierreVO(pago);
    					
    					movVendedor.getMovimiento().add(	movimiento);
    					movVendedor.setMontoAcumulado(		movVendedor.getMontoAcumulado() + pago.getMontoTrx());  
    					movVendedor.setPropinaAcumulada(	movVendedor.getPropinaAcumulada() + pago.getPropinaTrx());
    					movVendedor.setSubTotalAcumulado(   movVendedor.getSubTotalAcumulado() + pago.getSubTotalTrx());
    					movVendedor.setNumeroVentas(		movVendedor.getNumeroVentas()+1);    		   					
    				}else{
    					MovimientosDispositivoVO movVendedor = new MovimientosDispositivoVO();
    					ArrayList<MovimientoCierreVO> movimientos = new ArrayList<MovimientoCierreVO>();    					
    					MovimientoCierreVO movimiento = MapperVO.getMovimientoCierreVO(pago);
    					
    					movimientos.add(movimiento);
    					movVendedor.setIdVendedor(			pago.getIdVendedor());
    					movVendedor.setMontoAcumulado(		pago.getMontoTrx());
    					movVendedor.setPropinaAcumulada(	pago.getPropinaTrx());
    					movVendedor.setSubTotalAcumulado(   pago.getSubTotalTrx());
    					movVendedor.setMovimiento(			movimientos);
    					movVendedor.setNumeroVentas(		1);    					
    					
    					tablaVendedores.put(pago.getIdVendedor(), movVendedor);
    				}
    			}    	
    			
    			Iterator<MovimientosDispositivoVO> lista = tablaVendedores.values().iterator();
    			ArrayList<MovimientosDispositivoVO> movs = new ArrayList<MovimientosDispositivoVO>();
    			while(lista.hasNext()){
    				movs.add((MovimientosDispositivoVO)lista.next());
    			}
    			
    			MovimientosVendedorVO movsVendedor = new MovimientosVendedorVO();
    			movsVendedor.setVendedor(movs);
    			
    			respuesta.setFechaHoraSistema(	MotorPagosHelper.getFechaString(new Date()));
    			respuesta.setVendedores(		movsVendedor);
    			respuesta.setTotalDia(			totalRecaudado);
    			respuesta.setTotalPropinasDia(	totalPropinas);
    			respuesta.setTotalSubTotalDia(	totalSubtotal);
    			respuesta.setTotalVentasDia(	cantidadTrx);
    	    	respuesta.setRetorno(			MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_OK, MensajesError.OPERACION_EXITOSA));    
    	    	respuesta.setNombreComercio(	nombreComercio);
    	    	respuesta.setRutComercio(		rutComercio);
    	    	respuesta.setNombreConvenio(	nombreConvenio);
    		}    	
    	}catch(Exception e){
    		logger.error("Se produjo un error al obtener el total de ventas del dia.", e);
    		respuesta.setRetorno(MapperVO.getRespuestaVO(ConstantesMotorPagos.CODIGO_RETORNO_NOOK, MensajesError.ERROR_GENERICO));
    	}finally{
    		em.close();
    	}
    	return respuesta;
    }
}
