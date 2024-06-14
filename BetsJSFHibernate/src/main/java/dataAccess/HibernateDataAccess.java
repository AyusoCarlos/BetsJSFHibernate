package dataAccess;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.ArrayList;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import configuration.UtilDate;
import domain.Event;
import domain.Question;
import exceptions.QuestionAlreadyExist;
import modelo.HibernateUtil.HibernateUtil;

public class HibernateDataAccess  {

    public HibernateDataAccess(boolean initializeMode) {
        System.out.println("Creating HibernateDataAccess instance");

        
    }

    public HibernateDataAccess() {
        new HibernateDataAccess(false);
    }


    public void initializeDB() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

	    	Calendar today = Calendar.getInstance();
			   
			   int month=today.get(Calendar.MONTH);
			   month+=1;
			   int year=today.get(Calendar.YEAR);
			   if (month==12) { month=0; year+=1;}  
		    
				Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
				Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
				Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
				Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
				Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
				Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
				Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
				Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
				Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
				Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));
	
				Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
				Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
				Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
				Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
				Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
				Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
				
	
				Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month,28));
				Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month,28));
				Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
				Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month,28));
				
				Question q1;
				Question q2;
				Question q3;
				Question q4;
				Question q5;
				Question q6;
						
				if (Locale.getDefault().equals(new Locale("es"))) {
					q1=ev1.addQuestion("¿Quién ganará el partido?",1);
					q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
					q3=ev11.addQuestion("¿Quién ganará el partido?",1);
					q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
					q5=ev17.addQuestion("¿Quién ganará el partido?",1);
					q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
				}
				else if (Locale.getDefault().equals(new Locale("en"))) {
					final String p = "Who will win the match?";
					q1=ev1.addQuestion(p,1);
					q2=ev1.addQuestion("Who will score first?",2);
					q3=ev11.addQuestion(p,1);
					q4=ev11.addQuestion("How many goals will be scored in the match?",2);
					q5=ev17.addQuestion(p,1);
					q6=ev17.addQuestion("Will there be goals in the first half?",2);
				}			
				else {
					q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
					q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
					q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
					q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
					q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
					q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
					
				}
				
				
				session.persist(q1);
				session.persist(q2);
				session.persist(q3);
				session.persist(q4);
				session.persist(q5);
				session.persist(q6);
		
		        
				session.persist(ev1);
				session.persist(ev2);
				session.persist(ev3);
				session.persist(ev4);
				session.persist(ev5);
				session.persist(ev6);
				session.persist(ev7);
				session.persist(ev8);
				session.persist(ev9);
				session.persist(ev10);
				session.persist(ev11);
				session.persist(ev12);
				session.persist(ev13);
				session.persist(ev14);
				session.persist(ev15);
				session.persist(ev16);
				session.persist(ev17);
				session.persist(ev18);
				session.persist(ev19);
				session.persist(ev20);			
				
				session.getTransaction().commit();
				System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
    }

    
    public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
        // Implementación para crear una pregunta
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	//System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		//System.out.println(db+" "+event);
		
		
            session.beginTransaction();
            Event ev = (Event) session.get(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			

			Question q = ev.addQuestion(question, betMinimum);
			//session.persist(q);
			session.persist(ev); // session.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			session.getTransaction().commit();
			return q;
		 
    }

   
    public List<Event> getEvents(Date date) {
        // Implementación para obtener eventos de una fecha
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	System.out.println(">> DataAccess: getEvents");
    	
            session.beginTransaction();
	    	List<Event> res = new ArrayList<Event>();
	    	Query query = session.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=:date");   
			query.setParameter("date", date);
			List<Event> events = query.list();
		 	 for (Event ev:events){
		 	   System.out.println(ev.toString());		 
			   res.add(ev);
			  }
		 	session.getTransaction().commit();
		 	return res;
   
    }

    
    public List<Date> getEventsMonth(Date date) {
        // Implementación para obtener los días con eventos de un mes
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	System.out.println(">> DataAccess: getEventsMonth");
    
            session.beginTransaction();
			List<Date> res = new ArrayList<Date>();	
			
			Date firstDayMonthDate= UtilDate.firstDayMonth(date);
			Date lastDayMonthDate= UtilDate.lastDayMonth(date);
					
			
			Query query = session.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN :startDate and :endDate");
			query.setParameter("startDate", firstDayMonthDate);
			query.setParameter("endDate", lastDayMonthDate);

			List<Date> dates = query.list();
		 	 for (Date d:dates){
		 	   System.out.println(d.toString());		 
			   res.add(d);
			  }
		 	session.getTransaction().commit();
		 	return res;
    
		 
    }

  

   
    public void close() {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.close();
        System.out.println("Database closed");
    }

   

    
    public boolean existQuestion(Event event, String question) {
        // Implementación para verificar si una pregunta ya existe para un evento
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	boolean ex = false;
    	try {
            session.beginTransaction();
	    	System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
	    	Event ev = (Event) session.get(Event.class, event.getEventNumber());
	    	ex = ev.DoesQuestionExists(question);
	    } catch (Exception e) {
			  e.printStackTrace();
	    }
		return ex;
}}
