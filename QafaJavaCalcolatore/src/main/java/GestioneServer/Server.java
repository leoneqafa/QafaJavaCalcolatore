package GestioneServer; //package di riferimento
import java.io.*; //importo tutto utilizzando il * 
import java.net.*; //importo tutto utilizzando il * 
import java.util.*; //importo tutto utilizzando il * 

public class Server {
    ServerSocket socket_server=null;
    Socket socket_client=null;
    int messaggio_client=0; //istanzio variabile che conterrà uno dei due numeri
    String messaggio_client1=""; //Utilizzo il tipo String così posso contenere il tipo di operazione
    int messaggio_client2=0; //istanzio seconda variabile che conterrà uno dei due numeri
    String risposta_server=null;
    BufferedReader dati_dal_client;
    DataOutputStream dati_al_client;
    public Socket attendi(){
        try {
            System.out.println("Server in esecuzione."); //avviso dell'esecuzione
            socket_server=new ServerSocket(8888); //assegno la porta 8888 al ServerSocket
            System.out.println("Server in attesa del client."); //avviso dell'attesa del client
            socket_client=socket_server.accept(); //accetto la richiesta del collegamento del Client
            System.out.println("Client connesso."); //avviso del collegamento avvenuto 
            dati_dal_client=new BufferedReader(new InputStreamReader(socket_client.getInputStream())); 
            dati_al_client=new DataOutputStream(socket_client.getOutputStream());
        }
        catch (Exception e) { //Gestisco eventuali eccezioni 
            System.out.println(e.getMessage());
            System.out.println("Errore nell'istanziamento del server.");
            System.exit(1);
        }
        return(socket_client);
    }
    public void comunica(){ //metodo che si occupa di ricevere i dati dal client | ho utilizzato tre variabili al posto di un array per motivi di comodità
        try {
            System.out.println("In attesa del messaggio da parte del client."); //avviso di star aspettando il messaggio dal client 
            messaggio_client=Integer.valueOf(dati_dal_client.readLine()); //mi occupo di salvare il messaggio arrivato dal client dentro messaggio_client
            System.out.println("Messaggio ricevuto."); //avviso di aver ricevuto il messaggio
            dati_al_client.writeBytes("OK"+'\n');
            
            System.out.println("In attesa del messaggio da parte del client."); //avviso di star aspettando il messaggio dal client 
            messaggio_client1=dati_dal_client.readLine(); //mi occupo di salvare il messaggio arrivato dal client dentro messaggio_client1
            System.out.println("Messaggio ricevuto."); //avviso di aver ricevuto il messaggio
            dati_al_client.writeBytes("OK"+'\n');
            
            System.out.println("In attesa del messaggio da parte del client."); //avviso di star aspettando il messaggio dal client 
            messaggio_client2=Integer.valueOf(dati_dal_client.readLine()); //mi occupo di salvare il messaggio arrivato dal client dentro messaggio_client2
            System.out.println("Messaggio ricevuto."); //avviso di aver ricevuto il messaggio
            dati_al_client.writeBytes("OK"+'\n');
            
            switch(messaggio_client1) //con questo case gestisto la variabile messaggio_client1 così da sapere quale calcolo dover eseguere per poi restituirlo al client
            {
                case "+": 
                dati_al_client.writeBytes(String.valueOf(messaggio_client+messaggio_client2)+'\n'); //faccio la somma e restituisco al client il risultato
                break;
                case "-":
                dati_al_client.writeBytes(String.valueOf(messaggio_client-messaggio_client2)+'\n'); //faccio la sottrazione e restituisco al client il risultato
                break;
                case "*":
                dati_al_client.writeBytes(String.valueOf(messaggio_client*messaggio_client2)+'\n'); //faccio la moltiplicazione e restituisco al client il risultato
                break;
                case "/":
                dati_al_client.writeBytes(String.valueOf(messaggio_client/messaggio_client2)+'\n'); //faccio la divisione e restituisco al client il risultato
                break;
                default: //gestisco eventuali valori non validi
                System.out.println("valore inserito non riconosciuto");
                break;
            }

            System.out.println("Elaborazione completata.");
            socket_client.close(); //chiudo il socket dato che l'operazione si è conclusa
        }
        catch (Exception e) {
            System.out.println("Errore durante la comunicazione."); //gestisco una eventuale eccezione
        }
    }
}