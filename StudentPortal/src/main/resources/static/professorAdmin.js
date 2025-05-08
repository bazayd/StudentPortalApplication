document.getElementById("studentSearchForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const formData = new FormData(this);
    const data = Object.fromEntries(formData);
    const urlData = new URLSearchParams(data);

    try {
        const response = await fetch("/student-grades", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: urlData
        });

        if (!response.ok) {
            alert("Failed to fetch grades. Please try again.");
            return;
        }

        const result = await response.json();
        displayGrades(result);
    } catch (error) {
        console.error("Error fetching grades:", error);
    }
});

function displayGrades(data) {
    console.log(data);

    const groupedStudentData = data.reduce((acc, object) => {
        if (!acc[object.studentID]) {
            acc[object.studentID] = [];
        }
        acc[object.studentID].push(object);
        return acc;
    }, {});

    // Render grades
    const dataDisplay = Object.entries(groupedStudentData)
        .map(([studentID, grades]) => {
            return `
                <div class="container">
                    <p><strong>Student ID:</strong> ${studentID}</p>
                    ${grades
                        .map(
                            (g) => `
                                <div class="grade-section">
                                    <p>Course/Section ID: ${g.sectionID}</p>
                                    <input type="text" class="grade-input"
                                          data-student-id="${studentID}"
                                          data-section-id="${g.sectionID}"
                                          value="${g.grade}">
                                    <button class="update-grade-button"
                                            data-student-id="${studentID}"
                                            data-section-id="${g.sectionID}">
                                            Update Grade
                                    </button>
                                </div>
                            `
                        )
                        .join("")}
                </div>
            `;
        })
        .join("");

    document.getElementById("gradesDisplay").innerHTML = dataDisplay;

    document.querySelectorAll(".update-grade-button").forEach((button) => {
        button.addEventListener("click", async function () {
            const studentID = this.dataset.studentId;