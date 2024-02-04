package conexion;

import java.sql.Connection;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.HashMap;
import java.util.Map;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;

/**
 *
 * @author labtecweb06
 */
public class Prueba2 {

    public static void imprimirComprobante(int nro) {

        try {

            Connection conn = new Conectar().conectaBD();

            String dir = "ource/conexion/Prueba.jasper";

            JasperReport reporteJasper = JasperCompileManager.compileReport(dir);

            Map parametro = new HashMap();

            parametro.put("@nro", nro);

            JasperPrint mostrarReporte = JasperFillManager.fillReport(dir, parametro, conn);

            // ESTABLECE DATOS DE IMPRESORAS
            PrinterJob job = PrinterJob.getPrinterJob();

            PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);

            int selectedService = 0;
            for (PrintService service : services) {
                System.out.println(service.getName());
            }
            for (int i = 0; i < services.length; i++) {

                if (services[i].getName().toUpperCase().contains("EPSON")) {

                    selectedService = i;

                }

            }

            job.setPrintService(services[selectedService]);

            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();

            MediaSizeName mediaSizeName = MediaSize.findMedia(4, 4, MediaPrintableArea.INCH);

            printRequestAttributeSet.add(mediaSizeName);

            printRequestAttributeSet.add(new Copies(1));

            JRPrintServiceExporter exporter;

            exporter = new JRPrintServiceExporter();

            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, services[selectedService]);

            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, services[selectedService].getAttributes());

            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);

            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);

            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, mostrarReporte);

            exporter.exportReport();

        } catch (JRException ex) {

            JOptionPane.showMessageDialog(null, "Error de JREEXEPCION: " + ex);

        } catch (PrinterException ex) {

            JOptionPane.showMessageDialog(null, "ERROR PRINTEREXCEPCION " + ex);

        }

    }

}
