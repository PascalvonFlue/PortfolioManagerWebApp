/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.grid.Grid;


import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.Cursor;
import com.vaadin.flow.component.charts.model.DataGrouping;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.charts.model.OhlcItem;
import com.vaadin.flow.component.charts.model.PlotOptionsOhlc;
import com.vaadin.flow.component.charts.model.PlotOptionsPie;
import com.vaadin.flow.component.charts.model.RangeSelector;
import com.vaadin.flow.component.charts.model.Select;
import com.vaadin.flow.component.charts.model.TimeUnit;
import com.vaadin.flow.component.charts.model.TimeUnitMultiples;
import com.vaadin.flow.component.charts.model.Tooltip;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.Registration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pasca
 */


@Route("MainView")
public class MainView extends VerticalLayout{

    public MainView() throws IOException{
        TabSheet tabSheet = new TabSheet();

        this.title = new H2();

        this.debugLable = new Label();
        this.debugLable.setText("Log: ");

        this.publiSession = (Session)VaadinSession.getCurrent().getAttribute("SESSION");
        if(this.publiSession == null){
            setDebugLable("No User in Sessionmemmory");
            this.publiSession = new Session();
        }
        this.usr = this.publiSession.getUser();
        this.hld = this.publiSession.getHoldings();

        /********************************** My Stats Tab *************************************************************/
        this.stockGrid = new Grid<>(Stock.class, false);
        this.stockGrid.addColumn(Stock::getSymbol).setHeader("Symbol");
        this.stockGrid.addColumn(Stock::getCurrentPrice).setHeader("Market Price [USD]");
        this.stockGrid.addColumn(Stock::getMouvment).setHeader("Mouvment [%]");
        this.stockGrid.addColumn(Stock::getROI).setHeader("ROI [%]");
        List<Stock> currentHoldings = this.hld.holdings;
        this.stockGrid.setItems(currentHoldings);
        this.stockGrid.setSelectionMode(Grid.SelectionMode.SINGLE);

        this.pieChart = new Chart(ChartType.PIE);
        Configuration conf = pieChart.getConfiguration();
        //conf.setTitle("Browser market shares in January, 2018");
        Tooltip tooltip = new Tooltip();
        tooltip.setValueDecimals(1);
        conf.setTooltip(tooltip);
        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(false);
        conf.setPlotOptions(plotOptions);
        this.pieSeries = new DataSeries();
        conf.setSeries(this.pieSeries);
        pieChart.setVisibilityTogglingDisabled(true);
        pieChart.setHeight("200%");
        pieChart.setWidth("200%");

        HorizontalLayout overviewLayout = new HorizontalLayout();
        overviewLayout.add(pieChart);
        overviewLayout.setWidth("100%");

        VerticalLayout statsLayout = new VerticalLayout();
        statsLayout.add(overviewLayout,stockGrid);
        statsLayout.setWidth("100%");

        tabSheet.add("My Stats",
                statsLayout);
        /********************************** Stock Tab *************************************************************/
        this.stockGraph = new Chart(ChartType.OHLC);
        this.stockChartconfiguration = this.stockGraph.getConfiguration();
        this.stockSeries = new DataSeries();
      /*  stockChartconfiguration.getTitle().setText("");

        this.stockSeries = new DataSeries();
        PlotOptionsOhlc plotOptionsOhlc = new PlotOptionsOhlc();
        DataGrouping grouping = new DataGrouping();
        grouping.addUnit(new TimeUnitMultiples(TimeUnit.WEEK, 1));
        grouping.addUnit(new TimeUnitMultiples(TimeUnit.MONTH, 1, 2, 3, 4, 6));
        plotOptionsOhlc.setDataGrouping(grouping);
        this.stockSeries.setPlotOptions(plotOptionsOhlc);

        this.stockChartconfiguration.setSeries(this.stockSeries);

        RangeSelector rangeSelector = new RangeSelector();
        rangeSelector.setSelected(1);
        this.stockChartconfiguration.setRangeSelector(rangeSelector);

        stockGraph.setTimeline(true);*/

        this.stockInfo = new TextArea();
        this.stockInfo.setValue(" ");
        this.stockInfo.setReadOnly(true);
        this.stockInfo.setWidth("100%");

        this.stockholdings = new TextArea();
        stockholdings.setValue(" ");
        stockholdings.setReadOnly(true);
        stockholdings.setWidth("100%");

        HorizontalLayout stockdetails = new HorizontalLayout();
        stockdetails.add(stockInfo, stockholdings);
        stockdetails.setWidth("100%");

        VerticalLayout stockLayout = new VerticalLayout();
        stockLayout.add(stockGraph,stockdetails);
        stockLayout.setWidthFull();

        stockGrid.addItemClickListener((var event) -> {
            try {
                stockLayout.remove(stockGraph,stockdetails);
                Stock selectedStock = event.getItem();

                this.stockGraph = new Chart(ChartType.OHLC);
                this.stockChartconfiguration = this.stockGraph.getConfiguration();
                stockChartconfiguration.getTitle().setText("");


                PlotOptionsOhlc plotOptionsOhlc = new PlotOptionsOhlc();
                DataGrouping grouping = new DataGrouping();
                grouping.addUnit(new TimeUnitMultiples(TimeUnit.WEEK, 1));
                grouping.addUnit(new TimeUnitMultiples(TimeUnit.MONTH, 1, 2, 3, 4, 6));
                plotOptionsOhlc.setDataGrouping(grouping);
                this.stockSeries.setPlotOptions(plotOptionsOhlc);

                this.stockChartconfiguration.setSeries(this.stockSeries);

                RangeSelector rangeSelector = new RangeSelector();
                rangeSelector.setSelected(1);
                this.stockChartconfiguration.setRangeSelector(rangeSelector);

                stockGraph.setTimeline(true);


                if(selectedStock != null){
                    this.stockSeries.clear();
                    setDebugLable("Loading " + selectedStock.symbol + " Chart...");
                    selectedStock.update();
                    LinkedHashMap<Date, List> history = selectedStock.getHistory();
                    Set<Date> keys = history.keySet();
                    for(Date date : keys){
                        List data = history.get(date);
                        OhlcItem item = new OhlcItem();
                        item.setX(date);
                        item.setLow((Double)data.get(1));
                        item.setHigh((Double)data.get(0));
                        item.setClose((Double)data.get(3));
                        item.setOpen((Double)data.get(2));
                        this.stockSeries.add(item);
                    }
                    this.stockChartconfiguration.getTitle().setText(selectedStock.name + " ("  + selectedStock.symbol + ")");
                    this.stockGraph.drawChart(true);

                    this.stockInfo.setValue(selectedStock.DTO.getStockInfo());
                    this.stockholdings.setValue("Stock: " + selectedStock.getSymbol() + "\n" +
                                                "Current value: " + selectedStock.currentPrice + " USD\n" +
                                                "Amount of shares: " + selectedStock.shares + "\n\n" +
                                                "Purchase value: " + selectedStock.purchasePrice * selectedStock.shares+ " USD\n" +
                                                "Current value: " + selectedStock.currentPrice * selectedStock.shares + " USD\n" +
                                                "ROI: " + selectedStock.calcROI_curreny() + "USD/" + selectedStock.calcROI_percent() + "%");

                    stockLayout.add(stockGraph, stockdetails);
                    tabSheet.getTabAt(1).setEnabled(true);
                    tabSheet.setSelectedTab(tabSheet.getTabAt(1));
                    setDebugLable(selectedStock.symbol + " Chart loaded.");
                }
            } catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex){
                setDebugLable(ex.toString());
            }
        });


