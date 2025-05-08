document.getElementById("studentSearchForm").addEventListener("submit", async function(e) {
    e.preventDefault();
    
    const formData = new FormData(this);
    var sID = formData.get("StudentID");
    const data = Object.fromEntries(formData);
    const urlData = new URLSearchParams(data);

    const response = await fetch("/student-grades", {
        method: "POST",
        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
        body: urlData
    })

    const result = await response.json();
    displayGrades(result,sID);

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

async function addHold(studentID){
    urlData = new URLSearchParams({ 'StudentID': studentID });

    const response = await fetch("/add-hold", {
        method: "POST",
        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
        body: urlData
    })
}

async function remHold(studentID){
    urlData = new URLSearchParams({ 'StudentID': studentID });

    const response = await fetch("/rem-hold", {
        method: "POST",
        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
        body: urlData
    })
}

function displayGrades(data, studentID) {

    let holdSettings = `
        <div>
            <button onclick="addHold(${studentID})">Add Hold</button>
            <button onclick="remHold(${studentID})">Remove Hold</button>
        </div>
    `;
    
    let dataDisplay = data.map((object) => {
        return `
            <div class="Section-Container">
                <p>Course Name: ${object.courseName}</p>
                <p>Grade: ${object.grade}</p>
                <input id="newGrade-${object.courseID}-${object.studentID}" type="text" id="newGrade" name="newGrade">
                <button onclick="updateGrade(${object.courseID},${object.studentID})">Update Grade</button>
                </form>
            </div>
        `
    }).join("");

    document.getElementById("holdsDisplay").innerHTML = holdSettings;
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
        <div class="Section-Container">
            <p>Course: ${object.courseName}</p>
            <p>Student Name: ${object.studentName}</p>
            <p>Student ID: ${object.studentID}</p>
            <p>Student Grade: ${object.grade}</p>
        </div>
        `
    }).join("");

    document.getElementById("studentsDisplay").innerHTML = dataDisplay;

}