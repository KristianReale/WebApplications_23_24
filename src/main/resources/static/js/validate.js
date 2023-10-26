function verificaRistorante(){
    var nomeRistTxt = document.getElementById("nome_rist");
    var descRistTxt = document.getElementById("desc_rist");
    var ubRistElement = document.getElementById("ub_ristorante");

    var nomeRist = nomeRistTxt.value.trim();
    var descRist = descRistTxt.value.trim();
    var ubRist = ubRistElement.value.trim();

    var ok = validateRistorante(nomeRist, descRist, ubRist);
    if (!ok){
        alert("Mancano dei campi");
    }else{
        alert("Campi validati");
    }
}

function aggiungiRistorante(){
    var nomeRistTxt = document.getElementById("nome_rist");
    var descRistTxt = document.getElementById("desc_rist");
    var ubRistElement = document.getElementById("ub_ristorante");

    var nomeRist = nomeRistTxt.value.trim();
    var descRist = descRistTxt.value.trim();
    var ubRist = ubRistElement.value.trim();

    if (validateRistorante(nomeRist, descRist, ubRist)){
        let newTr = document.createElement("tr");

        let newTdNome = document.createElement("td");
        let newTdDescrizione = document.createElement("td");
        let newTdUbicazione = document.createElement("td");

        let contentNome = document.createTextNode(nomeRist);
        let contentDescrizione = document.createTextNode(descRist);
        let contentUbicazione = document.createTextNode(contentDescrizione);

        newTr.appendChild(newTdNome);
        newTr.appendChild(newTdDescrizione);
        newTr.appendChild(newTdUbicazione);

        newTdNome.appendChild(contentNome);
        newTdDescrizione.appendChild(contentDescrizione);
        newTdUbicazione.appendChild(contentUbicazione);

        let tableElement = document.getElementsByTagName("table")[0];
        tableElement.appendChild(newTrjjj);
    }else{
        alert("Mancano dei campi");
    }
}
function validateRistorante(nome, descrizione, ubicazione){
    var validationOK = true;
    if (nome == ""){
        validationOK = false;
    }
    if (descrizione == ""){
        validationOK = false;
    }else{
        if (descrizione.length < 10){
            alert("Il testo deve essere almeno 10 caratteri");
            validationOK = false;
        }
    }

    if (ubicazione === "---"){
        validationOK = false;
    }
    return validationOK;
}