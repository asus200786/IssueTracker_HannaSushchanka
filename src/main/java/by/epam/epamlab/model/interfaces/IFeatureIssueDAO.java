package by.epam.epamlab.model.interfaces;

import java.util.Map;

import by.epam.epamlab.exceptions.ExceptionDAO;
import by.epam.epamlab.model.beans.issues.Priority;
import by.epam.epamlab.model.beans.issues.Resolution;
import by.epam.epamlab.model.beans.issues.Status;
import by.epam.epamlab.model.beans.issues.Type;

public interface IFeatureIssueDAO {
	public Map<Short, Status> getStatuses() throws ExceptionDAO;

	public Map<Short, Resolution> getResolutions() throws ExceptionDAO;

	public Map<Short, Priority> getPriorities() throws ExceptionDAO;

	public Map<Short, Type> getTypes() throws ExceptionDAO;
}
