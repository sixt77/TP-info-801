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
        System.out.println("commande effectuer par "+logistics.value1);
        System.out.println("commande fourni par "+supplier.value1);
        System.out.println("commande transporter par "+transport.value1);
    }

}
