package service;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;


import com.jayway.restassured.path.json.JsonPath;
import groovyx.net.http.ContentType;

import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ZGoryn
 * Date: 16.03.15
 * Time: 23:31
 * To change this template use File | Settings | File Templates.
 */
public class CalendarResTest {

    @Before
    public void setUp(){
        RestAssured.basePath = "http://localhost:8080";
    }

    @Test
    public void getCalendarJSonTypeTest(){
        expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("/res/calendar/");
    }

    @Test
    public void getCalendarResponseBodyTest(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("EEEE, MM/dd/yyyy", Locale.US);
        String currentDate = "Today is "+dateFormat.format(date);
        String currentMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        Integer num = calendar.get(Calendar.MONTH);

        String json = get("/res/calendar/").asString();
        JsonPath jp = new JsonPath(json);
        assertEquals(currentDate, jp.get("currentDate"));
        jp.setRoot("monthList");
        Map month = jp.get("find {e -> e.name = '"+currentMonth+"'}");
        assertEquals(num, month.get("num"));
    }
}
