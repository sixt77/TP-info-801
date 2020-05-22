import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TupleSpace {
    public List<Tuple> list;
    public boolean  free;
    public String lastProc;
    public int nbProc;
    public TupleSpace(){
        {
            this.list = Collections.synchronizedList(new ArrayList<Tuple>());
            this.free = true;
            this.lastProc = "";
            this.nbProc = 0;
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
        if(this.free && (!this.lastProc.equals(name) || nbProc <= 1)){
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
        int j = 0;
        for(int i = 0; i < this.list.size(); i++){
            if(!this.list.get(i).name.equals("AppelOffre") && !this.list.get(i).name.equals("fournisseurOffre") && !this.list.get(i).name.equals("transportOffre")){
                System.out.println((j+1)+" : "+this.list.get(i).value);
                j++;
            }

        }
    }
    public void addProc(){
        this.nbProc++;
    }

    public Tuple sendMessage(String str1, String str2){
        Scanner sc1 = new Scanner(System.in);
        this.add(new Tuple(str1, str2));
        System.out.println("en attente de réponses, appuyer sur S pour stopper la recherche");
        while(!sc1.nextLine().equals("S")){

        }
        this.capture("main");
        this.printTSasList();
        Tuple sav = this.safeGet();
        this.list.clear();
        this.release();
        return sav;
    }
    public Tuple safeGet(){
        Scanner sc1 = new Scanner(System.in);
        System.out.println("selectionnez le numéro de la proposition choisit");
        int id = sc1.nextInt();
        while(id < 0 || id > this.list.size()-1){
            System.out.println("erreur selection impossible");
            System.out.println("selectionnez le numéro de la proposition choisit");
            id = sc1.nextInt();
        }

        return this.list.get(id);
    }

}
