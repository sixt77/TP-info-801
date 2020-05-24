import java.util.ArrayList;

public class Maker extends Thread {
    String name;
    TupleSpace ts;
    TupleSpace ws;
    ArrayList<String> treated;
    public Maker(String name, TupleSpace ts, TupleSpace ws){
        this.name = name;
        this.ts = ts;
        ts.addProc();
        this.ws = ws;
        ws.addProc();
        this.treated = new ArrayList<String>();
        System.out.println("fabricant créé avec id : "+name);
    }


    public void run() {
        Tuple tuple;
        while(1 == 1){
            if(ws.capture(this.name)){
                if(ts.contains("AppelOffre") && !treated.contains(ts.getTupleValue("AppelOffre"))  && !ws.contains("CostRequest") && !ws.contains("RequirementRequest")){
                    tuple = ts.getTuple("AppelOffre");

                    ws.add(new Tuple("CostRequest", tuple.value1, tuple.value2, tuple.value3,tuple.value4));
                    ws.add(new Tuple("RequirementRequest", tuple.value1, tuple.value2, tuple.value3, tuple.value4));
                    //System.out.println("Appel d'offre reçu, id :"+TupleID);
                    treated.add(tuple.value1);

                }

                if(ws.contains("RequirementResponse") && ws.contains("CostResponse")){
                    ws.capture(this.name);
                    ts.add(new Tuple("ContreOffre", this.name, ws.getTuple("CostResponse").value1, ws.getTuple("RequirementResponse").value1));
                    new Tuple("ContreOffre", this.name, ws.getTuple("CostResponse").value1, ws.getTuple("RequirementResponse").value1);
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
