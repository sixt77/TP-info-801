public class Tuple {
    public String name;
    public String value1;
    public String value2;
    public String value3;
    public String value4;
    public Tuple(String name, String value1){
        this.name = name;
        this.value1 = value1;
        this.value2 = "";
        this.value3 = "";
        this.value4 = "";
    }
    public Tuple(String name, String value1, String value2){
        this.name = name;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = "";
        this.value4 = "";
    }
    public Tuple(String name, String value1, String value2, String value3){
        this.name = name;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = "";
    }
    public Tuple(String name, String value1, String value2, String value3, String value4){
        this.name = name;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
    }
    public void printTuple(){
        System.out.println("name : "+this.name);
        System.out.println("v1 : "+this.value1);
        System.out.println("v2 : "+this.value2);
        System.out.println("v3 : "+this.value3);
        System.out.println("v4 : "+this.value4);
    }
}
