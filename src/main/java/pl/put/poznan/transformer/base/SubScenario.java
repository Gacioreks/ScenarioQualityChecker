package pl.put.poznan.transformer.base;

import com.google.gson.Gson;
import pl.put.poznan.transformer.logic.Visitor;
import pl.put.poznan.transformer.logic.myInt;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SubScenario {
    public ArrayList<Object> content;
    private int quantity = 0;
    private int key_words = 0;
    private ArrayList<Step> invalid_steps;
    

    SubScenario() {
        content = new ArrayList<>();
        invalid_steps = new ArrayList<Step>();
    }

    public ArrayList<Object> addContent(myInt start, ArrayList<String> list,int lvl)
    {
        String line;

        for(;start.getValue()<list.size();start.increment()) {
            line = list.get(start.getValue());
            line=line.replaceAll("\t+","");
            if (line.equals("<start>")) {
                start.increment();

                SubScenario sub = new SubScenario();
                sub.addContent(start,list,lvl+1);
                content.add(sub);

            } else if (Objects.equals(line, "<end>")) {
                return content;
            }
            else {
                Step s = new Step(lvl,line);
                content.add(s);
            }
        }

        return content;
    }

    public ArrayList<Object> lvlshow(myInt start, ArrayList<String> list,int lvl, int stop)
    {
        String line;

        for(;start.getValue()<list.size();start.increment()) {
            line = list.get(start.getValue());
            line=line.replaceAll("\\t+","");
            if (line.equals("<start>")) {
                start.increment();

                SubScenario sub = new SubScenario();
                sub.lvlshow(start,list,lvl+1, stop);
                content.add(sub);

            } else if (Objects.equals(line, "<end>")) {
                return content;
            }
            else {
                if (lvl >= stop){
                    continue;
                }
                else{
                    Step s = new Step(lvl,line);
                    content.add(s);
                }
            }
        }

        return content;
    }

    public ArrayList<Object> numerized(myInt start, ArrayList<String> list,int lvl, int stp, String dep)
    {
        String line;

        for(;start.getValue()<list.size();start.increment()) {
            line = list.get(start.getValue());
            line=line.replaceAll("\\t+","");
            if (line.equals("<start>")) {
                start.increment();
                if (dep == ""){
                    dep = dep + String.valueOf(stp-1);
                }
                else{
                    dep = dep + "." + String.valueOf((stp-1));
                }


                SubScenario sub = new SubScenario();
                sub.numerized(start,list,lvl+1, 1, dep);
                dep="";
                content.add(sub);

            } else if (Objects.equals(line, "<end>")) {
                return content;
            }
            else {
                if (stp == 0){
                    Step s = new Step(lvl,line);
                    content.add(s);
                    stp+=1;
                }
                else {
                    if (lvl > 0){
                        line = dep + "." + String.valueOf(stp) + "." + " " + line;
                        for (int i=0; i<lvl*3; i++){
                            line = " " + line;
                        }
                        Step s = new Step(lvl,line);
                        content.add(s);
                        stp+=1;
                    }
                    else{
                        line = String.valueOf(stp) + "." + " " + line;
                        Step s = new Step(lvl,line);
                        content.add(s);
                        stp+=1;
                    }

                }

            }
        }
        return content;
    }

    
     public void step_counter(SubScenario cont){
        for (Object s : cont.content){
            if(s.getClass() == Step.class){
                this.quantity += 1;
            }else{
                step_counter((SubScenario) s);
            }
        }
    }

    public int get_steps_count(){
        if (quantity!=0) {
            System.out.println("Liczba wszystkich kroków: " + (quantity - 1)); // quantity - 1 bo pierwszy element w liscie content jest pusty. Dlaczego ?
            int out = this.quantity-1;
            this.Save2JSONint(out,"./json/steps_count.json");
            this.quantity = 0;
            return out;
        }else{
            System.out.println("Liczba wszystkich kroków: " + (quantity));
            this.Save2JSONint(this.quantity,"./json/steps_count.json");
        }
        return this.quantity;
    }

    public void key_word_counter(SubScenario cont){
        String [] temp;
        for (Object s : cont.content){
            if(s.getClass() == Step.class){
                temp = ((Step) s).value.split(" ");
                for (String word : temp){
                    if (word.equals("IF:") || word.equals("FOR") || word.equals("ELSE:")){
                        this.key_words += 1;
                    }
                }
            }else{
                key_word_counter((SubScenario) s);
            }
        }
    }

    public void get_key_words_count(){
        System.out.println("Liczba słów kluczowych: " + this.key_words);
        this.Save2JSONint(this.key_words,"./json/Keywords.json");
        this.key_words = 0;
    }
    
    public void step_check(SubScenario sc, ArrayList<String> act, String sys_act){
        String [] temp;
        String tem;
        ArrayList<String> words = new ArrayList<String>();
        boolean starting_with_actor = false;


        for(Object s : sc.content){
            if(s.getClass() == Step.class){
                if (((Step) s).value.equals("")){
                    continue;
                }
                tem = ((Step) s).value;
                tem = tem.trim();
                temp = tem.split(" ");

                for (String t : temp){
                    words.add(t);
                }

                //words.remove(0); // usunięcie numeru
                if(words.get(0).equals("IF:") || words.get(0).equals("ELSE:")){
                    words.remove(0);
                }

                if(words.get(0).equals("FOR")){
                    words.clear();
                    continue;
                }

                for (String actor : act) {
                    if (words.get(0).equals(actor)){
                        starting_with_actor = true;
                    }
                }

                if(words.get(0).equals(sys_act)){
                    starting_with_actor = true;
                }

                if(!starting_with_actor){
                    this.invalid_steps.add((Step) s);
                }
                starting_with_actor = false;
                words.clear();
            }
            else{
                step_check((SubScenario) s, act, sys_act);
            }
        }
    }

    public void get_invalid_steps(){
        ArrayList<Step> tmp = new ArrayList<>();
        if(this.invalid_steps.size() == 0){
            System.out.println("Brak Kroków nie zaczynających się od aktora");
        }else {
            System.out.println("Kroki nie zaczynające się od aktora(" + this.invalid_steps.size() + "):");
            for (Step s : this.invalid_steps) {
                System.out.println(s.value);
                tmp.add(s);
            }
        }
        this.Save2JSON(tmp,"./json/Stepscheck.json");
        //this.invalid_steps.clear();

    }
    
    public void accept(Visitor v){
        v.visit(this);
        for (Object s : this.content){
            if(s.getClass()==Step.class) ((Step) s).accept(v);
            else ((SubScenario) s).accept(v);
        }
    }

    public  void Save2JSON(ArrayList<Step> s,String fileJson){
        FileWriter file = null;
        //private static FileWriter file;
        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("./files/"+fileJson);
            file.write(new Gson().toJson(s));

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public  void Save2JSONint(int i,String fileJson){
        FileWriter file = null;
        //private static FileWriter file;
        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("./files/"+fileJson);
            file.write(new Gson().toJson(i));

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
