package GestioneServer; //package di riferimento

public class ServerCalcolatore 
{

    public static void main(String[] args) 
    {

        Server Server = new Server(); //istanzio Server
        Server.attendi(); //chiamo il metodo attendi
        Server.comunica(); //chiamo il metodo comunica
    }
    
}
