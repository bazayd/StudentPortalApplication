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
        displayCourses(result);
    }
    catch {
        alert("You must be logged in to search section database!");
        return;
    }
    
});

async function registerForSection(SectionID) {
    urlData = new URLSearchParams({ 'SectionID': SectionID });

    const response = await fetch("/register-sections", {
        method: "POST",
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: urlData
    });

    const result = await response.json();

    alert(result);
}

function displayCourses(data){
    let dataDisplay = data.map((object) => {
        return `
        <div class="Section-Container">
            <button onclick="registerForSection(${object.sectionID})">Register For Section</button>
            <p>Course Name: ${object.courseName}</p>
        </div>
        `
    }).join("");

    document.getElementById("coursesDisplay").innerHTML = dataDisplay;
}