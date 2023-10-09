package es2;

interface AdvancedStack {
	
	interface Evaluate {
		
		/**
		 * The condition to test {@code objectToTest} against
		 * 
		 * @param objectToTest
		 * @return
		 */
		boolean verify(Object objectToTest);
	}
	

    /**
     * Push given object in the stack
     */
    void push(Object o);

    /**
     * Removes and returns the top element from the stack
     * 
     * @return
     */
    Object pop();

    /**
     * Returns the top element without removing it from the stack
     * 
     * @return
     */
    Object peek();

    /**
     * Returns the size of the stack
     * 
     * @return
     */
    long size();

    /**
     * Returns true if the stack is empty
     * 
     * @return
     */
    boolean isEmpty();
    
    /**
     * Tests each element inside the stack with {@code testLogic} and returns the count {@code testLogic} has returned true
     * 
     * @param testLogic
     * @return
     */
    long count(Evaluate testLogic);
}
