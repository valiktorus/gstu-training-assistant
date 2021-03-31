package by.gstu.workout.controller;

import by.gstu.workout.model.Book;
import by.gstu.workout.model.Pager;
import by.gstu.workout.service.BookService;
import by.gstu.workout.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private BookService bookService;
    @Autowired
    private GenreService genreService;

    @GetMapping(value = {"/", "/home"})
    public String booksPaging(@RequestParam Optional<Integer> page,
                              @RequestParam Optional<Integer> pageSize,
                              @RequestParam Optional<String> genre,
                              @RequestParam Optional<String> author,
                              @RequestParam Optional<String> sortedBy,
                              @RequestParam Optional<Sort.Direction> direction,
                              Model model) {
        int newPage = page.orElse(1);
        int currentPageSize = pageSize.orElse(2);
        String currentAuthor = author.orElse("");
        String currentGenre = genre.orElse("");
        String currentSortedByField = sortedBy.orElse("id");
        Sort.Direction currentDirection = direction.orElse(Sort.Direction.ASC);
        Page<Book> booksPage = bookService.getAllByGenreAndAuthor( currentGenre, currentAuthor,
                newPage - 1, currentPageSize, currentSortedByField, currentDirection);
        Pager<Book> pager = new Pager<>(booksPage);
        model.addAttribute("pager", pager);
        model.addAttribute("books", booksPage.getContent());
        model.addAttribute("genres", genreService.getAll());
        model.addAttribute("selectedGenre", currentGenre);
        model.addAttribute("sortedBy", currentSortedByField);
        model.addAttribute("author", currentAuthor);
        return "index";
    }

    @GetMapping(value = {"/books/{bookId}"})
    public String getBook(@PathVariable long bookId, Model model) {
        Book book = bookService.get(bookId);
        model.addAttribute("book", book);
        return "book-description";
    }
}
