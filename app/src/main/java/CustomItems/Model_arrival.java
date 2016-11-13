package CustomItems;

/**
 * Created by Ashu on 21-08-2016.
 */
public class Model_arrival {
    String title;
    String author;
    String copies;
    String call_no;
    String dept;
    String pub;

    public Model_arrival() {
    }

    public Model_arrival(String title, String author, String copies, String call_no, String dept, String pub) {
        this.title = title;
        this.author = author;
        this.copies = copies;
        this.call_no = call_no;
        this.dept = dept;
        this.pub = pub;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCopies() {
        return copies;
    }

    public void setCopies(String copies) {
        this.copies = copies;
    }

    public String getCall_no() {
        return call_no;
    }

    public void setCall_no(String call_no) {
        this.call_no = call_no;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }
}
