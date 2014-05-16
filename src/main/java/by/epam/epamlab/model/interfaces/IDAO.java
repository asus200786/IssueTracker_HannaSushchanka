package by.epam.epamlab.model.interfaces;

import by.epam.epamlab.exceptions.ExceptionDAO;

public interface IDAO <E>{
	E getObject (long idObject) throws ExceptionDAO;
}
