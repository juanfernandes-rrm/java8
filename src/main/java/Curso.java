import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Curso {
    private String nome;
    private int alunos;

    public Curso(String nome, int alunos) {
        this.nome = nome;
        this.alunos = alunos;
    }

    public String getNome() {
        return nome;
    }

    public int getAlunos() {
        return alunos;
    }
}

class ExemploCursos{
    public static void main(String[] args) {
        List<Curso> cursos = new ArrayList<>();
        cursos.add(new Curso("Python",45));
        cursos.add(new Curso("Java",120));
        cursos.add(new Curso("JS",25));
        cursos.add(new Curso("HTML",132));

        cursos.sort(Comparator.comparing(Curso::getAlunos));
        cursos.forEach(c -> System.out.println(c.getNome()));

        //Stream
        cursos.stream()
                .filter(c -> c.getAlunos() >= 100) //filtra cursos com numero de alunos >= 100
                .map(Curso::getAlunos)//faz um map do numero de alunos
                .forEach(System.out::println); //imprime o numero de alunos

        //IntStream
        int sum = cursos.stream()
                .filter(c -> c.getAlunos() >= 100)
                .mapToInt(Curso::getAlunos)//retorna um IntStream
                .sum();

        cursos.stream()
                .filter(c -> c.getAlunos() >= 100)
                .findAny(). //retorna um Optional
                ifPresent(curso -> System.out.println(curso.getNome()));

        cursos = cursos.stream()
                .filter(c -> c.getAlunos() >= 100)
                .collect(Collectors.toList()); //Retorna uma lista

        Map<String, Integer> collect = cursos.stream()
                .filter(c -> c.getAlunos() >= 100)
                .collect(Collectors.toMap(Curso::getNome, Curso::getAlunos));

        cursos.stream()
                .filter(c -> c.getAlunos() >= 100)
                .collect(Collectors.toMap(Curso::getNome, Curso::getAlunos))
                .forEach((nome, alunos) -> System.out.println("Curso "+nome+" tem "+alunos+" alunos"));//biconsumer
    }
}