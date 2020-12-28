package ui.controller;

import domain.db.DbException;
import domain.model.NotAuthorizedException;
import domain.model.Person;
import domain.model.Reservation;
import ui.util.Authorization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MakeReservationHandler extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, NotAuthorizedException, IOException {
        Person.Role[] roles = {Person.Role.user, Person.Role.administrator};
        Authorization.checkrole(request, roles);

        ArrayList<String> errors = new ArrayList();
        Person p = (Person) request.getSession().getAttribute("person");
        Reservation r = new Reservation(p);
        setstartTime(r, request, errors);
        setendTime(r, request, errors);
        setField(r, request, errors);
        setPhonenr(r, request, errors);
        setEmail(r, request, errors);

        System.out.println("Errors so far after processing fields:\n" + errors);
        if (errors.size() > 0){
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("Servlet?command=Profile").forward(request,response);
        }
        try{
            DB.addReservation(r);
        } catch (Exception e){
            errors.add(e.getMessage());
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("Servlet?command=Profile").forward(request,response);
        }

        request.getRequestDispatcher("Servlet?command=Overview").forward(request,response);
    }


    private void setstartTime(Reservation r, HttpServletRequest request, ArrayList<String> errors){
        try {
            String startTime = request.getParameter("startTime");
            request.setAttribute("startTimePreviousValue", startTime);
            r.setStartTimeString(startTime);
        } catch (DbException exc){
            errors.add(exc.getMessage());
        }
    }

    private void setendTime(Reservation r, HttpServletRequest request, ArrayList<String> errors){
        try {
            String endTime = request.getParameter("endTime");
            request.setAttribute("endTimePreviousValue", endTime);
            r.setEndTimeString(endTime);
        } catch (DbException exc){
            errors.add(exc.getMessage());
        }
    }

    private void setField(Reservation r, HttpServletRequest request, ArrayList<String> errors){

        try {
            String field = request.getParameter("field");
            request.setAttribute("fieldPreviousValue", field);
            r.setField(field);

        } catch (DbException exc){
            errors.add(exc.getMessage());
        }
    }

    private void setPhonenr(Reservation r, HttpServletRequest request, ArrayList<String> errors) {
        String phonenr = request.getParameter("phonenr");
        try{
            r.setPhonenr(phonenr);
            request.setAttribute("phonenrPreviousValue", phonenr);
        } catch(DbException e){
            errors.add(e.getMessage());
        }

    }

    private void setEmail(Reservation r, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        try{
            r.setEmail(email);
            request.setAttribute("emailPreviousValue", email);
        } catch(DbException e){
            errors.add(e.getMessage());
        }

    }
}
