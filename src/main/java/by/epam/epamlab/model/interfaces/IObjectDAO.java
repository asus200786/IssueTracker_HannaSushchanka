package by.epam.epamlab.model.interfaces;

import java.util.List;

import by.epam.epamlab.exceptions.ExceptionDAO;

public interface IObjectDAO<E> {
	public E getObjectById(long idObject) throws ExceptionDAO;

	public List<E> getObjectsList() throws ExceptionDAO;

	public void saveOrUpdateObject(E e) throws ExceptionDAO;
}
