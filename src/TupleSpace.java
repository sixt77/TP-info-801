import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TupleSpace {
    private List<Tuple> list;
    public boolean free;
    public TupleSpace(){
        {
            this.list = Collections.synchronizedList(new ArrayList<Tuple>());
            this.free = true;
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
    public void capture(){
        this.free = false;
    }
    public void release(){
        this.free = true;
    }
    public void waitTS(){
        while(!this.free){
            try {
                wait(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
