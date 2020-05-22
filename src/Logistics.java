import java.util.ArrayList;

public class Logistics extends Thread {
    String name;
    TupleSpace ts;
    TupleSpace ws;
    ArrayList<String> treated;
    public Logistics(String name, TupleSpace ts, TupleSpace ws){
        this.name = name;
        this.ts = ts;
        ts.addProc();
        this.ws = ws;
        ws.addProc();
        this.treated = new ArrayList<String>();
        System.out.println("fabricant créé avec id : "+name);
    }


    public void run() {
        String TupleID;
        while(1 == 1){
            if(ws.capture(this.name)){
                if(ts.contains("AppelOffre") && !treated.contains(ts.getTupleValue("AppelOffre"))  && !ws.contains("CostRequest") && !ws.contains("RequirementRequest")){
                    TupleID = ts.getTupleValue("AppelOffre");
                    ws.add("CostRequest", TupleID);
                    ws.add("RequirementRequest", TupleID);
                    //System.out.println("Appel d'offre reçu, id :"+TupleID);
                    treated.add(TupleID);

                }

                if(ws.contains("RequirementResponse") && ws.contains("CostResponse")){
                    ws.capture(this.name);
                    if(ws.getTupleValue("RequirementResponse").equals("true")){
                        ts.add("ContreOffre", this.name+"|"+ws.getTupleValue("RequirementResponse")+"|"+ws.getTupleValue("CostResponse"));
                    }
                    //ts.add(ws.getTupleValue("RequirementResponse"));
                    System.out.println("réponse transmise");
                    ws.list.clear();
                    ws.release();
                }
                ws.release();
            }else{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
