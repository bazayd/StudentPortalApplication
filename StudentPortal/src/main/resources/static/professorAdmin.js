document.getElementById("studentSearchForm").addEventListener("submit", async function(e) {
    e.preventDefault();

    const formData = new FormData(this);
    const data = Object.fromEntries(formData);
    const urlData = new URLSearchParams(data);

    const response = await fetch("/student-grades", {
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

    Document.getElementById("gradesDisplay").innerHTML = dataDisplay;

}

async function getStudents(){

    const response = await fetch("/get-students", {
        method: "POST",
        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
    })

    const result = await response.json();
    displayStudents(result);
}

function displayStudents(data) {
    let dataDisplay = data.map((object) => {
        return `
        <div class="container">
            <p>Course: ${object.courseName}</p>
            <p>Student Name: ${object.studentName}</p>
            <p>Student ID: ${object.studentID}</p>
            <p>Student Grade: ${object.grade}</p>
        </div>
        `
    }).join("");

    document.getElementById("studentsDisplay").innerHTML = dataDisplay;

}