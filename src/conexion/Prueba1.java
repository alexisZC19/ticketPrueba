
package conexion;

import java.awt.print.Printable;
import java.awt.print.PrinterJob;


public class Prueba1 {
    
    public static void main(String[] args) {
        // Llamada al método equivalente al Button2_Click en Java
        button2Click();
    }

    private static void button2Click() {
        String s = "Hello, this is a test";

        // Abre el cuadro de diálogo para seleccionar la impresora
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        if (printerJob.printDialog()) {
            // Si el usuario selecciona una impresora, imprime el texto
            printString(printerJob, s);
        }
    }

    private static void printString(PrinterJob printerJob, String text) {
        printerJob.setPrintable((graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            // Aquí puedes personalizar cómo se imprime el texto en la página
            graphics.drawString(text, 100, 100);

            return Printable.PAGE_EXISTS;
        });

        try {
            printerJob.print();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
