package yakushevroman1991.questionnaire;

public class Questionnaire {
    private int id;
    private String time;
    private int question;

    public Questionnaire(int id, String time, int question) {
        this.id = id;
        this.time = time;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }
}
