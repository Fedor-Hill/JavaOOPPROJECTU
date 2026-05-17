//creating a generated or custom recommendation letter 
package Research;

import java.io.Serializable;
import java.time.LocalDate;

public class RecommendationLetter implements Serializable {
    private Researcher recommender;
    private String researcherName;
    private String content;
    private LocalDate date;
    private String purpose;
    
    public RecommendationLetter(Researcher recommender, String researcherName, String purpose) {
        this.recommender = recommender;
        this.researcherName = researcherName;
        this.purpose = purpose;
        this.date = LocalDate.now();
        this.content = generateContent();
    }
    
    private String generateContent() {
        return String.format(
             "I am happy to recommend %s for %s. " +
            "%s is a talented researcher with strong skills in problem-solving, " +
            "data analysis, and scientific communication. I highly recommend them.",
            researcherName, purpose, researcherName
        );
    }
    
    public void setCustomContent(String content) {
        this.content = content;
    }
    
    public void printLetter() {
        System.out.println("RECOMMENDATION LETTER ");
        System.out.println("Date: " + date);
        System.out.println("Recommender: " + recommender.getClass().getSimpleName());
        System.out.println("For: " + researcherName);
        System.out.println("Purpose: " + purpose);
        System.out.println("\n" + content);
    }
    
   
    
    public String getContent() {
        return content;
    }
    
    public LocalDate getDate() {
        return date;
    }
    public String getResearcherName() {
    return researcherName;
}

    public String getPurpose() {
        return purpose;
}
}
