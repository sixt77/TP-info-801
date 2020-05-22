public class Ticket {
    public Tuple logistics;
    public Tuple supplier;
    public Tuple transport;
    public Ticket(){
        this.logistics = null;
        this.supplier = null;
        this.transport = null;
    }
    public void print(){
        System.out.println("commande effectuer par "+logistics.value);
        System.out.println("commande fourni par "+supplier.value);
        System.out.println("commande transporter par "+transport.value);
    }

}
