//our project requires throwing an exception whenever a perosn who is nota researcher trie sto join the resaerch .
package Exceptions;

public class NotResearcherException extends Exception {
    
    public NotResearcherException() {
        super("Only researchers can participate in research projects");
    }
    
    public NotResearcherException(String message) {
        super(message);
    }
}