
//here we a re creating startups taht are based on the papers published, we will include teh name f the startup, a description of what it is, what industry is it in and contributing to, who are the founders, and what is their funding
package Research;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Startup implements Serializable {

    private String name;
    private String description;
    private String industry;
    private List<Researcher>founders;
    private List<ResearchPaper>PapersBO;
    private double funding;


    public Startup(String name,String description,String industry){
        this.name = name;
        this.description = description;
        this.industry = industry;
        this.founders = new ArrayList<>();
        this.PapersBO = new ArrayList<>();
        this.funding = 0.0;

    }

    public String getName(){
        return name;
    }
     public String getDescription(){
        return description;
    }
    public String getIndustry(){
        return industry;
    }
     public List<Researcher> getFounders(){
        return founders;
    }
     public List<ResearchPaper> getPapersBO(){
        return PapersBO;
    }
    public double getFunding(){
        return funding;
    }

    public void setName(String name){
        this.name=name;
    }
    public void setDescription(String description){
        this.description=description;
    }
     public void setFunding(double funding){
        this.funding=funding;
    }

    public void addFounder(Researcher researcher){
        this.founders.add(researcher); }


    public void addPaper(ResearchPaper paper) {
         this.PapersBO.add(paper);  
}


    public void data(){
        System.out.println("Startup: " + name);
        System.out.println("Industry: " + industry);
        System.out.println("Description: " + description);
        System.out.println("Funding: $" + funding);
        System.out.println("Founders: " + founders.size());
        System.out.println("Based on " + PapersBO.size() + " research papers");
    }


    @Override 
    public String toString(){
        return "Startup is"+name+" in the "+industry+" industry"+ " the funding is equal to "+funding;
    }
//comparing startups based on their names
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Startup that = (Startup) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
         return Objects.hash(name);
    }
    
  
}