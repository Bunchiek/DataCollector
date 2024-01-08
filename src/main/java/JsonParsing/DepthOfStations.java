package JsonParsing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class DepthOfStations {

    private String station_name;
    private String depth;

    public static List<DepthOfStations> listOfDepths (List<String> listOfPaths){
        List<DepthOfStations> fullListOfDepth = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for(String s : listOfPaths){
            File file = new File(s);
            List<DepthOfStations> depth = null;
            try {
                depth = objectMapper.readValue(file, new TypeReference<>(){});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            fullListOfDepth.addAll(depth);

        }
        return fullListOfDepth;
    }

}