        tabSheet.add("Stock",
                stockLayout);
        tabSheet.getTabAt(1).setEnabled(false);

        /********************************** Trade Tab *************************************************************/
        TextField buyStockSymbolTextField = new TextField();
        buyStockSymbolTextField.setLabel("Stock Ticker");
        buyStockSymbolTextField.setPlaceholder("Stock Ticker");

        TextField buyStockPriceTextField = new TextField();
        buyStockPriceTextField.setLabel("Purchase Price");
        buyStockPriceTextField.setPlaceholder("Purchase Price");

        Checkbox buyStockCurPrcCheckbox = new Checkbox();
        buyStockCurPrcCheckbox.setLabel("Use current price");

        TextField buyStockAmountTextField = new TextField();
        buyStockAmountTextField.setLabel("Amount of shares");
        buyStockAmountTextField.setPlaceholder("Amount of shares");

        DatePicker buyStockDatePicker = new DatePicker();
        buyStockDatePicker.setLabel("Date of purchase");

        Checkbox buyStockTodayCheckbox = new Checkbox();
        buyStockTodayCheckbox.setLabel("Use current date");

        Button buyStockButton = new Button("Add to Portfolio");

        buyStockButton.addClickListener(clickEvent -> {
            try {
                String stockSymbol = buyStockSymbolTextField.getValue();
                float purchasePrice = Float.valueOf(buyStockPriceTextField.getValue());
                int shares = Integer.valueOf(buyStockAmountTextField.getValue());
                Stock newStock = new Stock(stockSymbol,purchasePrice,shares);
                this.hld.add(newStock);
                update();
                setDebugLable(purchasePrice * shares + "UDS " + newStock.getSymbol() + " Postion added to Portfolio.");
                tabSheet.setSelectedTab(tabSheet.getTabAt(0));
            } catch (IOException ex) {
                setDebugLable("Stock not found!");
            } catch (NullPointerException ex){
                setDebugLable("Stock not found!");
            } catch(NumberFormatException ex){
                setDebugLable("Please check your Inputs!");
            }
        });

