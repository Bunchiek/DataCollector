package JsonParsing;

import WebParsing.Lines;
import WebParsing.Stations;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data

public class FullInfoStation {
    private String name;
    private String line;
    private String date;
    private String depth;
    private Boolean hasConnection;

    public FullInfoStation(String name, String line, Boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.hasConnection = hasConnection;
    }


    public FullInfoStation(String name, String line, String date, String depth, Boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
        this.hasConnection = hasConnection;
    }


    public static Map<String, List<FullInfoStation>> fullInfoJsonFile(List<Stations> stations, List<Lines> lines, List<DateOfFoundation> dates, List<DepthOfStations> depths){
        Map<String,List<FullInfoStation>> map = new HashMap<>();
        List<FullInfoStation> list = new ArrayList<>();
        String name = "";
        String nameOfLine = "";
        String dateOfFoundation = "";
        String depthOfStation = "";
        for(Stations s : stations){
            name = s.getName();
            for(Lines l : lines){
                if(s.getNumberOfLine().equals(l.getNumber())){
                    nameOfLine = l.getName();
                }
            }
            for(DateOfFoundation date : dates){
                if(s.getName().equalsIgnoreCase(date.getName())){
                    dateOfFoundation = date.getDate();
                    date.setName("used");
                    break;
                }
            }
            for(DepthOfStations depth : depths){
                if(s.getName().equalsIgnoreCase(depth.getStation_name())){
                    depthOfStation = depth.getDepth();
                    depth.setStation_name("used");
                    break;
                }
            }
            if(dateOfFoundation.isEmpty() || depthOfStation.isEmpty()){
                list.add(new FullInfoStation(name,nameOfLine,s.getHasConnection()));
            }else {
                list.add(new FullInfoStation(name, nameOfLine, dateOfFoundation, depthOfStation, s.getHasConnection()));
                dateOfFoundation = "";
                depthOfStation = "";
            }
        }
        map.put("Stations", list);
        return map;
    }



}
