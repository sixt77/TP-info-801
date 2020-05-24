import java.util.ArrayList;

public class Design extends Thread {
    String name;
    TupleSpace ws;
    ArrayList<String> treated;
    public Design(String name, TupleSpace ws){
        this.name = name;
        this.ws = ws;
        ws.addProc();
        this.treated = new ArrayList<String>();
        System.out.println("bureau d'étude créé avec id : "+name);
    }


    public void run() {
        Tuple tuple;
        while(1 == 1){
            if(ws.capture(this.name)){
                if(ws.contains("CostRequest") && !treated.contains(ws.getTupleValue("CostRequest"))){
                    tuple = ws.getTuple("CostRequest");
                    //System.out.println("renvoi de cost request, id :"+TupleID);
                    ws.add("CostResponse",  Integer.toString((int) (Math.random() * Integer.parseInt(tuple.value2)/2)));
                    treated.add(tuple.value1);
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
