document.getElementById("sectionSearchForm").addEventListener("submit", async function(e) {
    e.preventDefault();

    const formData = new FormData(this);
    const data = Object.fromEntries(formData);
    const urlData = new URLSearchParams(data);

    const response = await fetch("/get-sections", {
        method: "POST",
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: urlData
    });

    try{
        const result = await response.json();
        displaySections(result);
    }
    catch(error) {
        alert("You must be logged in to search section database!");
        return;
    }
    
});

async function dropSection(SectionID) {
    urlData = new URLSearchParams({ 'SectionID': SectionID });

    const response = await fetch("/drop-sections", {
        method: "POST",
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: urlData
    });

    //const result = await response.text();
    loadRegisteredSections();
}

async function registerForSection(SectionID) {
    urlData = new URLSearchParams({ 'SectionID': SectionID });

    const response = await fetch("/register-sections", {
        method: "POST",
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: urlData
    });

    const result = await response.text();
    loadRegisteredSections();
    alert(result);
}

async function loadRegisteredSections(){
    const response = await fetch("/get-registered", {
        method: "POST",
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    });

    try{
        const result = await response.json();
        displayRegisteredSections(result);
    }
    catch {
        alert("You must be logged in to view your registered sections!");
        return;
    }
}

function displayRegisteredSections(data){
    let dataDisplay = data.map((object) => {
        return `
        <div class="Section-Container">
            <button onclick="dropSection(${object.sectionID})">Drop Section</button>
            <p>Course Name: ${object.courseName}</p>
        </div>
        `
    }).join("");

    document.getElementById("enrolledSectionsDisplay").innerHTML = dataDisplay;
}

function displaySections(data){
    let dataDisplay = data.map((object) => {
        return `
        <div class="Section-Container">
            <button onclick="registerForSection(${object.sectionID})">Register For Section</button>
            <p>Course Name: ${object.courseName}</p>
        </div>
        `
    }).join("");

    document.getElementById("availableSectionsDisplay").innerHTML = dataDisplay;
}



