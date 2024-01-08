package JsonParsing;

import WebParsing.Lines;
import WebParsing.Stations;
import WebParsing.WebParser;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
@AllArgsConstructor

public class ListOfStationJSON {
    private Map<String,List<String>> stations;
    private List<Lines> lines;
    //Метод для создания объекта ListOfStationJSON
    public static ListOfStationJSON getListOfStationJSON(WebParser webParser) {
        List<Stations> stations = webParser.getStations();
        Map<String,List<String>> map = new HashMap<>();
        for(Stations s : stations){
            List<String> list = new ArrayList<>();
            for(Stations t : stations){
                if(s.getNumberOfLine().equals(t.getNumberOfLine())){
                    list.add(t.getName());
                }
            }
            map.put(s.getNumberOfLine(), list);
        }
        return new ListOfStationJSON(map, webParser.getLines()); //возвращаем Map<String,List<String>>, List<Lines>
    }
}
