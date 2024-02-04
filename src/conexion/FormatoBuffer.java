package conexion;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FormatoBuffer {

    public static void main(String[] args) {
        // Información de la venta
        String contenidoTicket = generarContenidoTicketVenta("Producto A", 2, 10.50, "Producto B", 1, 20.00);

        // Llamada al método para imprimir el ticket
        imprimirTicket(contenidoTicket);
    }

    public static String generarContenidoTicketVenta(String producto1, int cantidad1, double precio1,
            String producto2, int cantidad2, double precio2 ) {
        // Generar contenido del ticket de venta
        StringBuilder contenido = new StringBuilder();
        contenido.append("============= MAGICOFFE ==============\n");
        contenido.append("MODULO D ZONA MERCANTIL LOC BAR. ARRIBA\n");
        contenido.append("  MIAHUATLAN DE P.D. OAXACA CP: 70805\n");

        contenido.append("ATENDIO: ").append(producto2).append("\t").append("FOLIO: ").append(producto2).append("\n");
        contenido.append("CLIENTE: ").append(producto2).append("\t").append("ESTADO/ORDEN").append(producto2).append("\n");
        contenido.append("=======================================\n");
        contenido.append("RFC: ").append(producto2).append("\t").append("TEL: ").append(producto2).append("\n");
        contenido.append("FECHA: ").append(producto2).append("\t").append("HORA: ").append(producto2).append("\n");

        contenido.append("=======================================\n");

        contenido.append("Producto\tCantidad\tPrecio\n");
        contenido.append("------------------------------\n");
        
        contenido.append("------------------------------\n");
        contenido.append("Total:\t\t\t\t$").append((precio1 * cantidad1) + (precio2 * cantidad2)).append("\n");
        contenido.append("Recibo:\t\t\t\t$").append((100)).append("\n");
        contenido.append("Cambio:\t\t\t\t$").append((100 - 50) + (precio2 * cantidad2)).append("\n");
        contenido.append("=======================================\n");
        contenido.append("    Gracias por su preferencia\n");
        contenido.append("\t Empresa Unsis\n");
        contenido.append("\tContacto= Null\n");
        contenido.append("=======================================\n");

        return contenido.toString();
    }

    public static void imprimirTicket(String contenidoTicket) {
        try {
            // Cambia la ruta del archivo según tu entorno
            FileWriter fw = new FileWriter("/dev/lp0");
            PrintWriter pw = new PrintWriter(fw);

            int i, len = contenidoTicket.length();

            // Imprimir el contenido del ticket en líneas de 80 caracteres
            for (i = 0; len > 80; i += 80) {
                pw.print(contenidoTicket.substring(i, i + 80));
                pw.print("\r\n");
                len -= 80;
            }

            // Imprimir la última línea (si es necesario)
            if (len > 0) {
                pw.print(contenidoTicket.substring(i));
                pw.print("\r\n");
            }

            // Cerrar el PrintWriter
            pw.close();
            System.out.println("Ticket de venta impreso con éxito.");

        } catch (IOException e) {
            System.out.println(" Error al imprimir el ticket de venta: " + e.getMessage());
        }
    }

}
