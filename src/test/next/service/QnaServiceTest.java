package next.service;

import next.CannotOperateException;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by granknight on 2016. 8. 8..
 */
@RunWith(MockitoJUnitRunner.class)
public class QnaServiceTest {

    @Mock
    private QuestionDao questionDao;

    @Mock
    private AnswerDao answerDao;

    @InjectMocks
    private QnaService dut;

    private User user;

    @Before
    public void setUp() throws Exception{
        user = new User("userId", "password", "name", "email");
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void delete_존재하지않은질문일때() throws Exception {

        User user = new User("userId", "password", "name", "email");
        when(questionDao.findById(0)).thenReturn(null);

        dut.deleteQuestion(0, user);
    }

    @Test(expected = CannotOperateException.class)
    public void delete_다른사람글을삭제할때() throws Exception {

        when(questionDao.findById(0)).thenReturn(new Question("anotherId", "", ""));

        dut.deleteQuestion(0, user);
    }

    @Test(expected = CannotOperateException.class)
    public void delete_다른사람이추가한댓글이있을때() throws Exception {

        when(questionDao.findById(0)).thenReturn(new Question("userId", "", ""));

        when(answerDao.findAllByQuestionId(0)).thenReturn(Arrays.asList(new Answer("anotherId", "", 1L), new Answer("userId", "", 2L)));

        dut.deleteQuestion(0, user);

    }

    @Test
    public void delete_같은사람댓글일때() throws Exception {

        when(questionDao.findById(0)).thenReturn(new Question("userId", "", ""));

        when(answerDao.findAllByQuestionId(0)).thenReturn(Arrays.asList(new Answer("userId", "", 1L), new Answer("userId", "", 2L)));

        dut.deleteQuestion(0, user);

        verify(questionDao).delete(0);

    }


}