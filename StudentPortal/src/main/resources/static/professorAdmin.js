document.getElementById("studentSearchForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const formData = new FormData(this);
    const data = Object.fromEntries(formData);
    const urlData = new URLSearchParams(data);

    try {
        const response = await fetch("/student-grades", {
       async function getStudents() {
           try {
               const response = await fetch("/get-students", {
                   method: "POST",
                   headers: { "Content-Type": "application/x-www-form-urlencoded" }
               });

               if (!response.ok) {
                   console.error("Failed to fetch students.");
                   alert("Error fetching students.");
                   return;
               }

               const students = await response.json();
               displayStudents(students);
           } catch (error) {
               console.error("Error fetching students:", error);
           }
       }

       function displayStudents(students) {
           const studentDisplayHTML = students.map(student => {
               return `
                   <div>
                       <p><strong>Student ID:</strong> ${student.studentID}</p>
                       <p><strong>Name:</strong> ${student.name}</p>
                   </div>
               `;
           }).join("");

           const studentDisplayElement = document.getElementById("studentsDisplay");
           studentDisplayElement.innerHTML = studentDisplayHTML;
       }

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


           document.querySelectorAll(".update-grade-button").forEach(button => {
               button.addEventListener("click", async function () {
                   const studentID = this.dataset.studentId;
                   const sectionID = this.dataset.sectionId;
                   const gradeInput = document.querySelector(
                       `.grade-input[data-student-id="${studentID}"][data-section-id="${sectionID}"]`
                   );

                   const newGrade = gradeInput.value.trim();

                   if (!newGrade) {
                       alert("Grade cannot be empty.");
                       return;
                   }

                   try {
                       const response = await fetch("/update-student-grade", {
                           method: "POST",
                           headers: { "Content-Type": "application/x-www-form-urlencoded" },
                           body: `StudentID=${encodeURIComponent(studentID)}&SectionID=${encodeURIComponent(sectionID)}&Grade=${encodeURIComponent(newGrade)}`
                       });

                       if (response.ok) {
                           alert("Grade updated successfully!");
                       } else {
                           alert("Failed to update grade. Please try again.");
                       }
                   } catch (error) {
                       console.error("Error updating grade:", error);
                       alert("An error occurred while updating the grade.");
                   }
               });
           });
       }

       getStudents();
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