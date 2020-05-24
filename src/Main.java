import java.util.Scanner;
public class Main{
    public static void main(String a[]){
        int id = 0;
        boolean waiting = false;
        boolean out = false;
        TupleSpace ts = new TupleSpace();
        TupleSpace tt = new TupleSpace();
        TupleSpace cs = new TupleSpace();
        Scanner sc1 = new Scanner(System.in);
        Scanner sc4 = new Scanner(System.in);
        Scanner sc5 = new Scanner(System.in);
        Scanner sc6 = new Scanner(System.in);
        Logistic l = new Logistic("logistic1", ts, tt, cs);
        l.start();
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
                        Maker f = new Maker(responseStr, ts, fs);
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
                        ts.add(new Tuple("CreationOffre", Integer.toString(id)));
                        ts.printTS();
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
                if(ts.capture("main")){
                    if(ts.contains("FinOffre")){
                        waiting = false;
                        ts.list.clear();
                    }
                    ts.release();
                }

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
