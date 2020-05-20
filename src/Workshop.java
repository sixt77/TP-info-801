import java.util.ArrayList;

public class Workshop extends Thread {
    String name;
    TupleSpace ls;
    int manage;
    ArrayList<String> treated;
    public Workshop(String name, TupleSpace fs, int manage){
        this.name = name;
        this.ls = fs;
        this.manage = manage;
        this.treated = new ArrayList<String>();
        System.out.println("l'atelier créé avec id :"+name);
    }


    public void run() {
        String TupleID;
        while(1 == 1){
            if(ls.contains("RequirementRequest") && !treated.contains(ls.getTupleValue("RequirementRequest"))){
                ls.waitTS();
                ls.capture();
                TupleID = ls.getTupleValue("RequirementRequest");
                System.out.println("renvoi de Requirement, id :"+TupleID);
                ls.add("RequirementResponse", "true");
                treated.add(TupleID);
                ls.release();
            }
        }
    }

}
