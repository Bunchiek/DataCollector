import java.io.File;
import java.util.ArrayList;
import java.util.List;
public  class SearchFiles {
    //Рекурсивный метод возвращающий строку с разделителями "*"
    public static String findFile(File folder){
        String regex = "^.*\\.(json|csv)$";
        if(folder.isFile()){
            if(folder.toString().matches(regex)){
                return folder.toString() + "*";
            }
            return "";
        }
        StringBuilder sum = new StringBuilder();
        File[]files = folder.listFiles();
        for(File s : files){
            sum.append(findFile(s));
        }
        return sum.toString();
    }
    //возвращаем список из строк содержащие ссылки на JSON файлы
    public static List<String> JSONFileParser(String files){
        List<String> list = new ArrayList<>();
        String[] data = files.split("\\*");
        String regex = "^.*\\.(json)$";
        for(String s : data){
            if(s.matches(regex)){
                list.add(s);
            }
        }
        return list;
    }
    //возвращаем список из строк содержащие ссылки на CSV файлы
    public static List<String> CSVFileParser(String files){
        List<String> list = new ArrayList<>();
        String[] data = files.split("\\*");
        String regex = "^.*\\.(csv)$";
        for(String s : data){
            if(s.matches(regex)){
                list.add(s);
            }
        }
        return list;
    }

}
