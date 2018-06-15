package components;

import core.Answer;
import core.Question;
import core.UserInputQ;


public class UserInputQFrame extends QuestionFrame {
    private Answer answer;

    @Override
    public QuestionFrame setQuestion(Question question) {
        answer = ((UserInputQ) question).getAnswer();
        this.question = question;
        init();
        return this;
    }

    UserInputQFrame() {
        super();
    }

    @Override
    public void init() {
        this.setBottom(answer.getAnswerTextField());
    }
}
