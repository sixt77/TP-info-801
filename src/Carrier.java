import java.util.ArrayList;

public class Carrier extends Thread {
    String name;
    TupleSpace cs;
    ArrayList<String> treated;
    public Carrier(String name, TupleSpace cs){
        this.name = name;
        this.cs = cs;
        cs.addProc();
        this.treated = new ArrayList<String>();
        System.out.println("transporteur créé avec id : "+name);
    }


    public void run() {
        String TupleID;
        while(1 == 1){
            if(cs.capture(this.name)){
                if(cs.contains("transportOffre") && !treated.contains(cs.getTupleValue("transportOffre"))){
                    TupleID = cs.getTupleValue("transportOffre");
                    cs.add("transportContreOffre", name+"|"+Integer.toString((int) (Math.random() * 200)));
                    System.out.println("réponse transmise");
                    treated.add(TupleID);
                }
                cs.release();
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
