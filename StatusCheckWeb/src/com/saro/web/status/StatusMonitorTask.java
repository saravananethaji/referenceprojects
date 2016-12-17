package com.saro.web.status;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TimerTask;

public class StatusMonitorTask extends TimerTask {
	private String fileToBeMonitored = null;
	private static final String STATUS_READ_FAIL="Status File Read Failed";

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// task to do

		System.out.println("Monitor task is getting executed");

		String statusContent = performStatusCheck();
		StatusWebSocketServer.broadcastMsg(statusContent);

	}

	private String performStatusCheck() {
		if (fileToBeMonitored == null) {
			String docroot = System.getProperty("APP_DOC_ROOT");
			if (docroot == null)
				docroot = "c:\\temp";
			String statusFile = System.getProperty("STATUS_FILE");
			if (statusFile == null)
				statusFile = "status.txt";

			fileToBeMonitored = docroot + File.separator + statusFile;
		}

		String statusContent = readFile(fileToBeMonitored);
		return statusContent;
	}

	private static String readFile(String file) {
		BufferedReader reader;
		try {

			reader = new BufferedReader(new FileReader(file));
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			String ls = System.getProperty("line.separator");

			try {
				while ((line = reader.readLine()) != null) {
					stringBuilder.append(line);
					stringBuilder.append(ls);
				}

				return stringBuilder.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return STATUS_READ_FAIL;

	}
}