import java.util.Scanner;
public class Main{
    public static void main(String a[]){
        boolean out = false;
        TupleSpace ts = new TupleSpace();
        Scanner sc1 = new Scanner(System.in);

        while(!out){
            dispayMenu();
            int response = sc1.nextInt();
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
                    ts.add("AppelOffre", "50");
                    System.out.println("création appel d'offre");
                    break;
                case 9:
                    System.out.println("aurevoir");
                    out = true;
                    break;
                default:
                    System.out.println("choix non répertoirié");
            }

        }
    }
    public static void dispayMenu(){
        System.out.println("1 :  Creer fabricant");
        System.out.println("2 :  Creer appel d'offre");
        System.out.println("9 :  Sortir");
    }
}
