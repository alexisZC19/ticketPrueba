package conexion;

import static conexion.FormatoBuffer.generarContenidoTicketVenta;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

public class FormatoBuffer1 {

    public static void main(String[] args) {
        // Información de la venta
        String contenidoTicket = generarContenidoTicketVenta("Producto A", 2, 10.50, "Producto B", 1, 20.00);

        // Llamada al método para imprimir el ticket
        imprimirTicket(contenidoTicket);
    }

    public static void imprimirTicket(String contenidoTicket) {
        // Convertir el contenido del ticket a un flujo de entrada
        try (InputStream inputStream = new ByteArrayInputStream(contenidoTicket.getBytes())) {
            // Obtener la impresora por su nombre (ajusta el nombre de la impresora según tu configuración)
            PrintService printService = buscarImpresoraPorNombre("nombre_de_tu_impresora");

            if (printService != null) {
                // Configurar atributos de impresión
                DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                DocPrintJob printJob = printService.createPrintJob();

                // Imprimir el documento
                Doc doc = new SimpleDoc(inputStream, flavor, null);
                printJob.print(doc, null);

                System.out.println("Ticket de venta impreso con éxito.");
            } else {
                System.out.println("No se encontró la impresora.");
            }

        } catch (Exception e) {
            System.out.println("Error al imprimir el ticket de venta: " + e.getMessage());
        }
    }

    public static PrintService buscarImpresoraPorNombre(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

        for (PrintService printService : printServices) {
            if (printService.getName().equals(printerName)) {
                return printService;
            }
        }

        return null;
    }

    public static String generarContenidoTicketVenta(String producto1, int cantidad1, double precio1,
            String producto2, int cantidad2, double precio2) {
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

}
