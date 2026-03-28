package domain;

/**
 * Класс для данных книги
 */
public class Book {
    private Long id;
    private String title;
    private String binding;
    private String publisher;
    private int publicationYear;
    private String genre;
    private Long idAuthor;
    private Author author;

    public Book() {
    }

    public Book(String title, String binding, String publisher, int publicationYear,
                String genre, Author author) {
        this.title = title;
        this.binding = binding;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.author = author;
    }

    public Book(String title, String binding, String publisher, int publicationYear,
                String genre, Long idAuthor, Author author) {
        this.title = title;
        this.binding = binding;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.idAuthor = idAuthor;
        this.author = author;
    }

    public Book(Long id, String title, String binding, String publisher, int publicationYear,
                String genre, Long idAuthor, Author author) {
        this.id = id;
        this.title = title;
        this.binding = binding;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.idAuthor = idAuthor;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Long idAuthor) {
        this.idAuthor = idAuthor;
    }

    public Author author() {
        return author;
    }

    public String getAuthor() {
        return author != null ? author.getFullName() : "";
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book {" +
                "id = " + id +
                ", title = " + title +
                ", binding = " + binding +
                ", publisher = " + publisher +
                ", publicationYear = " + publicationYear +
                ", genre = " + genre +
                ", author = " + getAuthor() +
                "}";
    }
}