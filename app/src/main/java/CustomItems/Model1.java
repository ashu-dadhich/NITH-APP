package CustomItems;

/**
 * Created by Ashu on 14-08-2016.
 */
public class Model1 {
    private String date;
    private String name;
    private String question;
    private String answer;
    private String branch;

    public Model1() {
    }

    public Model1(String date, String name, String question, String answer, String branch) {
        this.date = date;
        this.name = name;
        this.question = question;
        this.answer = answer;
        this.branch = branch;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
