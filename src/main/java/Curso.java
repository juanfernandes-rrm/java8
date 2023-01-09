import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    }
}