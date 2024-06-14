package modelo.bean;

import java.util.Date;

import java.util.List;
import java.util.Vector;
import java.util.ArrayList;

import javax.persistence.*;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import configuration.UtilDate;
import domain.Event;


public class QuestionBean {
    
    private Long id;
    
    private String question;
    
    private float betMinimum;
    
    private String result;
    
  
    private BLFacade businessLogic;
    
   
    private Event event;

    public QuestionBean() {
        this.businessLogic =  new BLFacadeImplementation();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestionText(String questionText) {
        this.question = questionText;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public double getBetMinimum() {
        return betMinimum;
    }

    public void setBetMinimum(Float betMinimum) {
        this.betMinimum = betMinimum;
    }
    
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    // Método para obtener la lista de eventos disponibles
    //@Transient
    public List<Event> availableEvents() {
        Date currentDate = new Date(); // Obtener la fecha actual
        List<Date> datesWithEvents = businessLogic.getEventsMonth(currentDate); // Obtener los días del mes con eventos
        
        List<Event> allEvents = new ArrayList<Event>();
        for (Date date : datesWithEvents) {
            List<Event> events = businessLogic.getEvents(date); // Obtener los eventos para cada día
            allEvents.addAll(events);
        }
        
        return allEvents;
    }

    public String createQuestion() throws EventFinished {
        try {
            businessLogic.createQuestion(event, question,betMinimum);
            return "success";
        } catch (QuestionAlreadyExist e) {
            e.printStackTrace();
            return "failure";
        }
    }
}
