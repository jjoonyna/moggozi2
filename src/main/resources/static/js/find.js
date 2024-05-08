const container = document.getElementById('container');
const idFindBtn = document.getElementById('idfind');
const pwFindBtn = document.getElementById('pwfind');

idFindBtn.addEventListener('click', () => {
    container.classList.add("active");
});

pwFindBtn.addEventListener('click', () => {
    container.classList.remove("active");
});