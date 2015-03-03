package bleakfalls.goldenhands.badjokes;

/**
 * Created by S9925872A on 3/3/2015.
 */
public class Joke {
    String mTitle;
    String mQuestion;
    String mAnswer;

    public Joke(String title, String question, String answer) {
        mTitle = title;
        mQuestion = question;
        mAnswer = answer;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String title) {
        this.mTitle = title;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String question) {
        this.mQuestion = question;
    }

    public String getmAnswer() {
        return mAnswer;
    }

    public void setmAnswer(String answer) {
        this.mAnswer = answer;
    }
}