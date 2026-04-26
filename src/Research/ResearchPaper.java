//the research paper itself with the all possible data like authors, pages, citatations, title, publisher,DOI(didgital object identifier), journal
package Research;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ResearchPaper implements Serializable{
   private String Title;
   private List<String> authors;   
   private int citations;
   private  int numberOfPages;
   private String DOI;
   private Date publicationDate;
   private String publisher;   
   private String journal; 




ResearchPaper(String Title, List<String> authors, int citations, int numberOfPages,String DOI, Date publicationDate, String publisher, String journal){

    this.Title=Title;
    this.authors=authors;
    this.citations=citations;
    this.numberOfPages=numberOfPages;
    this.DOI=DOI;
    this.publicationDate=publicationDate;
    this.publisher=publisher;
    this.journal=journal;



};

public String getTitle(){
    return Title;
}
void setTitle(String Title){
    this.Title=Title;
}


public List<String> getAuthors(){
    return authors;
}
void setAuthors(List<String> authors){
    this.authors=authors;
}


public int getCitations(){
    return citations;
}
void setCitations(int citations){
    this.citations=citations;
}

public int getNumberOfPages(){
    return numberOfPages;
}
void setNumberOfPages(int numberOfPages){
    this.numberOfPages=numberOfPages;
}
 

public String getDOI(){
    return DOI;
}
void setDOI(String DOI){
    this.DOI=DOI;
}


public Date getPublicationDate(){
    return publicationDate;
}
void setPublicationDate(Date publicationDate){
    this.publicationDate=publicationDate;
}

public String getPublisher(){
    return publisher;
}
void setPublisher(String publisher){
    this.publisher=publisher;
}


public String getJournal(){
    return journal;
}
void setJournal(String journal){
    this.journal=journal;
}


@Override
public String toString() {
    return "Paper: " + Title + "\n" + "Authors: " + authors + "\n" +
           "Citations: " + citations + "\n" + "Pages: " + numberOfPages + "\n" +
           "Published: " + publicationDate + "\n" + "DOI: " + DOI + "\n" +
           "Publisher: " + publisher + "\n" + "Journal: "+ journal;
}




@Override
public boolean equals(Object o){
    if(this==o)return true;
    if(o==null || getClass() != o.getClass()) return false;
    ResearchPaper that=(ResearchPaper)o;
    return Objects.equals(DOI, that.DOI); 
}



@Override
public int hashCode(){
    return Objects.hash(DOI);
}


    
}
