package JsonParsing;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor

public class DateOfFoundation {
    private String name;
    private String date;
    //Возвращаем список объектов DateOfFoundation, принимаем список путей CSV файлов
    public static List<DateOfFoundation> listOfDates(List<String> listOfPaths){
        List<DateOfFoundation> fullListOfData = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        for(String s1 : listOfPaths) {
            try {
                lines = Files.readAllLines(Paths.get(s1));
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String s : lines.subList(1, lines.size())) {
                String[] fragments = s.split(",");
                fullListOfData.add(new DateOfFoundation(fragments[0], fragments[1]));
            }
        }
        return fullListOfData;
    }
}
