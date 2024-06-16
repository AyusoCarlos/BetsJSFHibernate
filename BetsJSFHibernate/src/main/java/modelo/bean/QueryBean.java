package modelo.bean;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import java.util.List;
import java.util.Vector;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.event.SelectEvent;

import java.util.Date;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Event;
import domain.Question;

@ManagedBean(value = "queryBean")
@ViewScoped
public class QueryBean {
    

    private Long id;
    
    private String queryText;
    
   
    private QuestionBean question;
    
    private Date eventDate;

    
    private BLFacade businessLogic;
    
    
    private List<Event> events;
    
    private String eventNumber;

   
    private String event;

   
    private List<Question> questions;

    public QueryBean() {
        // Crear instancia de BLFacade
        this.businessLogic = new BLFacadeImplementation();
        this.questions = new ArrayList<Question>();
    }

    public String getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(String eventNumber) {
		this.eventNumber = eventNumber;
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
    
    public List<Event> getEvents() {
        return events;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void queryQuestions() {
    	LlenarEvent();
        List<Event> events = businessLogic.getEvents(this.eventDate); // Obtener todos los eventos
        questions = new ArrayList<Question>();
        
        for (Event e : events) {
            if (e.getDescription().equals(event)) {
                questions.addAll(e.getQuestions());
            }
        }
        
    }
    
    
    public void LlenarEvent() {
    	Event e = null;
    	try {
    		for (Event i : events) {
    			if((Integer.valueOf(eventNumber).intValue()==i.getEventNumber().intValue())) {
    				e = i;
    			}
    			
    		}
    	if (e == null) {
    		System.out.println("NO existe");
    	}else {
    		System.out.println("SI existe"+ e);
    		event = e.getDescription();
    	}
    		
    		
    	}catch(Exception e1){
    		
    	}
    }
    // Método para obtener la lista de eventos disponibles en la fecha actual
    public void updateEvents() {
            this.events = businessLogic.getEvents(this.eventDate);
            System.out.println(this.eventDate);
            System.out.println(this.events);
            setEventNumber(null);
       
    }
    
    public void onDateSelect(SelectEvent event) {
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fecha escogida: " + event.getObject()));
        System.out.println(event.getObject());
        eventDate =Date.from(((LocalDate) event.getObject()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        updateEvents();
        setEventNumber(null);
       
    }
}
