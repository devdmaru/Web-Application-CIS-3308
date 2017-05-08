

function $(element) {
    return document.getElementById(element);
}

var userHttpRequest;
var teamHttpRequest;

function ajaxGetSelectTags() {

    userHttpRequest = new XMLHttpRequest();  //For Firefox, Safari, Opera
    var url = "UserSelectTag_API.jsp";
    userHttpRequest.open("GET", url);
    userHttpRequest.onreadystatechange = handleUserSelect;
    userHttpRequest.send(null);

    teamHttpRequest = new XMLHttpRequest();
    var url2 = "otherSelectTag_API.jsp";
    teamHttpRequest.open("GET", url2);
    teamHttpRequest.onreadystatechange = handleTeamSelect;
    teamHttpRequest.send(null);

}

function handleUserSelect() {
    //alert('handling response');
    if (userHttpRequest.readyState == 4 && userHttpRequest.status == 200) {

        console.log("user response text is " + userHttpRequest.responseText);
        jsBuildSelectTag(userHttpRequest.responseText, 'userId', 'userSpan');
    }
}
function handleTeamSelect() {
    //alert('handling response');
    if (teamHttpRequest.readyState == 4 && teamHttpRequest.status == 200) {

        console.log("team response text is " + teamHttpRequest.responseText);
        jsBuildSelectTag(teamHttpRequest.responseText, 'teamId', 'teamSpan');
    }
}

function jsBuildSelectTag(responseText, tagId, parentId) {

    // avoid javaScript ambiguity
    responseText = "(" + responseText + ")";
    var responseObj = eval(responseText);

    if (responseObj == null) {
        logError(parentId, "jsBuildSelectTag trying to build into " +
                parentId + ": Null Object returned from JSON call.");
        return;
    }

    if (responseObj.dbError == null) {
        logError(parentId, "jsBuildSelectTag trying to build into " +
                parentId + ": dbError (message) not found.");
        return;
    }

    if (responseObj.dbError.length > 0) {
        $("databaseError").innerHTML = responseObj.dbError;
        logError(parentId, "jsBuildSelectTag trying to build into " +
                parentId + ": dbError message is " + responseObj.dbError);
        return;
    }

    if (responseObj.selectOptionList == null) {
        logError(parentId, "jsBuildSelectTag trying to build into " +
                parentId + ": selectOptionList not found.");
        return;
    }

    if (!Array.isArray(responseObj.selectOptionList)) {
        logError(parentId, "jsBuildSelectTag trying to build into " +
                parentId + ": selectOptionList found, but is not an array.");
        return;
    }

    if (responseObj.selectOptionList.length < 1) {
        logError(parentId, "jsBuildSelectTag trying to build into " +
                parentId + ": selectOptionList is empty array.");
        return;
    }


    var parent = document.getElementById(parentId); // get ref to parent element

    // Create a select tag, set it's id and append this tag to the parent.
    var selectList = document.createElement("select");
    selectList.id = tagId;
    parent.appendChild(selectList);

    var selectOption;

    //Create and append the options
    // i in optionList just iterates i from 0 to length of list-1.
    for (var i = 0; i < responseObj.selectOptionList.length; i++) {
        selectOption = responseObj.selectOptionList[i];
        // new Option() input parameers are displayed option, option value. 
        var myOption = new Option(selectOption.name,
                selectOption.id); // displayed option, option value
        selectList.appendChild(myOption);
    }
}

var searchHttpRequest;
function ajaxGetData() {
    searchHttpRequest = new XMLHttpRequest();
    var url = "dataSearch_API.jsp";
    url += "?date=" + $("dateSearch").value;
    url += "&userId=" + $("userId").value;
    url += "&teamId=" + $("teamId").value;
    console.log("Url: " + url);

    searchHttpRequest.open("GET", url);
    searchHttpRequest.onreadystatechange = handleSearch;
    searchHttpRequest.send(null);
    $("commentTable").innerHTML = "";
}

function handleSearch() {
    if (searchHttpRequest.readyState == 4 && searchHttpRequest.status == 200) {
        makeTable(searchHttpRequest.responseText);
    }
}

function makeTable(responseText) {
    responseText = "(" + responseText + ")";
    var response = eval(responseText);

    if (response.dbError == null) {
        $("commentsTable").innerHTML = "Search is currently unavailable.";
        return;
    }
    if (response.dbError.length > 0) {
        $("commentsTable").innerHTML = "Error in reading from database. Error: " + response.dbError;
        return;
    }
    if (parseInt(response.listSize) == 0) {
        $("countryTable").innerHTML = "No Comments Match Your Search";
        return;
    }

    var newCell;
    var newRow;

    var newTable = document.createElement("table");
    $("commentTable").appendChild(newTable);

    for (var i = 0; i < response.commentList.length; i++) {

        newRow = newTable.insertRow(i);
        newCell = newRow.insertCell(0);
        newCell.innerHTML = response.commentList[i].comment_id;

        newCell = newRow.insertCell(1);
        newCell.innerHTML = response.commentList[i].userName;

        newCell = newRow.insertCell(2);
        newCell.innerHTML = response.commentList[i].comment;
        
        newCell = newRow.insertCell(3);
        newCell.innerHTML = response.commentList[i].comment_date;
        
        newCell = newRow.insertCell(4);
        newCell.innerHTML = response.commentList[i].teamAbbrv;

    }

    // done adding regular rows, add header row at top: 

   var newHead = newTable.createTHead();
    newRow = newHead.insertRow(0);

    newCell = newRow.insertCell(0);
    newCell.innerHTML = "Comment Id";

    newCell = newRow.insertCell(1);
    newCell.innerHTML = "User";
    
    newCell = newRow.insertCell(2);
    newCell.innerHTML = "Comments";
    
    newCell = newRow.insertCell(3);
    newCell.innerHTML = "Comment Date";
    
    newCell = newRow.insertCell(4);
    newCell.innerHTML = "Team";

}
