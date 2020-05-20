import java.util.ArrayList;

public class Logistics extends Thread {
    String name;
    TupleSpace ts;
    TupleSpace ls;
    ArrayList<String> treated;
    public Logistics(String name, TupleSpace ts, TupleSpace fs){
        this.name = name;
        this.ts = ts;
        this.ls = fs;
        this.treated = new ArrayList<String>();
        System.out.println("fabricant créé avec id :"+name);
    }


    public void run() {
        String TupleID;
        while(1 == 1){
            if(ts.contains("AppelOffre") && !treated.contains(ts.getTupleValue("AppelOffre"))  && !ls.contains("CostRequest") && !ls.contains("RequirementRequest")){
                ts.waitTS();
                ts.capture();
                TupleID = ts.getTupleValue("AppelOffre");
                ls.add("CostRequest", TupleID);
                ls.add("RequirementRequest", TupleID);
                System.out.println("Appel d'offre reçu, id :"+TupleID);
                treated.add(TupleID);
                ts.release();
            }

            if(ls.contains("RequirementResponse") && ls.contains("CostResponse")){
                ls.waitTS();
                ls.capture();
                System.out.println("reponse transmie");
                ls.release();
                ls = new TupleSpace();
            }
        }
    }

}
