
package conexion;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EjemploTicket {

    public static void main(String[] args) {
        try {
//            FileWriter fw = new FileWriter("COM4:");
            FileWriter fw = new FileWriter("/dev/lp0");

            PrintWriter pw = new PrintWriter(fw);
            String s = "27,112,025250";

            int i, len = s.length();

            for (i = 0; len > 80; i += 80) {
                pw.print(s.substring(i, i + 80));
                pw.print("\r\n");
                len -= 80;
            }

            if (len > 0) {
                pw.print(s.substring(i));
                pw.print("\r\n");
            }

            pw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
