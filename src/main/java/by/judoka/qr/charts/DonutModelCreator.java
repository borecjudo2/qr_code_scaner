package by.judoka.qr.charts;

import by.judoka.qr.Analyzer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DonutModelCreator {

    public final static String KEY_READ = "KEY_READ";
    public final static String KEY_NOT_READ = "KEY_NOT_READ";
    private final static Integer ROUND = 5;

    private final Analyzer analyzer;

    public DonutModelCreator(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    private Map<String, Integer> initMap(){
        Map<String, Integer> map = new HashMap<>();
        map.put(KEY_READ, 0);
        map.put(KEY_NOT_READ, 0);
        return map;
    }

    private void addToMap(Optional value, Map<String, Integer> map){
        if(value.isPresent()){
            Integer readCounter = map.get(KEY_READ);
            readCounter+=1;
            map.put(KEY_READ, readCounter);
        }else {
            Integer readCounter = map.get(KEY_NOT_READ);
            readCounter+=1;
            map.put(KEY_NOT_READ, readCounter);
        }
    }

    public Map<String, Integer> getValueForDonut(){
        Map<String, Integer> map = initMap();
        for (int i = 0; i < ROUND; i++) {
            addToMap(analyzer.getDataFromStandardQrCode(), map);
        }
        return map;
    }

    public Map<String, Integer> getValueForDonutRotate(){
        Map<String, Integer> map = initMap();
        for (int i = 0; i < ROUND; i++) {
            addToMap(analyzer.getDataFromRotateQrCode(), map);
        }
        return map;
    }

    public Map<String, Integer> getValueForDonutGaussian(){
        Map<String, Integer> map = initMap();
        for (int i = 0; i < ROUND; i++) {
            addToMap(analyzer.getDataFromGaussianQrCode(), map);
        }
        return map;
    }

    public Map<String, Integer> getValueForDonutScale(){
        Map<String, Integer> map = initMap();
        for (int i = 0; i < ROUND; i++) {
            addToMap(analyzer.getDataFromScaleQrCode(), map);
        }
        return map;
    }

    public Map<String, Integer> getValueForDonutBarrel(){
        Map<String, Integer> map = initMap();
        for (int i = 0; i < ROUND; i++) {
            addToMap(analyzer.getDataFromBarrelQrCode(), map);
        }
        return map;
    }

    public Map<String, Integer> getValueForDonutBarrelInvert(){
        Map<String, Integer> map = initMap();
        for (int i = 0; i < ROUND; i++) {
            addToMap(analyzer.getDataFromBarrelInverseQrCode(), map);
        }
        return map;
    }

}
