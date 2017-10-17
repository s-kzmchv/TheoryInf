import java.util.ArrayList;
import java.util.Random;

public class TheoryInf {


    public static void main(String[] args) {


        //Probability probability = new Probability("aaaaabbbbc");
        Probability probability = new Probability("кузьмичёв семён");

        System.out.println(probability);

        Table tableFor1 = new Table(probability,1);

        System.out.println(tableFor1);

        Table tableFor2 = new Table(probability,2);

        System.out.println(tableFor2);


        //разобраться с скоростью

        //probability.makeH();

        /*System.out.println(probability.getH());

        probability = probability.makeNewCharacters(2);

        System.out.println(probability);

        probability.sort();

        System.out.println(probability);*/


        /*Double tmp = 0.4;
        tmp *= 0.4;
        System.out.println(tmp);*/


//        String str = probability.makeString(100000);

        //System.out.println(str);

        //probability = new Probability(str);

        //System.out.println(probability);


    }


}
