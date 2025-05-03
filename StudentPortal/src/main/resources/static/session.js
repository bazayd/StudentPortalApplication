var session = null;
function getSession(){
    fetch("/session",
        {
            method: "GET",
        }).then((response) => response.json()).then((json) => {
            session = JSON.parse(myJSON);
        });
}