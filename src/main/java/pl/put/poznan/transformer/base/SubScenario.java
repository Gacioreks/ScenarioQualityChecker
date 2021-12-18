package pl.put.poznan.transformer.base;

import pl.put.poznan.transformer.logic.Visitor;
import pl.put.poznan.transformer.logic.myInt;

import java.util.ArrayList;
import java.util.Objects;

public class SubScenario {
    public ArrayList<Object> content;
    public ArrayList<String> save;
    private int quantity = 0;
    private int key_words = 0;
    private ArrayList<Step> invalid_steps;
    

    SubScenario() {
        content = new ArrayList<>();
        save = new ArrayList<>();
        invalid_steps = new ArrayList<Step>();
    }

    public ArrayList<Object> addContent(myInt start, ArrayList<String> list,int lvl)
    {
        String line;

        for(;start.getValue()<list.size();start.increment()) {
            line = list.get(start.getValue());
            line=line.replaceAll("\\t+","");
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

        start.set(3);
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
        start.set(3);
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
                content.add(sub);
                for(int y=0; y<sub.save.size();y++){
                    save.add(sub.save.get(y));
                }

            } else if (Objects.equals(line, "<end>")) {
                return content;
            }
            else {
                if (stp == 0){
                    save.add(line);
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
                        save.add(line);
                        Step s = new Step(lvl,line);
                        content.add(s);
                        stp+=1;
                    }
                    else{
                        line = String.valueOf(stp) + "." + " " + line;
                        save.add(line);
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

    public void get_steps_count(){
        if (quantity!=0) {
            System.out.println("Liczba wszystkich kroków: " + (quantity - 1)); // quantity - 1 bo pierwszy element w liscie content jest pusty. Dlaczego ?
            this.quantity = 0;
        }else{
            System.out.println("Liczba wszystkich kroków: " + (quantity));

        }
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
                    if (words.get(0).equals(actor.trim())){
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
        if(this.invalid_steps.size() == 0){
            System.out.println("Brak Kroków nie zaczynających się od aktora");
        }else {
            System.out.println("Kroki nie zaczynające się od aktora(" + this.invalid_steps.size() + "):");
            for (Step s : this.invalid_steps) {
                System.out.println(s.value);
            }
        }
        this.invalid_steps.clear();
    }
    
    public void accept(Visitor v){
        v.visit(this);
        for (Object s : this.content){
            if(s.getClass()==Step.class) ((Step) s).accept(v);
            else ((SubScenario) s).accept(v);
        }
    }
}
