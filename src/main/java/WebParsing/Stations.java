package WebParsing;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class Stations {
    String name;
    String numberOfLine;
    Boolean hasConnection;
}
