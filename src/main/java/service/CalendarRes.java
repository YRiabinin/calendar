package service;

import calendar.CalendarEntity;
import calendar.CalendarXMLParser;

import javax.ws.rs.*;

/**
 * Created with IntelliJ IDEA.
 * User: ZGoryn
 * Date: 10.03.15
 * Time: 20:22
 * To change this template use File | Settings | File Templates.
 */

@Path("/")
public class CalendarRes {
    private CalendarXMLParser calendarXMLParser = new CalendarXMLParser();

    @GET
    @Path("/calendar/")
    @Produces("application/json")
    public CalendarEntity getCalendar(){
        CalendarEntity calendarEntity = CalendarEntity.getCalendarEntity();
        return calendarEntity;
    }

    @POST
    @Path("date/{month}/{day}")
    public void setDay(@PathParam(value = "month") String month, @PathParam(value = "day") int day ){
        this.calendarXMLParser.selectDay(month, day, true);
    }

    @DELETE
    @Path("date/{month}/{day}")
    public void unsetDay(@PathParam(value = "month") String month, @PathParam(value = "day") int day ){
        this.calendarXMLParser.selectDay(month, day, false);
    }

    @POST
    @Path("date/{month}/")
    public void setMonth(@PathParam(value = "month") String month){
        this.calendarXMLParser.selectMonth(month);
    }

}
