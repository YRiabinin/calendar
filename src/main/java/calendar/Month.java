package calendar;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ZGoryn
 * Date: 12.03.15
 * Time: 0:21
 * To change this template use File | Settings | File Templates.
 */

public class Month {
    private Calendar calendarInstance = Calendar.getInstance();
    private String name;
    private int num;
    private int currentDay;
    private List<Integer> days;
    private List<Integer> selectedDays;
    private int fstDay;
    private boolean isSelected;

    public Month() {
        initMonth();
    }

    public Month(int monthNum) {
        calendarInstance.add(Calendar.MONTH, monthNum);
        initMonth();
    }

    private void initMonth(){
        this.name = calendarInstance.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        this.num = calendarInstance.get(Calendar.MONTH);
        this.currentDay = calendarInstance.get(Calendar.DAY_OF_MONTH);
        this.days = new ArrayList<>(calendarInstance.getActualMaximum(Calendar.DAY_OF_MONTH));
        this.selectedDays = new ArrayList<>(calendarInstance.getActualMaximum(Calendar.DAY_OF_MONTH));
        CalendarXMLParser calendarXMLParser = new CalendarXMLParser();
        for (Map.Entry<Integer, Integer> entry: calendarXMLParser.getDays(this.name).entrySet()){
            this.days.add(entry.getKey());
            this.selectedDays.add(entry.getValue());
        }
        calendarInstance.add(Calendar.DAY_OF_MONTH,-this.currentDay+1);
        this.fstDay = calendarInstance.get(Calendar.DAY_OF_WEEK);
        this.isSelected = calendarXMLParser.isSelected(this.name);
    }

    public synchronized void editDay(int day){
        if (this.selectedDays.get(--day) == 0){
            this.selectedDays.set(day, 1);
        } else {
            this.selectedDays.set(day, 0);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    public List<Integer> getDays() {
        return days;
    }

    public void setDays(List<Integer> days) {
        this.days = days;
    }

    public List<Integer> getSelectedDays() {
        return selectedDays;
    }

    public void setSelectedDays(List<Integer> selectedDays) {
        this.selectedDays = selectedDays;
    }

    public int getFstDay() {
        return fstDay;
    }

    public void setFstDay(int fstDay) {
        this.fstDay = fstDay;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Month month = (Month) o;

        if (currentDay != month.currentDay) return false;
        if (fstDay != month.fstDay) return false;
        if (isSelected != month.isSelected) return false;
        if (num != month.num) return false;
        if (!calendarInstance.equals(month.calendarInstance)) return false;
        if (!days.equals(month.days)) return false;
        if (!name.equals(month.name)) return false;
        if (!selectedDays.equals(month.selectedDays)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = calendarInstance.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + num;
        result = 31 * result + currentDay;
        result = 31 * result + days.hashCode();
        result = 31 * result + selectedDays.hashCode();
        result = 31 * result + fstDay;
        result = 31 * result + (isSelected ? 1 : 0);
        return result;
    }
}
