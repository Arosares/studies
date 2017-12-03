#include <stdio.h>

int dostuff(int a, int b){

    while (a != 0) {
        if (a > b) {
            a = a - b;
        } else {
            b = b - a;
        }
        if (b == 0){
            return a;
        }        
    }
    return b;
}

int main (int argc, char *argv[]) {
    int a, b, c;

    printf("Enter two positive numbers seperated by a space: ");
    if (scanf("%d %d",&a, &b) != 2) {
        printf("Numbers, separated by space, I said!\n");
        return 1;
    }

    if (a < 0 || b < 0){
        printf("Positive, I said!\n");
        return -1;   
    }

    c = dostuff(a, b);
    printf("dostuff says: %d\n", c);
    
    return 0;
}

