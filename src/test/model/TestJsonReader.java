package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import persistence.JsonReader;

public class TestJsonReader extends TestJson {
    JsonReader testInvalidReader;
    JsonReader testReader1;
    JsonReader testReader2;
    UserFinancesList testFList;


    @BeforeEach
    void setup() {
        testInvalidReader = new JsonReader("./data/testNonExistent.json");
        testReader1 = new JsonReader("./data/testReaderNoUser.json");
        testReader2 = new JsonReader("./data/testReaderAndrew.json");
        testFList = new UserFinancesList("Andrew");
        testFList.addFinances(new Asset("cash", 25.27));
        testFList.addFinances(new Liability("student loans", -120.0));
    }

    @Test
    public void testReaderInvalidFile() {
        try { 
            UserFinancesList fnList = testInvalidReader.read();
            fail("IOExveption expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    public void testReaderNoUserData() {
        try {
            UserFinancesList fnList = testReader1.read();
            assertEquals("User1", fnList.getUser());
            assertEquals(0, fnList.getFinances().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderUserData() {
        try {
            UserFinancesList fnList = testReader2.read();
            assertEquals("Andrew", fnList.getUser());
            assertEquals(2, fnList.getFinances().size());
            checkFinance("cash", 25.27, fnList.getFinances().get(0));
            checkFinance("student loans", -120.0, fnList.getFinances().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
