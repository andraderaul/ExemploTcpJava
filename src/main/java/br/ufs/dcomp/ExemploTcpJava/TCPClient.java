/**
 * @author Tarcisio da Rocha (Prof. DCOMP/UFS)
 */
package br.ufs.dcomp.ExemploTcpJava;

import java.net.*;
import java.io.*;
import java.util.*;

public class TCPClient{
    public static void main(String[] args){
        try {
            System.out.print("[ Conectando com o Servidor TCP    ..................  ");
            /* 
                Primeiro paramentro é o ip da maquina local
                Segundo paramentro é a porta do servidor
                e tem outro construtor alem desse utilizado
                
                Cria o socket e a conexao
            */
            Socket sock = new Socket("127.0.0.1", 3300);
            System.out.println("[OK] ]");
            
            InputStream is = sock.getInputStream(); // Canal de entrada de dados
            OutputStream os = sock.getOutputStream(); // Canal de saída de dados
            String msg = "Olá, Servidor!!!";
            byte[] buf = msg.getBytes(); // Obtendo a respresntação em bytes da mensagem

            System.out.print("[ Enviando mensagem    ..............................  ");
            os.write(buf);
            System.out.println("[OK] ]");
            
            // ------------- MINHA ALTERACAO -------------
            buf = new byte[20];
            is.read(buf);
            msg = new String(buf);
            System.out.println(" Mensagem recebida: "+ msg);
            
            // chat amador
            int i = 0;
            
            while(i < 5 ) {
                
                // escrevendo e enviando a msg
                System.out.print(" Messagem enviada: ");
                Scanner se = new Scanner(System.in);
                msg = se.nextLine();
                buf = msg.getBytes();
                os.write(buf);
                
                // recebendo a msg
                buf = new byte[20];
                is.read(buf);
                msg = new String(buf);
                System.out.println(" Mensagem recebida: "+ msg);
                
                i++;
            }
            
            
        }catch(Exception e){System.out.println(e);}    
        System.out.println("[ FIM ]");
    }
}