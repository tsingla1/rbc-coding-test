package rbc.manage.records.reader;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import rbc.manage.records.DataBase;

@RestController
public class ReadRecord {

    private final DataBase dataBase;

    public ReadRecord(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @GetMapping("/records/{stockTicker}")
    @ResponseBody
    public Map<Long, Integer> readByStockTicker(@PathVariable(value = "stockTicker") String stockTicker) {
        if (stockTicker == null) {
            return new HashMap<>();
        }
        if (dataBase.getRecordTable().containsKey(stockTicker)) {
            return dataBase.getRecordTable().get(stockTicker);
        }
        return new HashMap<>();
    }
}



