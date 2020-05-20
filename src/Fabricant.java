public class Fabricant extends Thread {
    String name;
    TupleSpace ts;
    TupleSpace fs;
    public Fabricant(String name, TupleSpace ts, TupleSpace fs){
        this.name = name;
        this.ts = ts;
        this.fs = fs;
        System.out.println("fabricant créé avec id :"+name);
    }


    public void run() {
        String TupleID;
        while(1 == 1){
            if(ts.contains("Appel")){
                System.out.println("suka");
                ts.waitTS();
                ts.capture();
                TupleID = ts.getTupleValue("Appel");
                System.out.println("Appel d'offre reçu, id :"+TupleID);

                System.out.println("add : "+this.name);
                ts.release();


            }
            if(fs.contains("Contre")){
                while(!fs.free){
                    fs.waitTS();
                    fs.capture();

                    fs.release();
                }
            }
        }
    }

}
