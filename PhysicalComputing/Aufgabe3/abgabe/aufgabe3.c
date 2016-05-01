/* Tobias Hecht, Frank Kessler */

#include<stdio.h>
#include<string.h>
#include<math.h>

int main(int argc, char* argv[]) {
  char *argIn, *argOut; //Argumentvariablen
  FILE *fInput; //Inputfile
  FILE *fOutput;// Outputfile 
  int timeStamp = 0;
  float voltage = 0;
  float temperatureF = 0;
  float temperatureC = 0;

  if(argc == 3){ //Sind Input-/Outputfile bei Programmaufruf als Argument uebergeben?
    argIn = argv[1]; //Inputfile in Variable speichern
    argOut = argv[2];//Outputfile in Variable speichern

    fInput = fopen(argIn, "r"); //Inputfile oeffnen, "r" fuer read
    fOutput = fopen(argOut, "w");//Outputfile oeffnen, "w" fuer write

    if(fInput == NULL || fOutput == NULL) { //ueberpruefen ob Input-/Outputfile ok
      printf("Eingabe-/Ausgabedatei konnte nicht geoeffnet werden\n" );
      fclose(fInput); //Stream schliessen
      fclose(fOutput);//Stream schliessen
      return -1;
    }


    //Header in Outputfile drucken
    fprintf(fOutput,"%s \n","# Zeit in Sekunden;   Temperatur in Celsius");

    int args = 3; //3 gueltige Werte pro gelesener Zeile
    while(args == 3) { //iteriere Zeilenweise ueber Inputfile
      args = fscanf(fInput, "%d %f %f", &timeStamp, &voltage, &temperatureF);
      if(args) { //wenn 3 Argumente eingelesen wurden, konvertiere Temperaturformat und printe Werte in Outputfile
        temperatureC = (((temperatureF/10)-32)/1.8);
        fprintf(fOutput,"%d          %f \n",timeStamp,temperatureC);//Zeile mit konvertierter Temperatur in Outputfile schreiben
      } else {
        printf("Falsches Format!\n");
        return -1;
      }
    }
    fclose(fInput); //Stream schliessen
    fclose(fOutput);//Stream schliessen
    return 0;
  } else {//ungueltiger Aufruf
    printf("Bitte Eingabe-/Ausgabedatei angeben\n");
    return -1;
  }
}
