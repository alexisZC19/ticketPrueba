/** ******************************************
 * Autor:Zavaleta Cruz Jonathan Alexis
 * Fecha creacion:
 *
 *
 ******************************************* */
package conexion;

import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ImprimirTicket {

    void imprimirTicket(JTable jtbl_venta, String subTotal, String total, String dineroR, String devolucion) {
        try {

            PrinterMatrix printer = new PrinterMatrix();
            Extenso e = new Extenso();

            e.setNumber(20.30);
            //Definir el tamanho del papel para la impresion de dinamico y 32 columnas
            int filas = jtbl_venta.getRowCount();
            int tamaño = filas + 40;
            printer.setOutSize(tamaño, 80);

            //Imprimir = 1ra linea de la columa de 1 a 32
            printer.printTextWrap(0, 1, 5, 80, "===================================================================");
            printer.printTextWrap(1, 1, 30, 80, "**MINI   CAFETERIA**"); //Nombre establecimiento
            printer.printTextWrap(3, 1, 15, 80, "MODULO D ZONA MERCANTIL  LOC  COL SN FRANCISCO 567"); //Barrio
            printer.printTextWrap(4, 1, 23, 80, "MIAHUATLÁN DE P.D., OAZ CP:70805"); //Direccion
            printer.printTextWrap(5, 1, 24, 80, "RFC: "); //Codigo RFC
            printer.printTextWrap(5, 1, 42, 80, "TEL: "); //Codigo RFC

            printer.printTextWrap(6, 1, 24, 41, "FECHA: "); //Aqui va la fecha de recibo
            printer.printTextWrap(6, 1, 42, 80, "HORA: "); //Aqui va la hora de recibo

            printer.printTextWrap(7, 1, 5, 80, "==================================================================="); //Numero del recibo - FACTURA O PEDIDO
            printer.printTextWrap(8, 1, 6, 80, "ATIENDE: "); //Nombre Cajero
            printer.printTextWrap(9, 1, 6, 80, "CLIENTE: ");//Nombre del Cliente
            printer.printTextWrap(9, 1, 38, 80, "PEDIDO: ");//Nombre del Cliente
            printer.printTextWrap(10, 1, 5, 80, "===================================================================");
            printer.printTextWrap(11, 1, 7, 80, "COD   DESCRIPCION                        CANT           PRECIO");
            printer.printTextWrap(12, 1, 0, 80, " ");

            for (int i = 0; i < filas; i++) {
                int p = 13 + i; //Fila

                printer.printTextWrap(p, 1, 7, 19, jtbl_venta.getValueAt(i, 0).toString());
                printer.printTextWrap(p, 1, 12, 42, jtbl_venta.getValueAt(i, 1).toString());
                printer.printTextWrap(p, 1, 47, 49, jtbl_venta.getValueAt(i, 2).toString());

                String pre = printer.alinharADireita(10, jtbl_venta.getValueAt(i, 3).toString());
                printer.printTextWrap(p, 1, 57, 80, pre);

                //String inp= printer.alinharADireita(7,punto_Venta.jtbl_venta.getValueAt(i,6).toString());
                //printer.printTextWrap(p , 1, 25, 32, inp);
            }
            DecimalFormat formateador = new DecimalFormat("#.###");
            printer.printTextWrap(filas + 17, 1, 5, 80, "===================================================================");
            String sub = printer.alinharADireita(10, subTotal);
            printer.printTextWrap(filas + 18, 1, 45, 80, "Subtotal: ");
            printer.printTextWrap(filas + 18, 1, 60, 80, "$" + sub);

            String tot = printer.alinharADireita(10, total);
            printer.printTextWrap(filas + 19, 1, 45, 80, "Total a pagar: ");
            printer.printTextWrap(filas + 19, 1, 60, 80, "$" + tot);

            String efe = printer.alinharADireita(10, dineroR);
            printer.printTextWrap(filas + 20, 1, 45, 80, "Efectivo : ");
            printer.printTextWrap(filas + 20, 1, 60, 80, "$" + efe);

            String cam = printer.alinharADireita(10, devolucion);
            printer.printTextWrap(filas + 21, 1, 45, 80, "Cambio : ");
            printer.printTextWrap(filas + 21, 1, 60, 80, "$" + cam);

            printer.printTextWrap(filas + 22, 1, 5, 80, "===================================================================");
            printer.printTextWrap(filas + 23, 1, 26, 80, "!Gracias por su preferencia!");
            printer.printTextWrap(filas + 24, 1, 30, 80, "Empresa Unsis");
            printer.printTextWrap(filas + 25, 1, 31, 80, "Contacto: null");
            printer.printTextWrap(filas + 26, 1, 25, 80, "Software a Medida Restaurante");
            printer.printTextWrap(filas + 27, 1, 5, 80, "===================================================================");

            ///CREAR ARCHIVO EN CARPETA DEL PROYECTO PARA PEDIDOS
            printer.toFile("C:\\tmp\\impresion.txt");
            FileInputStream inputStream = null;

//            try {
//                inputStream = new FileInputStream("C:\\tmp\\impresion.txt");
//            } catch (FileNotFoundException ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(null, "Error al guardar");
//            }
//            if (inputStream == null) {
//                return;
//            }
//
//            DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
//            Doc document = new SimpleDoc(inputStream, docFormat, null);
//            PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
//            PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
//
//            if (defaultPrintService != null) {
//                DocPrintJob printJob = defaultPrintService.createPrintJob();
//                try {
//                    printJob.print(document, attributeSet);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            } else {
//                System.err.println("No existen impresoras instaladas");
//            }
//
//            inputStream.close();
//            //imprimirFin(subTotal, total, dineroR, devolucion); //ESTE METODO NO SE UTILIZARA
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al imprimir " + e);
        }

    }

    public void exportPDF() throws DocumentException {
        String rutaArchivoTxt = "C:\\tmp\\impresion.txt";  // Ruta del archivo de texto
        String rutaArchivoPdf = "C:\\tmp\\impresion.pdf";  // Ruta donde se guardará el archivo PDF

         try {
            // Crear un nuevo archivo PDF
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(rutaArchivoPdf));
            document.open();

            // Abrir el archivo de texto para leer
            try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivoTxt))) {
                // Crear el contenido del PDF leyendo líneas del archivo de texto
                String line;
                while ((line = br.readLine()) != null) {
                    // Crear un párrafo con formato y agregar al documento
                    Paragraph paragraph = new Paragraph(line);
                    paragraph.setAlignment(com.itextpdf.text.Element.ALIGN_JUSTIFIED);
                    paragraph.setSpacingBefore(10f);
                    paragraph.setSpacingAfter(10f);
                    document.add(paragraph);
                }
            }


            // Cerrar recursos
            document.close();

            System.out.println("El archivo PDF ha sido creado con éxito.");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    public String imprimirCaracter(int num, char car){
        String lin = null;
        for (int i = 0; i <= num; i++) {
            
            
        }
    
    
        
    
    return  lin;
    }

}
