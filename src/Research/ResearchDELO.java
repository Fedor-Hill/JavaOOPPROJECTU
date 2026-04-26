 //what it means to be a reseracher, like what makes you qualify, 
//teachers and studnets, bachelor studnets, employees can be researchers, this is decided by the manager or the admin, with setresearcher()
//professors(teachers) are always researchers
//papers and projects are gonna be lists since they might have worke don more than one paper, more than one project

//th eproject requires printPaper()sorted by (citations, date, length  )
package Research;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ResearchDELO {

    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;

    public ResearchDELO() {
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
    }

    public List<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    public List<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    public void setResearchPapers(List<ResearchPaper> papers) {
        this.researchPapers = papers;
    }

    public void setResearchProjects(List<ResearchProject> projects) {
        this.researchProjects = projects;
    }

    public void addResearchPaper(ResearchPaper paper) {
        researchPapers.add(paper);
    }

    public void addResearchProject(ResearchProject project) {
        researchProjects.add(project);
    }

    public int getHindex() {
        List<Integer> citations = new ArrayList<>();
        for (ResearchPaper paper : getResearchPapers()) {
            citations.add(paper.getCitations());
        }
        citations.sort(Collections.reverseOrder());
        int h = 0;
        for (int i = 0; i < citations.size(); i++) {
            if (citations.get(i) >= i + 1) {
                h = i + 1;
            } else {
                break;
            }
        }
        return h;
    }

    public int getTotalcitations() {
        int total = 0;
        for (ResearchPaper paper : getResearchPapers()) {
            total += paper.getCitations();
        }
        return total;
    }

    public int getPapercount() {
        return getResearchPapers().size();
    }

    public void printpapers(Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> sorted = new ArrayList<>(getResearchPapers());
        sorted.sort(comparator);
        System.out.println("we have " + getPapercount() + "papers");
        for (ResearchPaper paper : sorted) {
            System.out.println(paper);
        }
    }



    

    public static void printAllPapers(List<Researcher> Allresearchers, Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> Allpapers = new ArrayList<>();
        for (Researcher researcher : Allresearchers) {
            Allpapers.addAll(researcher.getResearchDELO().getResearchPapers());
        }
        Allpapers.sort(comparator);
        System.out.println("this researcher has " + Allpapers.size() + "papers");
        for (ResearchPaper paper : Allpapers) {
            System.out.println(paper);
        }
    }

    public static Researcher getTopCited(List<Researcher> researchers) {
        if (researchers == null || researchers.isEmpty()) return null;
        Researcher Topcited = researchers.get(0);
        for (Researcher r : researchers) {
            if (r.getResearchDELO().getTotalcitations() > Topcited.getResearchDELO().getTotalcitations()) {
                Topcited = r;
            }
        }
        return Topcited;
    }
}