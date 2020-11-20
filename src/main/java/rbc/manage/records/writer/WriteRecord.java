package rbc.manage.records.writer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

import rbc.manage.records.DataBase;
import rbc.manage.records.Exception.InvalidEntryException;
import rbc.manage.records.entity.EntryRecord;

@RestController
public class WriteRecord {

    private final DataBase dataBase;

    public WriteRecord(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @PostMapping(path = "/records/enterRecord", consumes = "application/json")
    public void writeSingleRecord(@RequestBody EntryRecord entryRecord) {
        enterRecord(entryRecord);
    }

    @PostMapping(path = "/records/enterBulkRecords", consumes = "application/json")
    public void writeMultipleRecords(@RequestBody List<EntryRecord> entryRecords) {
        for (EntryRecord entryRecord : entryRecords) {
            enterRecordsList(entryRecord);
        }
    }

    private void enterRecord(EntryRecord record) {
        if (validRecord(record)) {
            if (dataBase.getRecordTable().containsKey(record.stockTicker)) {
                dataBase.getRecordTable().get(record.stockTicker).put(record.epochMs, record.price);
            } else {
                insertEntryInDB(record);
            }
        } else {
            throw new InvalidEntryException("Invalid Entry for the record " +
                    "stockTicker: "+record.stockTicker +
                    ", epochMs: "+record.epochMs +
                    ", price: "+record.price );
        }
    }

    private void enterRecordsList(EntryRecord record) {
        try {
            enterRecord(record);
        } catch (Exception e) {
            throw new InvalidEntryException("Invalid Entry in the records list");
        }
    }

    private void insertEntryInDB(EntryRecord entryRecord) {
        dataBase.getRecordTable().put(entryRecord.stockTicker,
                new HashMap<Long, Integer>() {{
                    put(entryRecord.epochMs, entryRecord.price);
                }});
    }

    private Boolean validRecord(EntryRecord record) {
        if (record.getEpochMs() == null) {
            return false;
        }
        return record.getPrice() != null;
    }
}

