/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysimulation;

/**
 *
 * @author ypf12pxu
 */
public class LibraryBook {
    
     public enum status {

       REFERENCE_ONLY,ON_LOAN, AVAILABLE_FOR_LENDING
    }
  final private String author;
 final private String title;
  final private int pages;
   status cStatus;
   private String classification;
   private int borrowed;
   private int reserve;
   private static int loan;
   public int onloan;
   
   public LibraryBook(String bookAuthor, String bookTitle, int bookPages)
   {
        author = bookAuthor;
        title = bookTitle;
        pages = bookPages;
        classification = null;
        borrowed = 0;
        reserve = 0;
        loan=0;
        
   }
   public int getloans()
   {
       return loan;
   }
  
           
   
   
     public String getAuthor()
    {
        return author;
    }
    public String getTitle()
    {
        return title;
    }
    public int getPages()
    {
        return pages; 
    }
    public String getClassification()
    {
        return classification;
    }
    public int getBorrowed()
    {
        return borrowed;
    }
    public status getStatus()
    {
        return cStatus;
    }
    public boolean setClassification(String bookClass)
    {
        if(bookClass.length()>=3)
        {
            classification=bookClass;
            return true;
        }
        else
        return false;
    }
    public boolean setAsReferenceOnly()
    {
        if(cStatus!=cStatus.AVAILABLE_FOR_LENDING )
        {
            cStatus = cStatus.REFERENCE_ONLY;
            return true;
        }
        else if(cStatus!=cStatus.REFERENCE_ONLY)
        {
            cStatus = cStatus.REFERENCE_ONLY;
            return true;
        }
        else
        return false;        

    }
    public boolean setAsForLending ()
    {
          if(cStatus!=cStatus.AVAILABLE_FOR_LENDING )
        {
            cStatus = cStatus.AVAILABLE_FOR_LENDING;
            return true;
        }
        else if(cStatus!=cStatus.REFERENCE_ONLY)
        {
             cStatus = cStatus.AVAILABLE_FOR_LENDING;
            return false;
        }
        else
        return false; 
        
    }
    public boolean isAvailable()
    {
        if(cStatus==cStatus.AVAILABLE_FOR_LENDING )
        {
            return true;
        }
        else if(cStatus==cStatus.REFERENCE_ONLY)
        {
            return false;
        }
        else
        return false; 
    }
    public boolean resrveBook()
    {
        
        if(cStatus==cStatus.ON_LOAN )
        {
            
            
            if(reserve >=3)
            {
             return false;
            }
           reserve++;
           return true;
         
            
        }
        return false;
    }
    public void borrowBook()
    {
        borrowed++;
             
        
       if(cStatus == cStatus.AVAILABLE_FOR_LENDING)
       {
         cStatus = cStatus.ON_LOAN;
         loan++;
         
       }
       
       
    }
    public boolean returnBook()
    {
        if(cStatus == cStatus.ON_LOAN)
        {
          cStatus  = cStatus.AVAILABLE_FOR_LENDING;
          loan--;
          return true;
        }
        return false;
    }
      
     @Override
   public String toString()
   {
       return "Title: " + title + "\n" +  "Author: "+ author + "\n" +"Pages: "+ pages +"\n"+ "Classification:" + classification;
   }
   
}
