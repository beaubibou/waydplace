
function valideActivite (description,titre,datedebut,datefin){
	

	if (datedebut > datefin) {
		
		return "La date de début est supérieure à la date de fin";
		
	}
	if (datefin < new Date()) {
		return "La date de fin est infèrieure à maintenant";
	}

	var diffHeure = heureDiff(new Date(datedebut).getTime(), new Date(
			datefin).getTime());

	if (diffHeure > 12) {
		
		return "La durée ne peut pas exeder 12 heures";
	}

		
	
	return 'ok';
	
}

function dateDiff(date1, date2) {
	var diff = {} // Initialisation du retour
	var tmp = date2 - date1;

	tmp = Math.floor(tmp / 1000); // Nombre de secondes entre les 2 dates
	diff.sec = tmp % 60; // Extraction du nombre de secondes

	tmp = Math.floor((tmp - diff.sec) / 60); // Nombre de minutes (partie entiÃ¨re)
	diff.min = tmp % 60; // Extraction du nombre de minutes

	tmp = Math.floor((tmp - diff.min) / 60); // Nombre d'heures (entiÃ¨res)
	diff.hour = tmp % 24; // Extraction du nombre d'heures

	tmp = Math.floor((tmp - diff.hour) / 24); // Nombre de jours restants
	diff.day = tmp;

	return diff;
}

function heureDiff(date1, date2) {

	var tmp = date2 - date1;

	tmp = Math.floor(tmp / 1000) / 3600;

	return tmp;

}

