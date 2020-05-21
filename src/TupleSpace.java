import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TupleSpace {
    public List<Tuple> list;
    public boolean  free;
    public String lastProc;
    public TupleSpace(){
        {
            this.list = Collections.synchronizedList(new ArrayList<Tuple>());
            this.free = true;
            this.lastProc = "";
        }
    }

    public void add(Tuple t1){
        list.add(t1);
    }

    public void add(String name, String value){
        list.add(new Tuple(name, value));
    }

    public boolean contains(Tuple t1){
        if(this.list.contains(t1)){
            return true;
        }else{
            return false;
        }
    }
    public boolean contains(String name){
        boolean find = false;
        for(int i = 0; i < this.list.size(); i++){
            if(this.list.get(i).name.equals(name))find = true;
        }
        return find;
    }

    public String getTupleValue(String name){
        String result = "";
        for(Tuple elt : this.list){
            if(elt.name.equals(name))result = elt.value;
        }
        return result;
    }

    public void remove(Tuple t1){
        list.remove(t1);
    }

    public void remove(String name){
        for(Tuple elt : this.list){
            if(elt.name.equals(name))this.list.remove(elt);
        }
    }
    public synchronized boolean capture(String name){
        if(this.free && !this.lastProc.equals(name)){
            this.free = false;
            this.lastProc = name;
            return true;
        }else{
            return false;
        }

    }
    public synchronized void release(){
        this.free = true;
    }
    public void printTS(){
        this.list.stream().forEach(elt-> {
            System.out.println("nom : "+elt.name+", value : "+elt.value);
        });
    }
    public void printTSasList(){
        for(int i = 0; i < this.list.size(); i++){
            System.out.println((i+1)+" : "+this.list.get(i).value);
        }
    }

}
