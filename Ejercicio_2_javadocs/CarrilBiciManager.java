
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class CarrilBiciManager {

	/**
	 * @version 2.4.0
	 * @author CadizTech
	 * @see https://institucional.cadiz.es/area/Plan-de-Movilidad-Urbana-Sostenible/2021
	 */
	
    private final Map<String, Double> tramos; // nombre del tramo -> longitud en km
    private final Map<String, String> estadoTramos; // nombre del tramo -> estado

    /**
     * Crea el objeto inicializando sus atrivutos como hashMap
     */
    public CarrilBiciManager() {
        this.tramos = new HashMap<>();
        this.estadoTramos = new HashMap<>();
    }

    /**
     * Añade un nuevo tramo del carril con su nombre y longitud e inicializa su estado como en servicio
     * @param nombre El nombre del tramo añadido
     * @param longitud La longitud que tiene el tramo añadido
     * @throws Si el nombre del tramo es nulo o esta vacio se lanza una excepcion diciendo que el nombre del tramo esta vacio
     * @throws Si la longitud es menor o igual a 0 se lanza una excepcion diciendo que la longitud debe ser mayor que cero
     */
    public void añadirTramo(String nombre, double longitud) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del tramo no puede estar vacío");
        }
        if (longitud <= 0) {
            throw new IllegalArgumentException("La longitud debe ser mayor que cero");
        }
        tramos.put(nombre, longitud);
        estadoTramos.put(nombre, "En servicio");
    }

    /**
     * Modifica el estado del tramo pedido
     * @param nombre El nombre del tramo que se quiere modificar el estado
     * @param nuevoEstado El nuevo estado del tramo
     * @throws Si el tramo deseado no se encuentra se lanza una excepcion diciendo que el tramo indicado no existe
     */
    public void actualizarEstado(String nombre, String nuevoEstado) {
        if (!tramos.containsKey(nombre)) {
            throw new NoSuchElementException("El tramo indicado no existe: " + nombre);
        }
        estadoTramos.put(nombre, nuevoEstado);
    }
    
    /**
     * Cambia el estado del tramo llamando al metodo actualizar estado
     * @param nombre El nombre del tramo que se quiere modificar el estado
     * @param estado El nuevo estado del tramo
     * @deprecated El metodo no funciona correctamente y se recomienda usar mejor el metodo actualizar estado
     */
    public void cambiarEstado(String nombre, String estado) {
        actualizarEstado(nombre, estado);
    }

    /**
     * Consusta el estado del tramo pedido
     * @param nombre El nombre del tramo al que se quiere consultar su estado
     * @return Devuelve el estado del tramo
     * @throws Si no se encuentra el tramo se lanza una excepcion diciendo el tramo indicado no existe
     */
    public String consultarEstado(String nombre) {
        if (!estadoTramos.containsKey(nombre)) {
            throw new NoSuchElementException("El tramo indicado no existe");
        }
        return estadoTramos.get(nombre);
    }

    /**
     * Obtiene la longitud total de todos los tramos
     * @return Devuelve la longitud total
     */
    public double longitudTotal() {
        return tramos.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    /**
     * Obtiene todos los tramos guardados
     * @return Devuelve un mapa de los tramos
     */
    public Map<String, Double> obtenerTramos() {
        return Collections.unmodifiableMap(tramos);
    }

    /**
     * Genera un informe de los carriles añadiendo todos los tramos, sus estados y la longitud total
     * @return Devuelve el informe
     */
    public String generarInforme() {
        StringBuilder sb = new StringBuilder("INFORME DE CARRILES BICI - Bahía de Cádiz\n");
        sb.append("===========================================\n");
        for (String nombre : tramos.keySet()) {
            sb.append("- ").append(nombre).append(" (")
              .append(tramos.get(nombre)).append(" km): ")
              .append(estadoTramos.get(nombre)).append("\n");
        }
        sb.append("Longitud total: ").append(longitudTotal()).append(" km\n");
        return sb.toString();
    }
}
