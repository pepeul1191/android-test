package utils;

import java.util.HashMap;

public class Constants {
    private static HashMap<String, String> mapa = new HashMap<String, String>();

    public static final String BASE_URL = "http://192.168.1.7:3000/";//"http://192.168.1.3:8888/";

    public static void set(String llave, String valor){
        mapa.put(llave, valor);
    }

    public static String get(String llave){
        if(mapa.containsKey(llave)){
            return mapa.get(llave);
        }else{
            return "null";
        }
    }
}
