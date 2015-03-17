/**
 * Created with IntelliJ IDEA.
 * User: ZGoryn
 * Date: 15.03.15
 * Time: 23:51
 * To change this template use File | Settings | File Templates.
 */
function loadCalendar()
{
    var http_request = getXMLHttpRequest();
    http_request.onreadystatechange  = function(){
        if (http_request.readyState == 4  )
        {
            var jsonObj = JSON.parse(http_request.responseText);
            document.getElementById("currentDate").innerHTML = jsonObj.currentDate;
            var counter = 0;
            var monthBox;
            for (var i = 0; i < 3; i++){
                monthBox = document.getElementById("month_"+i);
                monthBox.innerHTML =  jsonObj.monthList[i].name;
                if (i == 0) {
                    monthBox.className = "current"
                }

                if (jsonObj.monthList[i].isSelected == 1) {
                    monthBox.className = monthBox.className + " selected"
                    document.getElementById("month_"+i+"-box").className = "show";
                }
                for (var j = 1; j <= 35; j++){
                    if (j >= jsonObj.monthList[i].fstDay){
                        if (jsonObj.monthList[i].days[counter] != undefined){
                            var dayTd = document.getElementById("month_"+i+"-"+j);
                            dayTd.innerHTML =  jsonObj.monthList[i].days[counter];
                            dayTd.setAttribute("name", "day");
                            if (i == 0 && ((counter+1) == jsonObj.monthList[i].currentDay)){
                                dayTd.className = "current";
                            }
                            if (j % 7 == 1){
                                dayTd.className = dayTd.className + " sn";
                            }
                            if (jsonObj.monthList[i].selectedDays[counter] == 1){
                                dayTd.className = dayTd.className + " selected";
                            }
                            counter++;
                        } else {
                            break;
                        }
                    }
                }
                counter = 0;
            }
        }
    }
    http_request.open("GET", window.location+"res/calendar/", true);
    http_request.send();
}

function clickMonth(id){
    if (document.getElementById(id).className.indexOf("selected") == -1){
        var http_request = getXMLHttpRequest();
        http_request.onreadystatechange  = function(){
            if (http_request.readyState == 4  ){
                for (var i = 0; i < 3; i++){
                    document.getElementById("month_"+i+"-box").className = "hide";
                    var element = document.getElementById("month_"+i);
                    var index = document.getElementById("month_"+i).className.indexOf("selected");
                    if (index != -1){
                        element.className = element.className.substring(0, index-1);
                    }
                }
                document.getElementById(id+"-box").className = "show";
                document.getElementById(id).className = document.getElementById(id).className + " selected";
            }
        }

        var month = document.getElementById(id);
        http_request.open("POST", window.location+"res/date/"+month.innerHTML+"/", true);
        http_request.send();

    }
}

function clickDay(id){
    var element = document.getElementById(id);
    if (element.getAttribute("name") == "day"){
        var http_request = getXMLHttpRequest();
        http_request.onreadystatechange  = function(){
            if (http_request.readyState == 4  ){
                var index = element.className.indexOf("selected");
                if (index == -1){
                    element.className = element.className + " selected";
                } else {
                    element.className = element.className.substring(0, index-1);
                }
            }
        }

        var idArr = id.split("-");
        var monthName = document.getElementById(idArr[0]);
        if (element.className.indexOf("selected") > -1){
            http_request.open("DELETE", window.location+"res/date/"+monthName.innerHTML+"/"+element.innerHTML, true);
            http_request.send();
        } else {
            http_request.open("POST", window.location+"res/date/"+monthName.innerHTML+"/"+element.innerHTML, true);
            http_request.send();
        }

    }

}

function getXMLHttpRequest(){
    //var data_file = "http://www.tutorialspoint.com/json/data.json";
    var http_request = new XMLHttpRequest();
    try{
        // Opera 8.0+, Firefox, Chrome, Safari
        http_request = new XMLHttpRequest();
    }catch (e){
        // Internet Explorer Browsers
        try{
            http_request = new ActiveXObject("Msxml2.XMLHTTP");
        }catch (e) {
            try{
                http_request = new ActiveXObject("Microsoft.XMLHTTP");
            }catch (e){
                // Something went wrong
                alert("Your browser broke!");
                return false;
            }
        }
    }
    return http_request;
}