//advancedsearch allows us to search for papers based on titles,authors,DOI and to search for projects based on the researchproject topic
package Research;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;



public class AdvancedSearch {
//Search for papers based on the papers title
    public static List<ResearchPaper> SearchTitleByRegex(List<ResearchPaper>papers,String regex){
        Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);

        List<ResearchPaper>results=new ArrayList<>();
        for(ResearchPaper paper:papers){
            if(pattern.matcher(paper.getTitle()).find()){
                results.add(paper);
            }
        }
        return results;
    }

    //search for authors based on title, here we go over the authors in one paer, since a paper has many authors, if the paper ofr an author was added, it doesnt add twice, if we come across another author who has particpated in it.

    public static List<ResearchPaper>SearchAuthorByRegex(List<ResearchPaper>papers, String regex){
        Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        List<ResearchPaper>results=new ArrayList<>();
        for (ResearchPaper paper : papers) {
            for(String author:paper.getAuthors()){
                if(pattern.matcher(author).find()){
                    results.add(paper);
                    break;
                }
            }
    }
      return results;
     
}


//search based on the DOI , the doi is unique and is case sensetive 
    public static List<ResearchPaper>SearchDOIByRegex(List<ResearchPaper>papers,String regex){
        Pattern pattern=Pattern.compile(regex);
        List<ResearchPaper>results=new ArrayList<>();
        for(ResearchPaper paper:papers){
            if(pattern.matcher(paper.getDOI()).find()){
                results.add(paper);
            }
        }
        return results;


    }

    //search based on the research project topic
    public static List<ResearchProject>SearchRPTByRegex(List<ResearchProject>Projects,String regex){
        Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        List<ResearchProject>results=new ArrayList<>();
        for(ResearchProject Project:Projects){
            if(pattern.matcher(Project.getRPtopic()).find()){
                results.add(Project);
            }
        }
        return results;
    }







}