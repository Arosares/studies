#include <stdio.h> // binde Deklarationen ein (I/O)
#include <stdlib.h>



int main(int argc, char *argv[]){
	
	//Ueberpruefung, ob Argumente uebergeben wurden
	if (argc <= 1) {
	printf("Keine Argumente übergeben! \n");
	} else {

	double  miwe = 0;
	
	//summierung der einzelnen Elemnte von argv
	for(int i=1; i<argc; i++){
    		miwe += atof(argv[i]);
 	 }
	
	//Berechnung des Mittelwertes
	miwe /= argc-1;
	
	//Ausgabe
 	printf("Der Mittelwert ist: %f \n", miwe);

	}
 	return 0; // melde dem Betriebsystem Erfolg
}
