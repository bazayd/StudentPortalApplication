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
    displayGrades(result,urlData);

})

async function updateGrade(courseID, studentID){
    var newGrade = document.getElementById("newGrade-"+courseID+"-"+studentID).value;

    urlData = new URLSearchParams({ 'CourseID': courseID, 'StudentID': studentID, 'NewGrade': newGrade });

    const response = await fetch("/modify-grade", {
        method: "POST",
        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
        body: urlData
    })

    const result = await response.json();

}


function displayGrades(data) {

    console.log(data);
    
    let dataDisplay = data.map((object) => {
        return `
            <div class="container">
                <p>Course Name: ${object.courseName}</p>
                <p>Grade: ${object.grade}</p>
                <input id="newGrade-${object.courseID}-${object.studentID}" type="text" id="newGrade" name="newGrade">
                <button onclick="updateGrade(${object.courseID},${object.studentID})">Update Grade</button>
                </form>
            </div>
        `
    }).join("");

    document.getElementById("gradesDisplay").innerHTML = dataDisplay;

}

document.getElementById("completeSemester").addEventListener("submit", async function(e) {
    e.preventDefault();

    const response = await fetch("/complete-semester", {
        method: "POST",
        headers: { 'Content-Type': 'application/x-www-form-urlencoded'}
    })

})

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