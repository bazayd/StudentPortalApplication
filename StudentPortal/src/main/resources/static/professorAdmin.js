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

document.getElementById("completeSemester").addEventListener("submit", async function(e) {
    e.preventDefault();

    const response = await fetch("/complete-semester", {
        method: "POST",
        headers: { 'Content-Type': 'application/x-www-form-urlencoded'}
    })

})

function displayGrades(data) {

     console.log(data);
     const groupedStudentData = data.reduce((acc, object) => {
        if (!acc[object.studentID]) {
            acc[object.studentID] = [];
        }
        acc[object.studentID].push(object.grade);
        return acc;
     }, {});

    const dataDisplay = Object.entries(groupedStudentData).map(([studentID, grade]) => {
        return `
          <div class="container">
            <p>Student ID: ${studentID}</p>
            <p>Grades: ${grade.join(", ")}</p>
          </div>
        `
    }).join("");

    document.getElementById("gradesDisplay").innerHTML = dataDisplay;

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