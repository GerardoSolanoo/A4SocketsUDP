/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.a4socketsudp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gerar
 */
public class A4SocketsUDP {

    private static final String _IP = "25.72.27.24";
    private static final int _PUERTO = 1234;

    public static void main(String[] args) throws UnknownHostException {

        System.out.println("Inicio");

        InetAddress ip = InetAddress.getByName(_IP);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try {

            System.out.println("IP de LocalHost = " + InetAddress.getLocalHost().toString());

            System.out.println("\nEscuchando en: ");
            System.out.println("IP Host = " + ip.getHostAddress());
            System.err.println("Puerto = " + _PUERTO + "\n");

        } catch (UnknownHostException ex) {
            System.err.println("No puede saber la direccion IP local: " + ex);
        }

        DatagramSocket dgmSocket = null;
        try {
            dgmSocket = new DatagramSocket(_PUERTO, ip);
            System.out.println("\nIP de LocalAddress = " + dgmSocket.getLocalAddress().toString());
        } catch (SocketException se) {
            System.err.println("Se ha producido un error al abrir el socket: " + se);
            System.exit(-1);

        }
        boolean av = false;
        while (av = false) {
            try {

                byte bufferEntrada[] = new byte[4];

                DatagramPacket dgmPaquete = new DatagramPacket(bufferEntrada, 4);

                dgmSocket.receive(dgmPaquete);

                int puertoRemitente = dgmPaquete.getPort();

                InetAddress ipRemitente = dgmPaquete.getAddress();

                ByteArrayInputStream arrayEntrada = new ByteArrayInputStream(bufferEntrada);

                DataInputStream datosEntrada = new DataInputStream(arrayEntrada);

                int entrada = datosEntrada.readInt();

                long salida = (long) entrada * (long) entrada;

                ByteArrayOutputStream arraySalida = new ByteArrayOutputStream();

                DataOutputStream datosSalida = new DataOutputStream(arraySalida);

                datosSalida.writeLong(salida);

                datosSalida.close();

                dgmPaquete = new DatagramPacket(arraySalida.toByteArray(), 8, ipRemitente, puertoRemitente);

                dgmSocket.send(dgmPaquete);

                System.out.println(
                        formatter.format(new Date())
                        + "\tCliente = " + ipRemitente + ":"
                        + puertoRemitente + "\tEntrada = "
                        + entrada + "\tSalida = " + salida
                );
                av = false;
            } catch (IOException ex) {
                av = true;
                Logger.getLogger(A4SocketsUDP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
