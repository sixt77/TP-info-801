import java.util.Scanner;
public class Main{
    public static void main(String a[]){
        int id = 1;
        boolean waiting = false;
        boolean out = false;
        TupleSpace ts = new TupleSpace();
        TupleSpace tt = new TupleSpace();
        TupleSpace cs = new TupleSpace();
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        Scanner sc4 = new Scanner(System.in);
        Scanner sc5 = new Scanner(System.in);
        Scanner sc6 = new Scanner(System.in);

        int response;
        String responseStr;
        while(!out){
            if(!waiting){
                dispayMenu();
                response = sc1.nextInt();
                switch (response){
                    case 1:
                        TupleSpace fs = new TupleSpace();
                        System.out.println("veuillez entrer un nom de fabricant");
                        responseStr = sc4.nextLine();
                        Logistics f = new Logistics(responseStr, ts, fs);
                        Design d = new Design(responseStr+"-Design", fs);
                        Workshop w = new Workshop(responseStr+"-Workshop", fs, 1);
                        f.start();
                        d.start();
                        w.start();
                        break;
                    case 2:
                        System.out.println("veuillez entrer un nom de fournisseur");
                        responseStr = sc5.nextLine();
                        Supplier f2 = new Supplier(responseStr, tt);
                        f2.start();
                        break;
                    case 3:
                        System.out.println("veuillez entrer un nom de transporteur");
                        responseStr = sc6.nextLine();
                        Carrier c = new Carrier(responseStr, cs);
                        c.start();
                        break;
                    case 4:
                        id++;
                        ts.add("AppelOffre", Integer.toString(id));
                        System.out.println("création appel d'offre");
                        waiting = true;
                        break;
                    case 9:
                        System.out.println("aurevoir");
                        out = true;
                        break;
                    default:
                        System.out.println("choix non répertoirié");
                }
            }else{
                System.out.println("en attente de réponses, appuyer sur S pour stopper la recherche");
                while(!sc2.nextLine().equals("S")){

                }
                ts.capture("main");
                ts.printTSasList();
                System.out.println("selectionnez le numéro de la proposition choisit (O) pour relancer l'offre");
                response = sc3.nextInt();

                if(response == 0){
                    ts.list.clear();
                    id++;
                    ts.add("AppelOffre", Integer.toString(id));
                    System.out.println("relance appel d'offre");

                }else{
                    System.out.println("validation du choix : "+response);
                    //enregistrement rep
                    ts.list.clear();

                    waiting = false;
                }
                ts.release();
                //gestion fournisseur
                System.out.println("choix du fournisseur");
                Tuple test = tt.sendMessage("fournisseurOffre", "1");
                System.out.println(test.value);
                //gestion transporteur
                System.out.println("choix du transporteur");
                Tuple test2 = cs.sendMessage("transportOffre", "1");
                System.out.println(test2.value);

            }
        }
    }
    public static void dispayMenu(){
        System.out.println("1 :  Creer fabricant");
        System.out.println("2 :  Creer fournisseur");
        System.out.println("3 :  Creer transporter");
        System.out.println("4 :  Creer appel d'offre");
        System.out.println("9 :  Sortir");
    }
}
