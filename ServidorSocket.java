
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ServidorSocket{ 
    
    static int suma(int x, int y){
            return x+y;
        }

    static int multiplicacion(int x, int y){
            return x*y;
        }            
            
    
    
    public static void main(String[] args){                                
        try {
            System.out.print("[ Iniciando Servidor TCP    .........................  ");
            ServerSocket ss = new ServerSocket(3300, 5, InetAddress.getByName("127.0.0.1"));
            System.out.println("[OK] ]");
            
            System.out.print("[ Esperando solicitudes de conexión ..................  ");
            Socket sock = ss.accept(); // Operación de bloqueo (esperando solicitud de conexión)
            System.out.println("[OK] ]");
            
            InputStream is = sock.getInputStream(); //Canal de entrada de datos
            OutputStream os = sock.getOutputStream(); //Canal de salida de datos
            byte[] buf = new byte[20]; // recibir búfer

            System.out.println("[ Esperando la recepción del mensaje  ..............  ");
            is.read(buf); // Operación de bloqueo (esperando la llegada de datos)
            System.out.println("[OK] ]");
            
            String msg = new String(buf); // Asignación de matriz(vector) de bytes recibidos a cadena            
            System.out.println("  Mensaje recibido: "+ msg);
            
            byte[] rec = new byte[50];
            System.out.println("[ Esperando la recepción de la operacion");
            is.read(rec);
                        
            String opcion=new String(rec);            
            System.out.println(opcion);            
            Pattern sum=Pattern.compile("suma\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\).+");
            Pattern mult=Pattern.compile("multiplicacion\\s*\\(\\s*\\d+\\s*\\,\\s*\\d+\\s*\\).+");            
            Matcher mSum=sum.matcher(opcion);
            Matcher mMult=mult.matcher(opcion);                        
            int x=0, y=0;  
          
            if(mSum.matches()){        
                Pattern p=Pattern.compile("\\d+");
                Matcher m=p.matcher(opcion);                
                
                if(m.find()){
                    x=Integer.parseInt(m.group());                    
                }
                if(m.find()){
                    y=Integer.parseInt(m.group());                    
                }
                System.out.println(x+"+"+y);
                String send="El resultado de la suma es: "+String.valueOf(suma(x, y));
                System.out.println(send);  
                os.write(send.getBytes());
            }
            else if(mMult.matches()){
                Pattern p=Pattern.compile("\\d+");
                Matcher m=p.matcher(opcion);                
                if(m.find()){
                    x=Integer.parseInt(m.group());                    
                }
                if(m.find()){
                    y=Integer.parseInt(m.group());
                }
                System.out.println(x+"+"+y);
                String send="El resultado de la multiplicacion es: "+String.valueOf(multiplicacion(x, y));
                System.out.println(send);  
                os.write(send.getBytes());
            }
            else{
                System.out.println("Sin coincidencias");
                os.write("Sin coincidencias".getBytes());
            }                                                    
            
        }catch(Exception e){System.out.println(e);}    
        System.out.println("[ Fin ]");                          
        
    }
}


