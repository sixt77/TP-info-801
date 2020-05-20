public class TestThread3 extends Thread {
    String name;
    TupleSpace ts;
    Tuple t1;
    Tuple t2;
    public TestThread3(String name, TupleSpace ts){
        this.name = name;
        this.ts = ts;
        this.t1 =  t1;
        this.t2 =  t2;
    }
    public TestThread3(String name, TupleSpace ts, Tuple t1, Tuple t2){
        this.name = name;
        this.ts = ts;
        this.t1 =  t1;
        this.t2 =  t2;
    }
    public void load(Tuple t1, Tuple t2){
        this.t1 = t1;
        this.t2 = t2;
    }

    public void run() {
        int i = 0;
        while(i < 5){
            if(ts.contains(this.t1) && ts.free){
                ts.capture();
                ts.remove(this.t1);
                ts.add(this.t2);
                i++;
                System.out.println("add : "+this.name);
                ts.release();
            }else{
                //System.out.println("waiting : "+this.name);
            }
        }
        System.out.println("end : "+this.name);
    }

}
