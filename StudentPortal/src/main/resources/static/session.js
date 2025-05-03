function getSession(){
    fetch("/session",
        {
            method: "GET",
        }).then((response) => response.json()).then((json) => {return(json);});
}