
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class ClienteSocket{
    public static void main(String[] args){
        try {
            System.out.print("[ Conectando con Servidor TCP    ..................  ");
            Socket sock = new Socket("127.0.0.1", 3300);
            System.out.println("[OK] ]");
            
            InputStream is = sock.getInputStream(); // Canal de entrada de dados
            OutputStream os = sock.getOutputStream(); // Canal de salida de dados
            String msg = "Hola con Sockets!";
            byte[] buf = msg.getBytes(); // obteniendo la representación del byte del mensaje
            byte[] sen=new byte[50];
            byte[] res=new byte[50];

            System.out.print("[ Enviando mensaje    ..............................  ");
            os.write(buf);
            System.out.println("[OK] ]");           
            
            
            String operacion="";
            Scanner sc = new Scanner(System.in);
            String opcion;
            
            System.out.println("Para ejecutar la suma siga la estrcutura: suma(x,y)");
            System.out.println("Para ejecutar la multiplicacion siga la estrcutura: multiplicacion(x,y)");
            System.out.println("Ingrese la operacion");
            opcion = sc.nextLine();            
            sen=opcion.getBytes();
            os.write(sen);
            System.out.println("Eperando respuesta");
            is.read(res);
            System.out.println();
            String respuesta=new String(res);
            System.out.println(respuesta);
            
            
        }catch(Exception e){System.out.println(e);}    
        
        System.out.println("[ Fin ]");
    }
}