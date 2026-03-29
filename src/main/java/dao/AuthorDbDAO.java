package dao;

import domain.Author;
import exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * DAO для таблицы authors
 */
public class AuthorDbDAO implements RepositoryDAO<Author> {

    private static final String SELECT_ALL_AUTHORS =
            "SELECT id, fullname, phone, email, rating FROM authors ORDER BY fullname ASC";
    private static final String SELECT_AUTHOR_BY_ID =
            "SELECT id, fullname, phone, email, rating FROM authors WHERE id = ?";
    private static final String INSERT_AUTHOR =
            "INSERT INTO authors(fullname, phone, email, rating) VALUES(?,?,?,?)";
    private static final String UPDATE_AUTHOR =
            "UPDATE authors SET fullname = ?, phone = ?, email = ?, rating = ? WHERE id = ?";
    private static final String DELETE_AUTHOR =
            "DELETE FROM authors WHERE id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();

    private Connection getConnection() throws java.sql.SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Author author) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_AUTHOR, new String[]{"id"})) {

            Long id = -1L;
            pst.setString(1, author.getFullName());
            pst.setString(2, author.getPhone());
            pst.setString(3, author.getEmail());
            pst.setDouble(4, author.getRating());
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
    public void update(Author author) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_AUTHOR)) {

            pst.setString(1, author.getFullName());
            pst.setString(2, author.getPhone());
            pst.setString(3, author.getEmail());
            pst.setDouble(4, author.getRating());
            pst.setLong(5, author.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_AUTHOR)) {

            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Author findById(Long id) throws DAOException {
        Author author = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_AUTHOR_BY_ID)) {

            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                author = fillAuthor(rs);
            }
            rs.close();
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return author;
    }

    @Override
    public List<Author> findAll() throws DAOException {
        List<Author> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_AUTHORS);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                list.add(fillAuthor(rs));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    private Author fillAuthor(ResultSet rs) throws java.sql.SQLException {
        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setFullName(rs.getString("fullname"));
        author.setPhone(rs.getString("phone"));
        author.setEmail(rs.getString("email"));
        author.setRating(rs.getDouble("rating"));
        return author;
    }

    public Author findById(Long id, List<Author> authors) {
        if (authors != null) {
            for (Author a : authors) {
                if (a.getId().equals(id)) {
                    return a;
                }
            }
        }
        return null;
    }
}