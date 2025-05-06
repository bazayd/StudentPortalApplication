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

    const result = await response.json();
    
    displayCourses(result);
});

function displayCourses(data){
    let dataDisplay = data.map((object) => {
        return `
        <div class="container">
            <p>Course Name: ${object.courseName}</p>
        </div>
        `
    }).join("");

    document.getElementById("coursesDisplay").innerHTML = dataDisplay;
}