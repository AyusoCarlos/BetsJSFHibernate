package businessLogic;

import java.util.Date;
import java.util.List;

import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface BLFacade {
    @WebMethod
    Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;

    @WebMethod
    List<Event> getEvents(Date date);

    @WebMethod
    List<Date> getEventsMonth(Date date);

    @WebMethod
    void initializeBD();
}
