import java.util.ArrayList;
import java.util.Scanner;

public class Logistic extends Thread {
    String name;
    TupleSpace ts;
    TupleSpace tt;
    TupleSpace cs;
    ArrayList<String> treated;
    public Logistic(String name, TupleSpace ts, TupleSpace tt, TupleSpace cs){
        this.name = name;
        this.ts = ts;
        this.tt = tt;
        this.cs = cs;
        ts.addProc();
        this.treated = new ArrayList<String>();
    }
    public void run() {
        String TupleID;
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        Scanner sc4 = new Scanner(System.in);
        Scanner sc5 = new Scanner(System.in);
        int response;
        int id = 0;
        String responseStr ="";
        String responseStr2 ="";
        String responseStr3 ="";
        Tuple sav = null;
        Boolean waiting = false;
        while(1 == 1){
            if(ts.forceCapture(this.name)){
                treated.size();
                if(ts.contains("CreationOffre") && !treated.contains(ts.getTupleValue("CreationOffre"))){
                    TupleID = ts.getTupleValue("CreationOffre");
                    System.out.println("veuillez préciser le budget approximatif pour votre projet");
                    responseStr = sc1.nextLine();
                    System.out.println("veuillez préciser la quantité souhaité dans laquelle sera produite votre projet");
                    responseStr2 = sc2.nextLine();
                    System.out.println("veuillez préciser le nombre de jours maximum pour le developpement de votre projet");
                    responseStr3 = sc3.nextLine();
                    sav = new Tuple("AppelOffre", Integer.toString(id), responseStr, responseStr2, responseStr3);
                    ts.add(sav);
                    ts.release();
                    //System.out.println("Appel d'offre reçu, id :"+TupleID);
                    treated.add(TupleID);
                    waiting = true;
                    id++;

                }

                if(waiting){
                    Ticket commande = new Ticket();
                    System.out.println("en attente de réponses, appuyer sur S pour stopper la recherche");
                    while(!sc4.nextLine().equals("S")){

                    }
                    ts.printTSasPriceList();
                    System.out.println("selectionnez le numéro de la proposition choisit (O) pour relancer l'offre");
                    response = sc5.nextInt();
                    while(response < 0 || response > ts.list.size()-1){
                        System.out.println("erreur selection impossible");
                        System.out.println("selectionnez le numéro de la proposition choisit");
                        response = sc3.nextInt();
                    }

                    if(response == 0){
                        ts.list.clear();
                        ts.add(new Tuple(sav.name, Integer.toString(id), sav.value2, sav.value3, sav.value4));
                        System.out.println("relance appel d'offre");
                        id++;

                    }else{
                        System.out.println("validation du choix : "+response);
                        commande.logistics = ts.list.get(response+1);
                        ts.list.clear();

                        waiting = false;
                        //gestion fournisseur
                        System.out.println("choix du fournisseur");

                        Tuple supplierTuple = tt.sendMessage(new Tuple("fournisseurOffre", Integer.toString(id), sav.value2));
                        System.out.println(supplierTuple.value1);
                        commande.supplier = supplierTuple;
                        //gestion transporteur
                        System.out.println("choix du transporteur");

                        Tuple transportTuple = cs.sendMessage(new Tuple("transportOffre", Integer.toString(id), sav.value2));
                        System.out.println(transportTuple.value1);
                        commande.transport = transportTuple;
                        commande.print();
                        ts.add("FinOffre", "");


                    }

                }

                ts.release();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
