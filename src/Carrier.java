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
        Tuple tuple;
        while(1 == 1){
            if(cs.capture(this.name)){
                if(cs.contains("transportOffre") && !treated.contains(cs.getTupleValue("transportOffre"))){
                    tuple = cs.getTuple("transportOffre");
                    treated.add(tuple.value1);
                    cs.add(new Tuple("transportContreOffre", name, Integer.toString((int) (Math.random() * Integer.parseInt(tuple.value2)/3))));
                    System.out.println("réponse transmise");

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
