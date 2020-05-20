import java.util.ArrayList;

public class Design extends Thread {
    String name;
    TupleSpace ls;
    ArrayList<String> treated;
    public Design(String name, TupleSpace fs){
        this.name = name;
        this.ls = fs;
        this.treated = new ArrayList<String>();
        System.out.println("bureau d'étude créé avec id :"+name);
    }


    public void run() {
        String TupleID;
        while(1 == 1){
            if(ls.contains("CostRequest") && !treated.contains(ls.getTupleValue("CostRequest"))){
                ls.waitTS();
                ls.capture();
                TupleID = ls.getTupleValue("CostRequest");
                System.out.println("renvoi de cost request, id :"+TupleID);
                ls.add("CostResponse", "200");
                treated.add(TupleID);
                ls.release();
            }
        }
    }

}
