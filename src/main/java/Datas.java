import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Datas {
    public static void main(String[] args) {
        LocalDate hoje = LocalDate.now();
        System.out.println(hoje); //2023-01-09

        LocalDate olimpiadas = LocalDate.of(2016, Month.JUNE, 5);

        Period period = Period.between(olimpiadas, hoje);
        System.out.println(period.getDays());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        System.out.println(hoje.format(formatter));


        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatterComHoras = DateTimeFormatter.ofPattern("dd/MM/YYYY hh:mm");
        System.out.println(agora.format(formatterComHoras));

        LocalDate localDate = agora.toLocalDate();

    }
}
