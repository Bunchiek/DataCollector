import JsonParsing.*;
import WebParsing.WebParser;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        WebParser webParser = new WebParser("https://skillbox-java.github.io/");
        String path = "Data/data";

        //Создание файлов fullInfo.json и map.json
        ListOfStationJSON listOfStationJSON = ListOfStationJSON.getListOfStationJSON(webParser);
        objectMapper.writeValue(Paths.get("map.json").toFile(), listOfStationJSON);
        objectMapper.writeValue(Paths.get("fullInfo.json").toFile(), FullInfoStation.fullInfoJsonFile(
                webParser.getStations(),
                webParser.getLines(),
                DateOfFoundation.listOfDates(SearchFiles.CSVFileParser(SearchFiles.findFile(new File(path)))),
                DepthOfStations.listOfDepths(SearchFiles.JSONFileParser(SearchFiles.findFile(new File(path))))));

    }
}
