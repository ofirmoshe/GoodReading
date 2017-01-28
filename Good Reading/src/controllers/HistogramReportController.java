package controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import boundary.ClientUI;
import client.Client;
import common.Message;
import i_book.Book;
import i_book.User_Book;
import i_book.Views_Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * 
 * @author Ofir
 * Histogram Report Controller: produces a views and downloads report to a specific book, by dates.
 */
public class HistogramReportController extends SystemController {
	@FXML
	private AnchorPane anchor;
	@FXML
	private DatePicker SinceDate;
	@FXML
	private DatePicker UntilDate;
	public static Book book;
	private Views_Date[] views;
	private User_Book[] downloads;
	private static Axis<String> xAxis;
	private static NumberAxis yAxis;
	private static BarChart<String, Number> bc;
	private Date startDate;
	private Date endDate;

	/** 
	 * 
	 *  This method initializes the controller. 
	 *  On default, the report is produced over the last 7 days.
	 *  It sets new endDate variable to today's date, and startDate to the date 7 days ago, also displays them at the DatePickers.
	 *  Then, it sends a message to the server, asking it to send all of the views by date and downloads records for this book.
	 *  
	 */
	
	public void initialize() {
		super.initialize();
		endDate = new Date();
		UntilDate.setValue(endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.add(Calendar.DATE, -7);
		startDate = cal.getTime();
		SinceDate.setValue(startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		try {
			Client.instance.sendToServer(new Message("histogram report", 1, book.getID()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 
	 * This method sets the histogram with the book views and downloads data, between the dates: startDate and endDate, the values of the DatePickers.
	 * @param xAxis the picked dates.
	 * @param yAxis the views or downloads count, per date.
	 * @param bc the BarChart.
	 * 
	 * For each date in xAxis, it matches:
	 * 1. View count in yAxis.
	 * 2. Download count in yAxis.
	 * 
	 * If there are not any views or downloads for a book in a date, it displays 0 count.
	 * 
	 */
	public void setHistogram() {
		if (!anchor.getChildren().isEmpty())
			anchor.getChildren().remove(bc);
		startDate = java.sql.Date.valueOf(SinceDate.getValue());
		endDate = java.sql.Date.valueOf(UntilDate.getValue());
		xAxis = new CategoryAxis();
		yAxis = new NumberAxis();
		int count = 0;
		bc = new BarChart<String, Number>(xAxis, yAxis);
		bc.setTitle("Book Views & Downloads By Date Report");
		xAxis.setLabel("Date");
		yAxis.setLabel("Count");
		bc.setLayoutY(50);
		bc.setPrefSize(900, 400);
		XYChart.Series series1 = new XYChart.Series();
		XYChart.Series series2 = new XYChart.Series();
		series1.setName("Views");
		long days = endDate.getTime() - startDate.getTime();
		int d = Math.abs((int) TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS));
		for (int i = d; i >= 0; i--) {
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String date = formatter.format(endDate);
			series1.getData().add(new XYChart.Data(date, 0));
			series2.getData().add(new XYChart.Data(date, 0));
			for (int j = 0; j < views.length; j++) {
				String cdate = formatter.format(views[j].getDate());
				if (date.equals(cdate))
					series1.getData().add(new XYChart.Data(date, views[j].getViewCount()));
			}
			for (int j = 0; j < downloads.length; j++) {
				String cdate = formatter.format(downloads[j].getpDate());
				if (date.equals(cdate))
					count++;
			}
			series2.getData().add(new XYChart.Data(date, count));
			count = 0;
			Calendar cal = Calendar.getInstance();
			cal.setTime(endDate);
			cal.add(Calendar.DATE, -1);
			endDate = cal.getTime();
		}

		series2.setName("Downloads");
		bc.getData().addAll(series1, series2);
		anchor.getChildren().add(bc);
	}
	/**
	 * This method implements AbstractController's method. It handles message
	 * from server.
	 * 
	 * @param msg
	 *            case 1: 
	 *				The message is the views by date array and the downloads array. 
	 *				This case just initialize the variables views, downloads with the data brought by the server.
	 */        
	
	@Override
	public void handleMessage(Message msg) {
		switch (msg.getFunc()) {
		case 1:
			Object[] o = (Object[]) msg.getMsg();
			views = (Views_Date[]) o[0];
			downloads = (User_Book[]) o[1];
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					setHistogram();
				}
			});
			break;
		}
	}

}