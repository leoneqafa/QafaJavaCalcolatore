package GestioneClient; //package di riferimento
import java.io.*; //importo tutto utilizzando il * 
import java.net.*; //importo tutto utilizzando il * 
public class Client 
{
    String nome_server="127.0.0.1"; //ip del server di riferimento
    int porta_server=8888; //porta di rifderimento
    Socket socket;
    BufferedReader input_tastiera;
    String messaggio;
    String risposta;
    DataOutputStream dati_al_server;
    BufferedReader dati_dal_server;
    public Socket connetti() //metodo per gestire la connessione al server
    {
        System.out.println("Client in esecuzione."); //avviso che il client è in esecuzione
        try 
        {
            input_tastiera=new BufferedReader(new InputStreamReader(System.in));
            socket=new Socket(nome_server,porta_server); //creo il socket e quindi il collegamento con il server
            dati_al_server=new DataOutputStream(socket.getOutputStream());
            dati_dal_server=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(UnknownHostException e) //gestisco eventuale eccezione
        {
            System.err.println("Host non riconosciuto.");
        }
        catch (Exception e) //gestisco eventuale eccezione
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione.");
            System.exit(1);
        }
        return(socket);
    }
    public void comunica() //metodo che si occupa dell'input da mandare al server
    {
        try 
        {
            System.out.println("Inserire numero"); //avviso l'informazione che mi serve per il calcolo
            messaggio=input_tastiera.readLine(); //prendo in imput il numero
            System.out.println("Invio del messaggio al server."); //avviso dell'informazione che mi serve per il calcolo
            dati_al_server.writeBytes(messaggio+'\n');
            risposta=dati_dal_server.readLine();
            
            System.out.println("Inserire operazione"); //avviso l'informazione che mi serve per il calcolo
            messaggio=input_tastiera.readLine(); //prendo in imput il simbolo per il calcolo
            System.out.println("Invio del messaggio al server."); //avviso dell'informazione che mi serve per il calcolo
            dati_al_server.writeBytes(messaggio+'\n'); 
            risposta=dati_dal_server.readLine();
            
            System.out.println("Inserire altro numero"); //avviso l'informazione che mi serve per il calcolo
            messaggio=input_tastiera.readLine(); //prendo in imput il numero
            System.out.println("Invio del messaggio al server."); //avviso dell'informazione che mi serve per il calcolo
            dati_al_server.writeBytes(messaggio+'\n');
            risposta=dati_dal_server.readLine();
            
            risposta=dati_dal_server.readLine();
            
            System.out.println("Risposta del server: "+risposta);
            System.out.println("Chiusura dell'esecuzione."); //avviso di della chiusura del server
            socket.close(); //chiusura effettiva del socket
        }
        catch (Exception e) //gestisco eventuali eccezioni
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server.");
            System.exit(1);
        }
    }
}