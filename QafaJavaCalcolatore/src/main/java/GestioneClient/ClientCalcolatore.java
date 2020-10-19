package GestioneClient; //package di riferimento
public class ClientCalcolatore 
{
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Client client = new Client(); //instanzio 
        client.connetti(); //chiamo il metodo connetti
        client.comunica(); //chiamo il metodo comunica
    } 
    
}