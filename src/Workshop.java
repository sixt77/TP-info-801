import java.util.ArrayList;

public class Workshop extends Thread {
    String name;
    TupleSpace ws;
    int manage;
    ArrayList<String> treated;
    public Workshop(String name, TupleSpace ws, int manage){
        this.name = name;
        this.ws = ws;
        ws.addProc();
        this.manage = manage;
        this.treated = new ArrayList<String>();
        System.out.println("l'atelier créé avec id : "+name);
    }


    public void run() {
        Tuple tuple;
        while (1 == 1) {
            if (ws.capture(this.name)) {
                if (ws.contains("RequirementRequest") && !treated.contains(ws.getTupleValue("RequirementRequest"))) {
                    tuple = ws.getTuple("RequirementRequest");
                    //System.out.println("renvoi de Requirement, id :"+TupleID);
                    ws.add("RequirementResponse", Integer.toString((int) (Math.random() * Integer.parseInt(tuple.value3)*Integer.parseInt(tuple.value4)*1.2)));
                    treated.add(tuple.value1);
                }
                ws.release();
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
