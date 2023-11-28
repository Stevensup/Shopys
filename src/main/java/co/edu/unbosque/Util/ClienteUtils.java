package co.edu.unbosque.Util;

import co.edu.unbosque.Model.Cliente;

public class ClienteUtils {

    /**
     * @param cliente
     * @return String
     */
    public static String formatNombreCompleto(Cliente cliente) {
        return cliente.getNombre() + " " + cliente.getApellido();
    }

}
