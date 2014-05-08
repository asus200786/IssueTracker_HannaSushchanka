package by.epam.epamlab.utilities;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import by.epam.epamlab.constants.Constants;

public class DateHelper {

	private DateHelper() {
		super();
	}

	public static Date parseDate(String strDate) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				Constants.OUTPUT_DATE_FORMAT);
		return new java.sql.Date(simpleDateFormat.parse(strDate).getTime());
	}
}
