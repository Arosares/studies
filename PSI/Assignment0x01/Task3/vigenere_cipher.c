#include <ctype.h>
#include <stdio.h>
#include <string.h>

int getRotation(char c) {
  /*According to ascii 'A' transfers to 65
  and Z to 90. By subtracting 65 of the char we get
  the rotation. */
  return c - 65;
}

int main(int argc, char *argv[]) {
  char key[256];
  char word[256];

  printf("Type in the key\n");
  fgets(key, sizeof(key), stdin);

  printf("What do you want to encrypt?\n");
  fgets(word, sizeof(word), stdin);

  // Number of char that were not uppercase
  int cntSkipped = 0;
  // length of the key - 1 to know when to start from 0
  int keyLength = strlen(key) - 1; // remove \n
  int keyPosition = 0;             // index of the key word

  for (int i = 0; i < strlen(word); i++) {
		/* Set keyPosition to 0 when end of key is reached
		i is substracted by the number of skipped chars
		so the % operator works as intended */
    if ((i > 0) & ((i - cntSkipped) % keyLength == 0)) {
      keyPosition = 0;
    }
    // ignore lowercases and whitespaces
    if (!isupper(word[i])) {
      cntSkipped++;
      continue;
    }

    int rotation = getRotation(key[keyPosition]);
    keyPosition++;

#ifdef DEBUG
    printf("%i ", rotation);
#endif

    word[i] = ((word[i] - 'A' + rotation) % 26) + 'A';
  }
  printf("%s", word);

  return 0;
}
