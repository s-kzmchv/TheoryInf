import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;


import static java.lang.Math.*;


public class Probability {


    static class Elem{
        private String characters="";
        private Double probability = 0.0;

        public Elem(String s, Double p){
            characters = s;
            probability = p;
        }

        public static final Comparator<Elem> COMPARE_BY_PROBABILITY = new Comparator<Elem>() {
            @Override
            public int compare(Elem t1, Elem t2) {
                //Double.compare()
                return Double.compare(t1.probability,t2.probability);
            }
        };

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Elem) {
                Elem tmp = (Elem) obj;
                if (this.characters.equals(tmp.characters) && this.probability.equals(tmp.probability)){
                    return true;
                }
                else {
                    return false;
                }
            }

            if (obj instanceof String) {
                String tmp = (String) obj;
                if (this.characters.equals(tmp)){
                    return true;
                }
                else {
                    return false;
                }
            }

            if (obj instanceof Double) {
                Double tmp = (Double) obj;
                if (this.probability.equals(tmp)){
                    return true;
                }
                else {
                    return false;
                }
            }

            return false;


        }
    }


    private ArrayList<Elem> characters_probability = new ArrayList<>();
    private double H = 0;

    public Probability(){ }

    public Probability(String str){

        for (int i = 0; i < str.length(); i++){
            add(str.substring(i,i+1));
        }

        div(str.length());
    }

    public String makeString(int length) {

        Random random = new Random(System.currentTimeMillis());
        StringBuilder res = new StringBuilder();

        for (int i = 0; i <length; i++) {
            int tmp = random.nextInt(1000);
            double d = (tmp % 1000);
            d /=1000;
            res.append(getChars(d));
        }

        return res.toString();

    }

    public Probability makeNewCharacters(int n){
        Probability res = new Probability();

        recursia(n,new StringBuffer(),res);

        return res;
    }

    public void recursia(int n, StringBuffer stringBuffer, Probability res ){

        if (n-1 == 0){
            for (int i = 0; i < characters_probability.size(); i++){
                stringBuffer.append(characters_probability.get(i).characters);



                double probability = 1;

                for( int j = 0; j < stringBuffer.length(); j++) {
                    String tmp = String.valueOf(stringBuffer.charAt(j));
                    Iterator<Elem> it = characters_probability.listIterator();
                    while (it.hasNext()) {
                        Elem tmpElem = it.next();
                        if (tmpElem.characters.equals(tmp)) {
                            probability *= tmpElem.probability;
                            break;
                        }
                    }
                }

                res.addBoth(stringBuffer.toString(),probability);

                stringBuffer.deleteCharAt(stringBuffer.length()-1);
            }
        }
        else {
            for (int i = 0; i < characters_probability.size(); i++) {
                stringBuffer.append(characters_probability.get(i).characters);
                recursia(n-1,stringBuffer,res);
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            }
        }
    }

    public void addBoth(String s, double p) {
        if (!characters_probability.contains(s)) {
            characters_probability.add(new Elem(s,p));
        }
    }


    public void makeH() {
        for(int i = 0; i < characters_probability.size(); i ++){
            H -= characters_probability.get(i).probability * (log10(characters_probability.get(i).probability)/log10(2));
        }
    }

    public double getH() {
        return H;
    }



    public void add(String str){

        Iterator<Elem> it = characters_probability.listIterator();
        while (it.hasNext()){
            Elem tmp = it.next();
            if (tmp.characters.equals(str)){
                tmp.probability++;
                return;
            }
        }

        characters_probability.add(new Elem(str,1.0));

    }

    public void div(int value){

        Iterator<Elem> it = characters_probability.listIterator();
        while (it.hasNext()){
            Elem tmp = it.next();

            tmp.probability /= value;

        }
    }


    public String getChars(double d){
        double tmp = 0;
        for (int i = 0; i < characters_probability.size();i++){
            Elem tmpElem = characters_probability.get(i);
            if (d >= tmp && d < (tmp + tmpElem.probability)){
                return tmpElem.characters;
            }
            else
            {
                tmp += tmpElem.probability;
            }
        }
        return "";
    }

    public int size(){return characters_probability.size();}

    public void sort(){
        characters_probability.sort(Elem.COMPARE_BY_PROBABILITY);
    }

    public Double countPe(int lost) {
        Double res = 0.0;
        for (int i = 0; i < lost; i++){
            res += characters_probability.get(i).probability;
        }

        return res;
    }

    public String toString()
    {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < characters_probability.size(); i++){
            res.append(characters_probability.get(i).characters + "=" + String.format("%.5f", characters_probability.get(i).probability) + "; ");
        }

        return res.toString();
    }


}