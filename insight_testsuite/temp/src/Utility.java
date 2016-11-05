import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Utility {
	public static List<Transaction> loadFile(String filePath) {
		String csvFile = filePath;
		BufferedReader br = null;
		String line = "";
		int counter = 150000; // my local machine can only handle this number of
								// record to build the Friend network
		List<Transaction> trans = new ArrayList<Transaction>();
		try {
			br = new BufferedReader(new FileReader(csvFile));
			br.readLine();// skip header
			while ((line = br.readLine()) != null && counter > 0) {
				// use comma as separator
				String[] item = cleanData(line);
				if (item == null) {
					continue;
				}
				Transaction tr = new Transaction(item[0], item[1], item[2],
						item[3], item[4]);
				trans.add(tr);
				counter--;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return trans;
	}

	// clean data so that if follows schema (5 items per line)
	private static String[] cleanData(String line) {
		String cvsSplitBy = ",";
		int MAX_MESSAGE_LENGTH = 200;
		int COL_NUM = 5;
		String fmt = "yyyy-MM-dd HH:mm:ss";
		DateTimeFormatter format = DateTimeFormatter.ofPattern(fmt);

		String[] raw = line.split(cvsSplitBy);
		String[] result = new String[5];
		// check if enough comma
		if (raw.length < COL_NUM) {
			System.out.print("found invalid data: ");
			System.out.println(line);
			return null;
		}
		if (raw.length >= COL_NUM) {
			// check time-stamp format
			try {
				LocalDate localDate = LocalDate.parse(raw[0], format);
			} catch (DateTimeParseException e) {
				System.out.println("Date Time Exception was thrown");
				return null;
			}
			result[0] = raw[0];// time stamp
			result[1] = raw[1];// id
			result[2] = raw[2];// id
			result[3] = raw[3];// double number
			result[4] = (raw[4].length() > MAX_MESSAGE_LENGTH) ? raw[4]
					.substring(0, MAX_MESSAGE_LENGTH) : raw[4];

		}
		return result;
	}

	public static void writeFile(String path, String[] dataTotal)
			throws IOException {
		File fout = new File(path);
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		for (String data : dataTotal) {
			bw.write(data);
			bw.newLine();
		}
		bw.close();
	}

}