        buyStockCurPrcCheckbox.addValueChangeListener(clickEvent ->{
            if (buyStockCurPrcCheckbox.getValue()){
                buyStockPriceTextField.setValue("0");
                buyStockPriceTextField.setEnabled(false);
            } else {
                buyStockPriceTextField.setEnabled(true);
                buyStockPriceTextField.setValue("");
            }
        });

        buyStockTodayCheckbox.addValueChangeListener(clickEvent ->{
            if(buyStockTodayCheckbox.getValue()){
                buyStockDatePicker.setValue(LocalDate.now());
                buyStockDatePicker.setEnabled(false);
            } else {
                buyStockDatePicker.setEnabled(true);
            }
        });

        VerticalLayout buysection = new VerticalLayout();
        buysection.add(buyStockSymbolTextField,
                        buyStockPriceTextField,
                        buyStockCurPrcCheckbox,
                        buyStockAmountTextField,
                        buyStockDatePicker,
                        buyStockTodayCheckbox,
                        buyStockButton);

        /**/

        this.portfolioComboBox = new ComboBox<>("Select Stock");
        this.portfolioComboBox.setItems(this.hld.holdings);
        this.portfolioComboBox.setItemLabelGenerator(Stock::getSymbol);

        TextField sellStockPriceTextField = new TextField();
        sellStockPriceTextField.setLabel("Sell Price");
        sellStockPriceTextField.setPlaceholder("Sell Price");

        Checkbox sellStockCurPrcCheckbox = new Checkbox();
        sellStockCurPrcCheckbox.setLabel("Use current price");

        TextField sellStockAmountTextField = new TextField();
        sellStockAmountTextField.setLabel("Amount of shares");
        sellStockAmountTextField.setPlaceholder("Amount of shares");

        DatePicker sellStockDatePicker = new DatePicker();
        sellStockDatePicker.setLabel("Date of purchase");

        Checkbox sellStockTodayCheckbox = new Checkbox();
        sellStockTodayCheckbox.setLabel("Use current date");

        Button sellStockButton = new Button("Remouve From Portfolio");
        sellStockButton.setEnabled(false);

        sellStockButton.addClickListener(clickEvent -> {
            try {
                Stock selectedStock = portfolioComboBox.getValue();
                float price = Float.valueOf(sellStockPriceTextField.getValue());
                int amount = Integer.valueOf(sellStockAmountTextField.getValue());
                if(amount > selectedStock.shares){amount = selectedStock.shares;}

                if(selectedStock.shares == amount){
                    setDebugLable(selectedStock.symbol + " liquidated for " + price*amount + "USD");
                } else {
                    setDebugLable("Position " + selectedStock.symbol + " reduced by " + price*amount + "USD");
                }

                this.hld.adjustStock(selectedStock, price, amount);

                update();
                tabSheet.setSelectedTab(tabSheet.getTabAt(0));
            } catch (IOException ex) {
                setDebugLable("IOException");
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            } catch(NumberFormatException ex){
                setDebugLable("Please check your Inputs!");
            } catch(Exception ex){
                setDebugLable(ex.getMessage());
            }
        });

        sellStockCurPrcCheckbox.addValueChangeListener(clickEvent ->{
            if (sellStockCurPrcCheckbox.getValue()){
                sellStockPriceTextField.setValue("0");
                sellStockPriceTextField.setEnabled(false);
            } else {
                sellStockPriceTextField.setEnabled(true);
                sellStockPriceTextField.setValue("");
            }
        });

