/**
 * @author Tarcisio da Rocha (Prof. DCOMP/UFS)
 */
package br.ufs.dcomp.ExemploTcpJava;

import java.net.*;
import java.io.*;
import java.util.*;

public class TCPServer{
    public static void main(String[] args){
        
        try {
            System.out.print("[ Iniciando Servidor TCP    .........................  ");
            /* 
                o primeiro paramentro é a porta que o servidor ta escutando
                o segundo paramentro é o tamanho da fila que o listen vai usar internamente
                o terceiro paramentro é o IP da maquina local
                
                Serversocket faz o SOCKET, O BIND E O LISTEN
            */
            ServerSocket ss = new ServerSocket(3300, 5, InetAddress.getByName("127.0.0.1")); 
            System.out.println("[OK] ]");
            
            System.out.print("[ Aquardando pedidos de conexão    ..................  ");
            /* 
                Aqui ele faz o pedido de conexao , utilizando o serversocket declarado logo acima
                Socket é so do javanet e é chamado assim no TCP no UDP é DatagramaSocket
            */
            Socket sock = ss.accept(); // Operação bloqueante (aguardando pedido de conexão)
            System.out.println("[OK] ]");
            
            /* InputStream e OutputStream é o fluxo de bytes */
            InputStream is = sock.getInputStream(); //Canal de entrada de dados
            OutputStream os = sock.getOutputStream(); //Canal de saída de dados
            byte[] buf = new byte[20]; // buffer de recepção

            System.out.print("[ Aguardando recebimento de mensagem   ..............  ");
            is.read(buf); // Operação bloqueante (aguardando chegada de dados)
            System.out.println("[OK] ]");
            
            String msg = new String(buf); // Mapeando vetor de bytes recebido para String
            
            System.out.println("  Mensagem recebida: "+ msg);
            
            // ------------- MINHA ALTERACAO -------------
            msg = "Ola, Client";
            buf = msg.getBytes();
            os.write(buf);
            
            
            // chat amador
            int i = 0;
            
            while (i < 5) {
                
                // recebendo a msg
                buf = new byte[20];
                is.read(buf);
                msg = new String(buf);
                System.out.println(" Mensagem recebida: "+ msg);
                
                // escrevendo e enviando a msg
                System.out.print(" Messagem enviada: ");
                Scanner se = new Scanner(System.in);
                msg = se.nextLine();
                buf = msg.getBytes();
                os.write(buf);
                
                i++;
            }
            
        }catch(Exception e){System.out.println(e);}    
        System.out.println("[ FIM ]");
    }
}