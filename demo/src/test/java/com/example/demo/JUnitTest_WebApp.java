/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.example.demo;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pasca
 */
public class JUnitTest_WebApp {

    @Test
    public void testInit() throws NullPointerException, IOException{
        Session test = new Session();
        assertNotNull(test.getUser());
        assertNotNull(test.hld);

        Stock tesStock = new Stock("NIO", 10, 50);
        assertNotNull(tesStock.DTO);
    }

    @Test
    public void testStock() throws IOException{
        Stock testStock = new Stock("NIO", 1, 50);
        assertEquals(testStock.name, "NIO Inc.");
        assertTrue(testStock.purchasePrice == 1);
        assertTrue(testStock.currentPrice > testStock.purchasePrice);
        assertTrue(testStock.shares == 50);
        assertTrue(testStock.mouvment != 0);
    }

    @Test
    public void testStockHistoryData() throws IOException{
        Stock testStock = new Stock("NIO", 1, 50);
        LinkedHashMap<Date, List> history = null;
        history = testStock.getHistory();

        assertNotNull(history);
    }

    @Test
    public void stockNotFound(){
        assertThrows(IOException.class, () -> new Stock("xxxxx",0,0));
    }

    @Test
    public void stockInfo() throws IOException{
        String result = null;
        Stock testStock = new Stock("NIO", 1, 50);
        result = testStock.DTO.getStockInfo();
        assertNotNull(result);
    }

    @Test
    public void calcStockPos() throws IOException{
        Stock testStock = new Stock("NIO", 1, 50);
        assertTrue(testStock.calcPosition() > 50);
    }

    @Test
    public void testAddToPortfolio() throws NullPointerException, IOException{
        Session test = new Session();
        Holdings hld = test.getHoldings();

        assertTrue(hld.holdings.isEmpty() == true);

        Stock testStock = new Stock("NIO", 10, 50);
        hld.add(testStock);

        assertTrue(hld.holdings.isEmpty() == false);
    }

    @Test
    public void testremouvePortfolio() throws IOException{
        Session test = new Session();
        Holdings hld = test.getHoldings();

        Stock testStock = new Stock("NIO", 10, 50);
        hld.add(testStock);
        hld.remove(testStock, 100);

        assertTrue(hld.holdings.isEmpty() == true);
    }

    @Test
    public void testadjustStock() throws NullPointerException, IOException{
        Session test = new Session();
        Holdings hld = test.getHoldings();

        Stock testStock = new Stock("NIO", 10, 50);
        hld.add(testStock);
        hld.adjustStock(testStock, 100, 25);

        assertTrue(hld.getRealROI() == 2500);
        assertTrue(testStock.shares == 25);
        assertTrue(hld.holdings.isEmpty() == false);

        hld.adjustStock(testStock,100,25);
        assertTrue(hld.getRealROI() == 5000);
        assertTrue(hld.holdings.isEmpty() == true);
    }

    @Test
    public void testTrade() throws IOException{
        Session test = new Session();
        Holdings hld = test.getHoldings();

        Stock testStock = new Stock("NIO", 1, 50);
        hld.add(testStock);

        assertTrue(hld.getRealROI() == 0);
        assertTrue(hld.getTotalInvestment() == 50);
        assertTrue(hld.getROI_currency() > 50);
        assertTrue(hld.getROI_percent() > 100);

        hld.remove(testStock, 20);

        assertTrue(hld.getRealROI() == 100);
    }

}
