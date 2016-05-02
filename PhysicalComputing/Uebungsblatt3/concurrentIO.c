/* Tobias Hecht, Frank Kessler */

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

#define NUM_THREADS 	1

void *perform_work(void *argument)
{
 	
 	int loop = 1;
 		
	while(loop){
		printf("Waiting for your Input!\n");
		char input;
		scanf(" %c", &input);

		if(input == 'q') {
			printf("Quitting..\n");
			loop = 0;
		}
	
		printf("Input: %c \n", input);
	}
 
	return NULL;
}

int main(void)
{
   pthread_t threads[NUM_THREADS];
   int thread_args[NUM_THREADS];
   int result_code, index;
 
   // create all threads one by one
   for (index = 0; index < NUM_THREADS; ++index) {
      thread_args[index] = index;
      
      result_code = pthread_create(&threads[index], NULL, perform_work, (void *) &thread_args[index]);
      assert(0 == result_code);
   }
 
   // wait for each thread to complete
   for (index = 0; index < NUM_THREADS; ++index) {
      // block until thread 'index' completes
      result_code = pthread_join(threads[index], NULL);
      
      assert(0 == result_code);
   }
 
   
   exit(EXIT_SUCCESS);
}
