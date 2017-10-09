import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TheoryInf {

    static class Prob{
        private ArrayList<Double> probability = new ArrayList<>();
        private ArrayList<Character> character = new ArrayList<>();

        public void add(char c){
            if (character.contains(c)){
                int tmp = character.indexOf(c);
                probability.set(tmp,probability.get(tmp) + 1.0);
            }
            else {
                character.add(c);
                probability.add(1.0);
            }
        }

        public void div(int value){
            for (int i = 0; i < probability.size();i++){
                probability.set(i,probability.get(i) / value);
                //probability[i] = probability[i] / value;
            }
        }


        public char getChar(double d){
            double tmp = 0;
            for (int i = 0; i < probability.size();i++){
                if (d >= tmp && d < (tmp + probability.get(i))){
                    return character.get(i);
                }
                else
                {
                    tmp += probability.get(i);
                }
            }
            return ' ';
        }

        public String toString()
        {
            StringBuilder res = new StringBuilder();

            res.append(character.toString() + "\n" + probability.toString());

            return res.toString();
        }


    }

    Prob check;


    public void stat(String str){
        Prob res = new Prob();

        for (int i = 0; i < str.length(); i++){
            res.add(str.charAt(i));
        }

        res.div(str.length());

        check =  res;
    }


    public String makeString(int length) {

        Random random = new Random(System.currentTimeMillis());
        StringBuilder res = new StringBuilder();

        for (int i = 0; i <length; i++) {
            int tmp = random.nextInt(1000);
            double d = (tmp % 1000);
            d /=1000;
            res.append(check.getChar(d));
        }

        return res.toString();

    }

    public static void main(String[] args) {

        TheoryInf t = new TheoryInf();
        t.stat("loqq");

        System.out.println(t.check);

        String str = t.makeString(100000);

        System.out.println(str);

        t.stat(str);

        System.out.println(t.check);




    }


}
