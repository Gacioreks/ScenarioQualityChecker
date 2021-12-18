package pl.put.poznan.transformer.logic;

public class myInt {
    public int value;

    public myInt(int a){
        value = a;
    }

    public void increment(){
        this.value+=1;
    }
    public void set(int a){this.value=a;}
    public int getValue(){
        return value;
    }
}
