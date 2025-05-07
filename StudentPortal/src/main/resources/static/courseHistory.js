async function loadCompletedCourses(){
    const response = await fetch("/get-completed", {
        method: "POST",
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    });

    try{
        const result = await response.json();
        displayCompletedCourses(result);
    }
    catch{
        alert("You must be logged in to view your registered sections!");
        return;
    }
}

function displayCompletedCourses(data) {
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

    document.getElementById("coursesDisplay").innerHTML = dataDisplay;

}