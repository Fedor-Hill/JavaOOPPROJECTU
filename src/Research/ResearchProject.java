// the resaerch project itself that they are working on it will include the participaants, the title of the project, maybe the papers that they would be using , h index measures  how good the researcher i s based on their most cited paper
package Research;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Exceptions.NotResearcherException;

public class ResearchProject implements Serializable{
    
    private String RPtopic;
    private List<Researcher>participants;
    private List<ResearchPaper>publishedPapers;



      public ResearchProject(String RPtopic) {
        this.RPtopic = RPtopic;
        this.publishedPapers = new ArrayList<>();
        this.participants = new ArrayList<>();
    }


     public ResearchProject(String RPtopic, List<Researcher> participants) {
        this.RPtopic = RPtopic;
        this.publishedPapers = new ArrayList<>();
        this.participants = new ArrayList<>(participants);
    }


    public String getRPtopic(){
        return RPtopic;
    }
    public void setRPtopic(String RPtopic){
        this.RPtopic=RPtopic;
    }


    public List<ResearchPaper>getPublishedPapers(){
        return publishedPapers;
    }

    public List<Researcher>getParticipants(){
        return participants;
    }


    public void addPaper(ResearchPaper paper){
        publishedPapers.add(paper);
        System.out.println("paper is added to the project "+RPtopic);

    }

    public void addPartiicipant(Object person)throws NotResearcherException{
        if(!(person instanceof Researcher)){
            throw new  NotResearcherException("only researchers can join ");
        }
        participants.add((Researcher)person);
        System.out.println("new researcher joined the project");
    }

    public void removeParticipant(Researcher researcher){
        participants.remove(researcher);
        System.out.println("researcher removed from the project");
    }



}
