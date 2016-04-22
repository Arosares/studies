#include <stdio.h> // binde Deklarationen ein (I/O)
#include <stdlib.h>
#include <ctype.h>
#include <math.h>

int main(int argc, char *argv[]){
	
	//Ueberpruefung, ob Argumente uebergeben wurden
	if (argc <= 1) {
		printf("No arguments given! Usage: ./'program' arg1 arg2 arg3 ...\n");
	} else {

		double  miwe = 0;
	
		int invalidArgs = 1;
		
		//summierung der einzelnen Elemnte von argv
		for(int i=1; i<argc; i++){
			// -0 and +0 is considered as invalid, so that -asd and +asd is not possible. Just 0 still works.
			if (isdigit(*argv[i]) || *argv[i] == '-' && atof(argv[i]) != -0 || *argv[i] == '+' && atof(argv[i]) != +0){
				miwe += atof(argv[i]);
			} else {
				printf("Only digits are allowed as argument!\tThe non-digit Input '%s' will be ignored\n", argv[i]);
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


