import java.util.Scanner;
public class Main{
    public static void main(String a[]){
        int id = 1;
        boolean waiting = false;
        boolean out = false;
        TupleSpace ts = new TupleSpace();
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        int response;
        while(!out){
            if(!waiting){
                dispayMenu();
                response = sc1.nextInt();
                switch (response){
                    case 1:
                        TupleSpace fs = new TupleSpace();
                        Logistics f = new Logistics("fab1", ts, fs);
                        Design d = new Design("fab1-Design", fs);
                        Workshop w = new Workshop("fab1-Workshop", fs, 1);
                        f.start();
                        d.start();
                        w.start();
                        break;
                    case 2:
                        ts.add("AppelOffre", Integer.toString(id));
                        System.out.println("création appel d'offre");
                        waiting = true;
                        id++;
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
                ts.list.clear();
                if(response == 0){
                    ts.add("AppelOffre", Integer.toString(id));
                    System.out.println("relance appel d'offre");

                }else{
                    System.out.println("validation du choix"+response);
                    waiting = false;
                }

                ts.release();


            }
        }
    }
    public static void dispayMenu(){
        System.out.println("1 :  Creer fabricant");
        System.out.println("2 :  Creer appel d'offre");
        System.out.println("9 :  Sortir");
    }
}
