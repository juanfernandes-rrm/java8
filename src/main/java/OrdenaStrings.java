import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class OrdenaStrings {
    public static void main(String[] args) {

        List<String> palavras = new ArrayList<>();
        palavras.add("alura online");
        palavras.add("editora casa do codigo");
        palavras.add("caelum");

        //Comparator<String> comparador = new ComparaPorTamanho();
        //Collections.sort(palavras, comparador);

        palavras.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));

        //Fuctional Interface
        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        //Comparator Factory
        Comparator<String> comparator = Comparator.comparing(function);

        palavras.sort(comparator);

        //lambda
        palavras.sort(Comparator.comparing(s -> s.length()));
        //lambda com method references
        palavras.sort(Comparator.comparing(String::length));

        System.out.println(palavras);

        //for convencional
//        for (String p: palavras) {
//            System.out.println(p);
//        }

        //forEach com classe anônima
//        palavras.forEach(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        });


        //lambda
        //palavras.forEach((String s) -> System.out.println(s));
        palavras.forEach(s -> System.out.println(s));
        //lambda com method references
        palavras.forEach(System.out::println);
    }
}

//Definindo um novo padrão de comparação
class ComparaPorTamanho implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        if(o1.length() < o2.length())
            return -1;
        if(o1.length() > o2.length())
            return 1;
        return 0;
    }
}
