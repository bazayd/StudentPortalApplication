async function getStudentSession() {
    const response = await fetch("/student-details");
    const json = await response.json();
    return json;
}

async function getProfessorSession() {
    const response = await fetch("/professor-details");
    const json = await response.json();
    return json;
}

function populateUserData(){
    getStudentSession().then((student) => {
        // Input Student Data
        if(student!=0){
            document.getElementById("UserName").textContent = student.name;
            document.getElementById("UserID").textContent = student.studentID;
        }
    })
    getProfessorSession().then((professor) => {
        // Input Professor Data
        if(professor!=0){
            document.getElementById("UserName").textContent = professor.name;
            document.getElementById("UserID").textContent = professor.professorID;
        }  
    })
}
