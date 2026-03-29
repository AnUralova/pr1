package dao;

import domain.Book;
import exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * DAO для таблицы books
 */
public class BookDbDAO implements RepositoryDAO<Book> {

    private static final String SELECT_ALL_BOOKS =
            "SELECT id, authorid, title, binding, publisher, publicationyear, genre FROM books ORDER BY title ASC";
    private static final String SELECT_BOOK_BY_ID =
            "SELECT id, authorid, title, binding, publisher, publicationyear, genre FROM books WHERE id = ?";
    private static final String INSERT_BOOK =
            "INSERT INTO books(authorid, title, binding, publisher, publicationyear, genre) VALUES(?,?,?,?,?,?)";
    private static final String UPDATE_BOOK =
            "UPDATE books SET authorid = ?, title = ?, binding = ?, publisher = ?, publicationyear = ?, genre = ? WHERE id = ?";
    private static final String DELETE_BOOK =
            "DELETE FROM books WHERE id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();

    private Connection getConnection() throws java.sql.SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Book book) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_BOOK, new String[]{"id"})) {

            Long id = -1L;
            pst.setLong(1, book.getIdAuthor());
            pst.setString(2, book.getTitle());
            pst.setString(3, book.getBinding());
            pst.setString(4, book.getPublisher());
            pst.setInt(5, book.getPublicationYear());
            pst.setString(6, book.getGenre());
            pst.executeUpdate();

            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                id = gk.getLong("id");
            }
            gk.close();
            return id;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Book book) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_BOOK)) {

            pst.setLong(1, book.getIdAuthor());
            pst.setString(2, book.getTitle());
            pst.setString(3, book.getBinding());
            pst.setString(4, book.getPublisher());
            pst.setInt(5, book.getPublicationYear());
            pst.setString(6, book.getGenre());
            pst.setLong(7, book.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_BOOK)) {

            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Book findById(Long id) throws DAOException {
        Book book = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_BOOK_BY_ID)) {

            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                book = fillBook(rs);
            }
            rs.close();
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return book;
    }

    @Override
    public List<Book> findAll() throws DAOException {
        List<Book> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_BOOKS);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                list.add(fillBook(rs));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    private Book fillBook(ResultSet rs) throws java.sql.SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setIdAuthor(rs.getLong("authorid"));
        book.setTitle(rs.getString("title"));
        book.setBinding(rs.getString("binding"));
        book.setPublisher(rs.getString("publisher"));
        book.setPublicationYear(rs.getInt("publicationyear"));
        book.setGenre(rs.getString("genre"));
        return book;
    }
}