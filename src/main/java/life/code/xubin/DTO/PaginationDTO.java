package life.code.xubin.DTO;

import java.util.ArrayList;
import java.util.List;

public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showFirstPage;
    private boolean showEndPage;
    private boolean showPrevious;
    private boolean showNext;
    private Integer pageNum;
    private List<Integer>pages= new ArrayList<>();
    private int totalPage;
    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public boolean isShowFirstPage() {
        return showFirstPage;
    }

    public void setShowFirstPage(boolean showFirstPage) {
        this.showFirstPage = showFirstPage;
    }

    public boolean isShowEndPage() {
        return showEndPage;
    }

    public void setShowEndPage(boolean showEndPage) {
        this.showEndPage = showEndPage;
    }

    public boolean isShowPrevious() {
        return showPrevious;
    }

    public void setShowPrevious(boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNumber(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setpagination( int pageNum, int totalPage) {




        this.totalPage=totalPage;
        if(pageNum<1){
            pageNum=1;
        }
        if(pageNum>totalPage){
            pageNum=totalPage;
        }
        this.pageNum=pageNum;
        pages.add(pageNum);
        for (int i=1;i<=3;i++){
            if (pageNum-i>0){
                pages.add(0,pageNum-i);
            }

            if (pageNum+i<=totalPage) {
                pages.add(pageNum+i);
            }
        }
        if(pageNum==1){
            showPrevious=false;
        }else {
            showPrevious=true;
        }
        if (pageNum==totalPage){
            showNext=false;
        }else {
            showNext=true;
        }
        if(pageNum-3>1){
            showFirstPage=true;
        }else {
            showFirstPage = false;
        }
        if(pageNum<totalPage-3){
            showEndPage=true;
        }else {
            showEndPage=false;
        }







    }
}
