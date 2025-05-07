document.getElementById("studentSearchForm").addEventListener("submit", async function(e) {
    e.preventDefault();

    const formData = new FormData(this);
    const data = Object.fromEntries(formData);
    const urlData = new URLSearchParams(data);

    const response = await fetch("/grades/{studentID}", {
        method: "POST",
        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
        body: urlData
    })

    const result = await response.json();

    displayGrades(result);

})

function displayGrades(data) {
     let dataDisplay = data.map((object) => {
            return `
            <div class="container">
                <p>Student ID: ${object.StudentID}</p>
                <p>Grades: ${object.Grades}</p>
            </div>
            `
     }).join("");
}