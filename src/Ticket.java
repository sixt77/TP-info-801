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
        int totalPrice;
        System.out.println("Recapitulatif de commande :");
        System.out.println("info fabriquant :");
        System.out.println("identifiant :"+logistics.value1);
        System.out.println("info fournisseur :");
        System.out.println("identifiant :"+supplier.value1);
        System.out.println("info transporteur :");
        System.out.println("identifiant :"+transport.value1);
        System.out.println("info commande :");
        System.out.println("nombre d'objet produit :"+logistics.value3);
        totalPrice = Integer.parseInt(logistics.value2)+Integer.parseInt(supplier.value2)+Integer.parseInt(transport.value2);
        System.out.println("pour un prix total :"+totalPrice);
    }

}
