### Work Status
###### (*Application - Diagrams - Documentation*)  - **Meiramkhan Alinur** - Done
- [x] Work Demo 
- [x] Check that Draft Demo is work 
- [x] Done Diagrams
- [x] Use Case 
- [x] Documentation and other papers papers 
- [x] Check Requirements 
- [x] Report Generator 
- [x] TUI

###### *Adamdar* - **Alina Kim** - Done
- [x] Adam and Eva
- [x] Sub classes (Teacher, Manager, Student, Proctor and etc)
- [x] idk

###### *AcademicThings* - **Azimbay Zhanel** - Done
- [x] Write classes
- [x] Add Lesson Type 
- [x] Add schedule 
- [x] update your diagram view

###### *Research* - **Alazab Alaa** - Done
- [x] Write classes 
- [x] Fills fields for researcher papers https://ieeexplore.ieee.org/document/9766691 . Take 5-10 important ones, citations, name, authors, journal, pages, date , doi etc.
- [x] Add comparator that prints his research papers in sorted order, dictated by the comparator - by date published or by citations or by the article length (use pages).
- [x] Print ALLLL PAPERS 


---
### Project File Structure 

## Project Structure

```text
src/
├── 🎓 AcademicThigns/          
│   ├── Attestation.java
│   ├── Course.java
│   ├── Enrollment.java
│   ├── Lesson.java
│   ├── LessonType.java
│   ├── Major.java
│   ├── Mark.java
│   ├── Room.java
│   ├── ScheduledLesson.java
│   └── Subject.java
├── 👥 Adamdar/                 
│   ├── Adam.java               
│   ├── Employee.java
│   ├── Student.java
│   ├── Teacher.java
│   ├── Admin.java
│   ├── Dean.java
│   ├── Manager.java
│   ├── Proctor.java
│   └── Eva.java
├── ⚙️ Application/             
│   ├── DataStorage.java        
│   ├── IdGenerator.java
│   ├── LangManager.java        
│   ├── Printer.java
│   ├── TUIConsole.java         
│   ├── Main.java
│   ├── VVSP.java
│   ├── LoginMenu.class         
│   ├── Request.java            
│   ├── RequestHandler.java
│   ├── AdamdarCreationRequest.java
│   └── SubjectRegistrationManager.java
├── 📊 Enums/                   
│   ├── ACCESS_RIGHT.java
│   ├── COMPLAINT_PRIORITY.java
│   ├── COMPLAINT_STATUS.java
│   ├── GENDER.java
│   ├── MAJOR.java
│   ├── MANAGER_TYPE.java
│   ├── MESSAGE_TYPE.java
│   ├── PROGRAMS.java
│   ├── REQUEST_STATUS.java
│   ├── RoomType.java
│   ├── SCHOOLS.java
│   ├── STATUS.java
│   └── TEACHER_LVL.java
├── ⚠️ Exceptions/              
│   ├── CourseRegistrationException.java
│   ├── CreditLimitExceededException.java
│   ├── NotResearcherException.java
│   ├── ScheduleConflictException.java
│   └── SupervisorQualificationException.java
├── 🔬 Research/                
│   ├── Researcher.java         
│   ├── ResearchPaper.java      
│   ├── ResearchProject.java    
│   ├── ResearchDELO.java
│   ├── AdvancedSearch.java
│   ├── RecommendationLetter.java
│   ├── Startup.java
│   └── researchcomparator.java 
└── 🌐 resources/               
    ├── label_en.properties     
    ├── label_kk.properties     
    └── label_ru.properties     
``` 
