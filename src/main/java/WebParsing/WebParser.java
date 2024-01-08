package WebParsing;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;

public class WebParser {
    private String url;
    public WebParser(String url) {
        this.url = url;
    }
    private Document getDoc() throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc;
    }
    //Метод возврашает список линий
    public List<Lines> getLines(){
        List<Lines> list = new ArrayList<>();
        try {
            Elements lines = getDoc().select("span[data-line]");
            for (Element line : lines){
                list.add(new Lines(line.text(),line.attr("data-line")));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    //Метод возврашает список станций
    public List<Stations> getStations(){
        List<Stations> list = new ArrayList<>();
        try {
            Elements lines = getDoc().select("span");
            String numOflines = "";
            String name = "";
            for (Element line : lines){
                if(!line.attr("data-line").isEmpty()){
                    numOflines = line.attr("data-line");
                }
                if(!line.select("span[class=\"name\"]").text().isEmpty()){
                    if(!name.isEmpty()){
                        list.add(new Stations(name,numOflines,false));
                    }
                    name = line.select("span[class=\"name\"]").text();
                }
                if(!line.attr("title").isEmpty() && !name.isEmpty() && !numOflines.isEmpty()){
                    list.add(new Stations(name,numOflines,true));
                    name="";
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
