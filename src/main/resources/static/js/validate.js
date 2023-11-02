ristorantiDaCancellare = new Array();
ristorantiDaAggiungere = new Array();

/*
XML
<ristorante>
    <nome>
        Gironi ristopasti
    </nome>
    <descrizione>
        Pizze
    </descrizione>
    <ubicazione>
        Cosenza
    </ubicazione>
</ristorante>
*/
/*
  [
   {
        "nome": "Gironi ristopasti",
        "descrizione": "Pizze",
        "ubicazione": "Cosenza"
   },
   {
        "nome": "Gironi ristopasti secondi",
        "descrizione": "Pizze Gourmet",
        "ubicazione": "Rende"
   }
   ]
 */

class Ristorante{
    constructor(nome, descrizione, ubicazione) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.ubicazione = ubicazione;
    }
    getNome(){
        return this.nome;
    }
}

window.addEventListener("load", function() {
    var butRimuovi = document.querySelector("#btn_cancella");
    butRimuovi.addEventListener("click", function () {
        rimuoviRistorante();
    });

    var butVerifica = document.querySelector("#btn_verifica");
    butVerifica.addEventListener("click", function () {
        verificaRistorante();
    });

    var butAggiungi = document.querySelector("#btn_aggiungi");
    butAggiungi.addEventListener("click", function () {
        aggiungiRistorante();
    });

    var butSalva = document.querySelector("#btn_salva");
    butSalva.addEventListener("click", function () {
        salva();
    });


});

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
        nuovoRistorante = new Ristorante(nomeRist, descRist, ubRist);
        ristorantiDaAggiungere.push(nuovoRistorante);

        let newTr = document.createElement("tr");

        let newTdChk = document.createElement("td");
        let newTdId = document.createElement("td");
        let newTdNome = document.createElement("td");
        let newTdDescrizione = document.createElement("td");
        let newTdUbicazione = document.createElement("td");

        let contentNome = document.createTextNode(nomeRist);
        let contentDescrizione = document.createTextNode(descRist);
        let contentUbicazione = document.createTextNode(ubRist);

        newTr.appendChild(newTdChk);
        newTr.appendChild(newTdId);
        newTr.appendChild(newTdNome);
        newTr.appendChild(newTdDescrizione);
        newTr.appendChild(newTdUbicazione);

        newTdNome.appendChild(contentNome);
        newTdDescrizione.appendChild(contentDescrizione);
        newTdUbicazione.appendChild(contentUbicazione);

        newTr.style = "font-weight: bold";

        let tableElement = document.getElementsByTagName("tbody")[0];
        tableElement.appendChild(newTr);
    }else{
        alert("Mancano dei campi");
    }
}

function rimuoviRistorante(){
    var ristorantiSelezionati = document.querySelectorAll(".ristoranteSelezionato:checked");
    ristorantiSelezionati.forEach(function (elementSelected){
        // for each elementSelected in ristorantiSelezionati:
        var valueSel = elementSelected.getAttribute("value");

        var riga = document.querySelector("tr#r" + valueSel);
        riga.style = "text-decoration: line-through";

        ristorantiDaCancellare.push(valueSel);
    });
}

function salva(){
    var ristJson = JSON.stringify(ristorantiDaAggiungere);
    console.log(ristJson);
    $.ajax({
        url: "addRistorante",
        type: "POST",
        data: ristJson,
        contentType: "application/json",
        success: function(risposta) {
            //alert(risposta);
            if (risposta == "OK") {
                //alert("SI");
                let tutteLeRighe = document.querySelectorAll("tr");
                tutteLeRighe.forEach(function (riga) {
                    riga.style.removeProperty("font-weight");
                    }
                );
            }
        }
    });
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