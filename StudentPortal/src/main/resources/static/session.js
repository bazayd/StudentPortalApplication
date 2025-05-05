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
            document.getElementById("StudentSessionJSON").textContent = JSON.stringify(student);
        }
    })
    getProfessorSession().then((professor) => {
        // Input Professor Data
        if(professor!=0){
            document.getElementById("ProfessorSessionJSON").textContent = JSON.stringify(professor);
        }  
    })
}
