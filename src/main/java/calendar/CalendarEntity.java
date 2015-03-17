package calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ZGoryn
 * Date: 14.03.15
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 */
public class CalendarEntity {
    private static CalendarEntity calendarEntity = null;
    private List<Month> monthList = new ArrayList<>();
    private String currentDate;

    private CalendarEntity() {
            this.monthList.add(new Month());
            this.monthList.add(new Month(-1));
            this.monthList.add(new Month(1));

            if (!isSelectedMonth()){
                this.monthList.get(0).setIsSelected(true);
            }

            this.currentDate = setDate();
    }

    public static synchronized CalendarEntity getCalendarEntity(){
        if (calendarEntity == null){
            calendarEntity = new CalendarEntity();
        }
        return calendarEntity;
    }

    public List<Month> getMonthList() {
        return monthList;
    }

    public void setMonthList(List<Month> monthList) {
        this.monthList = monthList;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public synchronized void editDay(String monthName, int day){
        for (Month month: monthList){
            if (month.getName().equals(monthName)){
                month.editDay(day);
            }
        }
    }

    public synchronized void selectMonth(String monthName){
        for (Month month: monthList){
            month.setIsSelected(false);
            if (month.getName().equals(monthName)){
                month.setIsSelected(true);
            }
        }
    }

    private boolean isSelectedMonth(){
        Iterator<Month> iterator = this.monthList.iterator();
        boolean hasSelected = false;
        while (iterator.hasNext()){
            if (iterator.next().getIsSelected() == true){
                hasSelected = true;
                break;
            }
        }
        return hasSelected;
    }

    private String setDate(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("EEEE, MM/dd/yyyy", Locale.US);
        return "Today is "+dateFormat.format(date);
    }
}
