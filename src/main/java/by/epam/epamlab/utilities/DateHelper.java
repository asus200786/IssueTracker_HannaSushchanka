package by.epam.epamlab.utilities;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

import by.epam.epamlab.constants.Constants;
import by.epam.epamlab.model.beans.issues.Issue;

public class DateHelper implements Comparator<Issue> {

	private DateHelper() {
		super();
	}

	public static Date parseDate(String strDate) throws ParseException {
		DateFormat simpleDateFormat = new SimpleDateFormat(
				Constants.OUTPUT_DATE_FORMAT);
		return new java.sql.Date(simpleDateFormat.parse(strDate).getTime());
	}

	public int compare(Issue firstIssue, Issue secondIssue) {
		long dateFirst = firstIssue.getCreateDate().getTime();
		long dateSecond = secondIssue.getCreateDate().getTime();
		if (dateSecond > dateFirst) {
			return 1;
		} else if (dateFirst > dateSecond) {
			return -1;
		} else {
			return 0;
		}
	}

}
