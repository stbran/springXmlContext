package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;
    private final QuestionDao questionDao;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");

        List<Question> questions = questionDao.findAll();
        if (questions.isEmpty()) {
            ioService.printLine("No questions found.");
            return;
        }

        int i = 1;
        for (Question question : questions) {
            ioService.printFormattedLine("%d. %s", i, question.text());

            int j = 1;
            for (Answer answer : question.answers()) {
                ioService.printFormattedLine("\t%d) %s", j, answer.text());
                j++;
            }
            ioService.printLine("");
            i++;
        }
    }
}
