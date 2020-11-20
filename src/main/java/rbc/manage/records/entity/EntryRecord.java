package rbc.manage.records.entity;

import lombok.Data;

@Data
public class EntryRecord {
    public String stockTicker;
    public Long epochMs;
    public Integer price;
}
