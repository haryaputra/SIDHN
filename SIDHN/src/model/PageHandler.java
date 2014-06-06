package model;


/**
 * @param firstRow
 * @param recordPerPage
 */
@SuppressWarnings("oracle.jdeveloper.java.tag-is-missing")
public class PageHandler {
    public PageHandler() {
        super();
    }
    
    int currentPage;
    int numberOfPage;
    
    public void setCurrentPage (int firstRow, int recordPerPage){
            this.currentPage = ((firstRow+recordPerPage)%recordPerPage);
        }
    public int getCurrentPage (){
            return this.currentPage;
        }
    //(int)Math.ceil((RowNum/recordPerPage))
    
    public void setNumberOfPage (int rowNum,int recordPerPage){
            this.numberOfPage=(int)Math.ceil((rowNum/recordPerPage));
        }
    public int getNumberOfPage (){
            return this.numberOfPage;
        }
}
