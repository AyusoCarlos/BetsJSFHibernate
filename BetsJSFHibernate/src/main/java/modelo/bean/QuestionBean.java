package modelo.bean;

import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.UtilDate;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import domain.Event;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

@ManagedBean(name = "QuestionBean")
@ViewScoped
public class QuestionBean {
    
    private Long id;
    private String questionText;
    private float betMinimum;
    private String result;
    private List<Event> events;
    private Date eventDate;
    private BLFacade businessLogic;
    private Event event;
    private String eventNumber;

    public QuestionBean() {
        this.businessLogic = new BLFacadeImplementation();
        //this.eventDate =UtilDate.newDate(2024,6,17); 
       // Inicializar con la fecha actual
         // Inicializar la lista de eventos
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

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public float getBetMinimum() {
        return betMinimum;
    }

    public void setBetMinimum(float betMinimum) {
        this.betMinimum = betMinimum;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public List<Event> getEvents() {
        return events;
    }

    // Método para obtener la lista de eventos disponibles en la fecha actual
    public void updateEvents() {
            this.events = businessLogic.getEvents(this.eventDate);
            System.out.println(this.eventDate);
            System.out.println(this.events);
            eventNumber = null;
       
    }
    
    public void onDateSelect(SelectEvent event) {
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fecha escogida: " + event.getObject()));
        System.out.println(event.getObject());
        eventDate =Date.from(((LocalDate) event.getObject()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        updateEvents();
        eventNumber = null;
        
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
    		event = e;
    	}
    		
    		
    	}catch(Exception e1){
    		
    	}
    }


    public void createQuestion() {
        try {
        	LlenarEvent();
        	System.out.println(event +"DD");
            businessLogic.createQuestion(event, questionText, betMinimum);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   
}