        sellStockTodayCheckbox.addValueChangeListener(clickEvent ->{
            if(sellStockTodayCheckbox.getValue()){
                sellStockDatePicker.setValue(LocalDate.now());
                sellStockDatePicker.setEnabled(false);
            } else {
                sellStockDatePicker.setEnabled(true);
            }
        });

        portfolioComboBox.addValueChangeListener(event ->{
            Stock stock = portfolioComboBox.getValue();
            if(stock != null){
                sellStockButton.setEnabled(true);
            }
        });

        VerticalLayout sellsection = new VerticalLayout();
        sellsection.add(portfolioComboBox,
                        sellStockPriceTextField,
                        sellStockCurPrcCheckbox,
                        sellStockAmountTextField,
                        sellStockDatePicker,
                        sellStockTodayCheckbox,
                        sellStockButton);

        HorizontalLayout tradeLayout = new HorizontalLayout();
        tradeLayout.setWidthFull();

        tradeLayout.add(buysection, new Divider(), sellsection);
        tradeLayout.setAlignItems(Alignment.CENTER);

        tabSheet.add("Trade",
                tradeLayout);

        VerticalLayout profileLayout = new VerticalLayout();
        profileLayout.setWidth("20%");

        this.myStatsArea = new TextArea();
        myStatsArea.setLabel("MY Stats");
        myStatsArea.setWidth("100%");
        myStatsArea.setReadOnly(true);

        Image image = new Image(usr.getProfilePicture(), "Profilepicture");
        image.setWidth("100%");

        Button settingButton = new Button("Settings");
        settingButton.setWidth("100%");

        settingButton.addClickListener(clickEvent -> {
            VaadinSession.getCurrent().setAttribute("SESSION", this.publiSession);
            UI.getCurrent().navigate(MainView.class);
            UI.getCurrent().navigate(SettingsView.class);
        });

        Button logoutButton = new Button("Log Out");
        logoutButton.setWidth("100%");

        logoutButton.addClickListener(clickEvent -> {
            UI.getCurrent().navigate(LoginView.class);
        });

        profileLayout.add(image,
                this.myStatsArea,
                settingButton,
                logoutButton);


        HorizontalLayout completeLayout = new HorizontalLayout();
        completeLayout.add(profileLayout, tabSheet);

        add(this.title,completeLayout);
        tabSheet.setSizeFull();

        tabSheet.addSelectedChangeListener(event ->{
            int tab = tabSheet.getIndexOf(event.getSelectedTab());
            if(tab != 1){
                tabSheet.getTabAt(1).setEnabled(false);
            }
        });

        add(debugLable);

        update();
    }

    Session publiSession = null;
    User usr = null;
    Holdings hld = null;
    Grid<Stock> stockGrid = null;

    H2 title = null;

    TextArea myStatsArea = null;
    Chart pieChart = null;
    DataSeries pieSeries = null;

    Chart stockGraph = null;
    DataSeries stockSeries = null;
    Configuration stockChartconfiguration = null;

    TextArea stockInfo = null;

    TextArea stockholdings = null;

    ComboBox<Stock> portfolioComboBox = null;

    Label debugLable = null;

    public class Divider extends Span {
        public Divider() {
            getStyle().set("background-image", "linear-gradient(135deg, #777 , rgba(0, 0, 0, 0))");
            getStyle().set("flex", "0 0 2px");
            getStyle().set("align-self", "stretch");
        }
    }

    public void update() throws IOException{
        this.hld.updatePortfolio();

        if(this.pieSeries.size() != 0){
            this.pieSeries.clear();
        }

        for(Stock stock:this.hld.holdings){
            this.pieSeries.add(new DataSeriesItem(stock.getSymbol(),stock.calcPosition()));
        }
        this.pieChart.drawChart();

        portfolioComboBox.clear();
        this.portfolioComboBox.setItems(this.hld.holdings);
        this.portfolioComboBox.setItemLabelGenerator(Stock::getSymbol);

        this.myStatsArea.setValue(this.usr.getUsername() + "\n" +
                "Total Investments: " +
                this.hld.getTotalInvestment() + "\n" +
                "Current Value: " +
                this.hld.getHoldingsValue() + "\n" +
                "ROI: " +
                this.hld.getROI_currency() + "USD/" + this.hld.getROI_percent() + "%\n"
        );

        this.title.setText("Welcome back " + this.usr.getFistname() + " " + this.usr.getLastname());
    }

    public void setDebugLable(String text){
        this.debugLable.setText("Log: " + text);
    }
}
