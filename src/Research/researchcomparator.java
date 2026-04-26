//we wilb be comparing the papers based on citatons how many time s it has been cited, based on publishing date, number of pages.
package Research;

import java.util.Comparator;

public class researchcomparator {

    public static class CitationSort implements Comparator<ResearchPaper>{
        @Override
        public int compare(ResearchPaper p1,ResearchPaper p2){
            return Integer.compare(p2.getCitations(),p1.getCitations());
        }
    }


    
    public static class DateSort implements Comparator<ResearchPaper>{
        @Override
        public int compare(ResearchPaper p1,ResearchPaper p2){
            return p2.getPublicationDate().compareTo(p1.getPublicationDate());
        }
    }


       public static class lengthSort implements Comparator<ResearchPaper>{
        @Override
        public int compare(ResearchPaper p1,ResearchPaper p2){
            return Integer.compare(p2.getNumberOfPages(),p1.getNumberOfPages());
        }
    }



    
}
