import java.util.ArrayList;

public class Supplier extends Thread {
    String name;
    TupleSpace tts;
    ArrayList<String> treated;
    public Supplier(String name, TupleSpace tts){
        this.name = name;
        this.tts = tts;
        tts.addProc();
        this.treated = new ArrayList<String>();
        System.out.println("fournisseur créé avec id : "+name);
    }


    public void run() {
        Tuple tuple;
        while(1 == 1){
            if(tts.capture(this.name)){
                if(tts.contains("fournisseurOffre") && !treated.contains(tts.getTupleValue("fournisseurOffre"))){
                    tuple = tts.getTuple("fournisseurOffre");
                    treated.add(tuple.value1);
                    tts.add(new Tuple("fournisseurContreOffre", name, Integer.toString((int) (Math.random() * Integer.parseInt(tuple.value2)/3))));
                    System.out.println("réponse transmise");

                }
                tts.release();
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
