async function loadMessages(){
    const response = await fetch("/get-messages", {
        method: "POST",
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    });
    try{
        const result = await response.json();
        displaySections(result);
    }
    catch(error) {
        alert("You must be logged in to view your messages!");
        return;
    }
}


function displaySections(data){
    let dataDisplay = data.map((object) => {
        return `
        <div class="Section-Container">
            <p>${object.professorName} - <b>${object.messageTitle}</b> (Sent On: ${object.messageDate})</p>
            <p>${object.messageBody}</p>
        </div>
        `
    }).join("");

    document.getElementById("messages").innerHTML = dataDisplay;
}
