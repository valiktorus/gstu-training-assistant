package by.gstu.workout.utils;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
public class Pager<T> {
    private List<Long> availablePages;
    private long lastPage;
    private long currentPage;

    public Pager(Page<T> page) {
        int totalPages = page.getTotalPages();
        this.lastPage = totalPages == 0 ? 1 : totalPages;
        this.currentPage = page.getNumber() + 1;
        availablePages = new ArrayList<>();
        if (currentPage - 2 > 0){
            availablePages.add(currentPage - 2);
        }
        if (currentPage - 1 > 0){
            availablePages.add(currentPage - 1);
        }
        availablePages.add(currentPage);
        if (currentPage + 1 <= lastPage){
            availablePages.add(currentPage + 1);
        }
        if (currentPage + 2 <= lastPage){
            availablePages.add(currentPage + 2);
        }
    }
}
