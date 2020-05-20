import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
public class Main{
    public static void main(String a[]){
        TupleSpace ts = new TupleSpace();

//List methods are synchronized
        Tuple tuple1 = new Tuple("test1", "0");
        Tuple tuple2 = new Tuple("test2", "0");
        Tuple tuple3 = new Tuple("test3", "0");
        Tuple tuple4 = new Tuple("test4", "0");
        TestThread1 t1 = new TestThread1("t1", ts);
        TestThread2 t2 = new TestThread2("t2", ts);
        TestThread3 t3 = new TestThread3("t3", ts);
        TestThread4 t4 = new TestThread4("t4", ts);
        t1.load(tuple1, tuple2);
        t2.load(tuple2, tuple3);
        t3.load(tuple3, tuple4);
        t4.load(tuple4, tuple1);
        t4.start();
        t3.start();
        t2.start();
        t1.start();


        ts.add(tuple1);

//Use explicit synchronization while iterating


    }
}
