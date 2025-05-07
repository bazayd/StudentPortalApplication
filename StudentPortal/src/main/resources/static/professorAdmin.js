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