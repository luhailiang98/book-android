package top.luhailiang.mylibrary.model;

public class BookInfoDTO {

    private Integer bookId;
    private String bookName;
    private Integer imageId;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public BookInfoDTO() {
    }

    public BookInfoDTO(Integer bookId, String bookName, Integer imageId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.imageId = imageId;
    }
}
