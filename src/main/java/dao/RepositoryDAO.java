package dao;

import java.util.List;
import exception.DAOException;

public interface RepositoryDAO<T> {
    Long insert(T o) throws DAOException;
    void update(T o) throws DAOException;
    void delete(Long id) throws DAOException;
    T findById(Long id) throws DAOException;
    List<T> findAll() throws DAOException;
}