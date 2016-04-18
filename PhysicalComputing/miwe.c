#include <stdio.h> // binde Deklarationen ein (I/O)
#include <stdlib.h>
#include <ctype.h>
#include <math.h>



int main(int argc, char *argv[]){
	
	//Ueberpruefung, ob Argumente uebergeben wurden
	if (argc <= 1) {
	printf("No arguments given! \n");
	} else {

		double  miwe = 0;
	
		int invalidArgs = 1;
		//summierung der einzelnen Elemnte von argv
		for(int i=1; i<argc; i++){
				//char arg = *argv[i];
				if (isdigit(*argv[i]) || *argv[i] == '-'){
					miwe += atof(argv[i]);
				} else {
					printf("Only digits are allowed as argument! The non-digit Input will be ignored \n");
					invalidArgs++;
				}
				
	 	 }
	
		//Berechnung des Mittelwertes
		miwe /= argc-invalidArgs;
		
		//Ausgabe
		if (isnan(miwe)){
			printf("No valid arguments \n");
		} else {
			printf("mean value is: %f \n", miwe);
		}
		
	}
	
 	return 0; // melde dem Betriebsystem Erfolg
}
