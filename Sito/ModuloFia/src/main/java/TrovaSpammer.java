package src.main.java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class TrovaSpammer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(2020);
        while(true) {
            System.out.println("Attendo un client");
            Socket socket = serverSocket.accept();
            System.out.println("Client accettato");
            Scanner objectInputStream = new Scanner(socket.getInputStream());
            String jsonCommenti = objectInputStream.nextLine();
            Gson gson = new Gson();
            ArrayList<CommentoSemplice> commenti = gson.fromJson(jsonCommenti, new TypeToken<ArrayList<CommentoSemplice>>() {
            }.getType());
            System.out.println("Commenti ricevuti: "+commenti);
            ArrayList<String> utentiSpammer = controllaSpammer(commenti);
            String jsonSpammer = gson.toJson(utentiSpammer);
            PrintWriter objectOutputStream = new PrintWriter(socket.getOutputStream(),true);
            objectOutputStream.println(jsonSpammer);
            System.out.println("Utenti inviati: "+jsonSpammer);
        }
    }
    public static ArrayList<String> controllaSpammer(ArrayList<CommentoSemplice> commenti){
        TreeSet<String> utenti = new TreeSet<>();
        for(CommentoSemplice commento:commenti)
            utenti.add(commento.getUsername());
        ArrayList<String> spammers = new ArrayList<>();
        Random rand = new Random();
        for(String utente:utenti){
            if(rand.nextInt(50)>40)
                spammers.add(utente);
        }
        return spammers;
    }
}
