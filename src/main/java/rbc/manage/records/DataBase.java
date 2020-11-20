package rbc.manage.records;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DataBase {

    public ConcurrentHashMap<String, HashMap<Long, Integer>> recordTable;

    public DataBase() {
        recordTable = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String, HashMap<Long, Integer>> getRecordTable() {
        return recordTable;
    }
}
