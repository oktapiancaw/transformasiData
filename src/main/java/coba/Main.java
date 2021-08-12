package coba;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JSONParser jsonParser = new JSONParser();
        ClassLoader classLoader = Main.class.getClassLoader();
        List<Object> result = new LinkedList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(new File(classLoader.getResource("node.json").getFile())))){
            for(String line; (line = br.readLine()) != null; ) {
                Object obj = jsonParser.parse(line);
                JSONObject jsonObject = (JSONObject) obj;

//                Dapatkan data lalu hapus
                JSONObject statisticList = (JSONObject) jsonObject.get("statistics");
                jsonObject.remove("statistics");

//                Gabungkan datanya
                JSONObject mergedObject = new JSONObject();
                Iterator i1 = jsonObject.keySet().iterator();
                Iterator i2 = statisticList.keySet().iterator();
                String tmp_key;
                while (i1.hasNext()){
                    tmp_key = (String) i1.next();
                    mergedObject.put(tmp_key, jsonObject.get(tmp_key));
                }
                while (i2.hasNext()){
                    tmp_key = (String) i2.next();
                    mergedObject.put(tmp_key, statisticList.get(tmp_key));
                }
                result.add(mergedObject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(result);
    }
}
