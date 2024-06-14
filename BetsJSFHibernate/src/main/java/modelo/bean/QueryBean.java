package modelo.bean;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.Date;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Event;
import domain.Question;


public class QueryBean {
    

    private Long id;
    
    private String queryText;
    
   
    private QuestionBean question;

    
    private BLFacade businessLogic;

   
    private String event;

   
    private List<Question> questions;

    public QueryBean() {
        // Crear instancia de BLFacade
        this.businessLogic = new BLFacadeImplementation();
        this.questions = new ArrayList<Question>();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    public QuestionBean getQuestion() {
        return question;
    }

    public void setQuestion(QuestionBean question) {
        this.question = question;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void queryQuestions() {
        Date currentDate = new Date(0);
        List<Event> events = businessLogic.getEvents(currentDate); // Obtener todos los eventos
        questions = new ArrayList<Question>();
        
        for (Event e : events) {
            if (e.getDescription().equals(event)) {
                questions.addAll(e.getQuestions());
            }
        }
    }
    public List<Event> availableEvents() {
        Date currentDate = new Date(); // Obtener la fecha actual
        List<Date> datesWithEvents = businessLogic.getEventsMonth(currentDate); // Obtener los días del mes con eventos
        
        List<Event> allEvents = new Vector<Event>();
        for (Date date : datesWithEvents) {
            List<Event> events = businessLogic.getEvents(date); // Obtener los eventos para cada día
            allEvents.addAll(events);
        }
        
        return allEvents;
    }
}
