import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import rbc.manage.records.DataBase;
import rbc.manage.records.entity.EntryRecord;
import rbc.manage.records.reader.ReadRecord;

@WebMvcTest(controllers = ReadRecord.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebAppConfiguration
@ContextConfiguration(classes = ReadRecord.class)
public class ReadRecordTest {

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    DataBase dataBase;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void ReturnSuccessfulResponseForAValidStockTicker() throws Exception {

        //Given
        EntryRecord mockEntryRecord = new EntryRecord();
        mockEntryRecord.setStockTicker("st");
        mockEntryRecord.setEpochMs(100000L);
        mockEntryRecord.setPrice(50);

        ConcurrentHashMap<String, HashMap<Long, Integer>> mockRecord = new ConcurrentHashMap<>();
        mockRecord.put(mockEntryRecord.stockTicker, new HashMap<>() {{
            put(mockEntryRecord.epochMs, mockEntryRecord.price);
        }});
        Mockito.when(dataBase.getRecordTable()).thenReturn(mockRecord);

        // When
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/records/st");

        // Then
        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful());
    }

    /*@Test
    public void ReturnEmptyForAnInValidStockTicker() {
    }

    @Test
    public void ReturnEmptyForWhenStockTickerDoesNotExistInTable() {
    }

    @Test
    public void ReturnEmptyForWhenStockTickerIsNull() {
    }

    @Test
    public void Return404ForWhenStockTickerIsNotMentioned() {
    }*/
}
