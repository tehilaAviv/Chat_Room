//
document.addEventListener('DOMContentLoaded',refresh);
document.addEventListener('DOMContentLoaded',refreshUseers);
setTimeout(refresh, 10000);
setTimeout(refreshUseers, 10000);
//This function is responsible for displaying the last 5 messages entered by the user and updating every 10 seconds
function refresh() {
    setTimeout(refresh, 10000);
    fetch("/getMessages", {
        headers: {
            'Content-Type': 'application/json'
        },
        method: "GET"
    })
        .then(res => res.json())
        .then(response => {
            var s = " ";
            var i = 0;
            for (var i of response) {
                if (i != undefined)// Print the message and the username that wrote the message
                {
                    s += "<p>" + i.msg + " " + " <b>author:</b> " + i.userName + "</p>";
                }
            }
            document.getElementById("listMsg").innerHTML = s;
        })
        .catch(e => {
        });
}

//============================================================================
//This function is responsible for displaying all connected users and is updated every 10 seconds
function refreshUseers() {
    setTimeout(refreshUseers, 10000);
    fetch("/getUsersConnect", {
        headers: {
            'Content-Type': 'application/json'
        },
        method: "GET"
    })
        .then(res => res.json())
        .then(response => {
            var s = " ";
            var i = 0;
            for (var i of response) {
                if (i != undefined)// Print connected users
                {
                    s += "<p> " + i.userName + "</p>";
                    console.log(i.msg);
                }
            }
            document.getElementById("listUsersConnect").innerHTML = s;
        })
        .catch(e => {

        });
}

//============================================================================
//This function is responsible for displaying the appropriate messages for the username entered in the search
function  searchUser(){
    var userName=document.getElementById("userName").value;
    fetch("/searchUsersJson/"+userName, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: "GET"
    })
        .then(res => res.json())
        .then(response => {
            var s = " ";
            var i = 0;

            for (var i of response) {
                if (i != undefined)// Print the messages associated with the username entered in the search
                {
                    s += "<p> " + i.msg +  "</p>";
                }
            }
            document.getElementById("listUsers").innerHTML = s;

        })
        .catch(e => {
            //If there is no message from the user entered in the search, an error message will be displayed
            document.getElementById("listUsers").innerHTML = "no user found with the search";
        });

}

//============================================================================
//This function is responsible for displaying the messages that correspond to the content of the message that the user entered in the search
function  searchContentMsg(){
    var content =document.getElementById("word").value;
    fetch("/searchContentJson/"+content, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: "GET"
    })
        .then(res => res.json())
        .then(response => {
            var s = " ";
            var i = 0;
            for (var i of response) {
                if (i != undefined)// Print the messages associated with the username entered in the search
                {
                    s += "<p> " + i.msg +  "</p>";
                }
            }
            document.getElementById("listMessage").innerHTML = s;
        })
        //If there is no message from the user entered in the search, an error message will be displayed
        .catch(e => {
            document.getElementById("listMessage").innerHTML = "no message found with the search";
        });

}