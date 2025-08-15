package mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import mvc.dao.BookDAO;
import mvc.dao.PersonDAO;

@Controller
@RequestMapping("/book-assignments")
public class BookAssignmentController {

    private final BookDAO bookDAO;

    @Autowired
    public BookAssignmentController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
    }
    // Обработка назначения
    @PostMapping("/{bookId}/assign")
    public String assignBook(@PathVariable("bookId") int bookId,
                             @RequestParam("personId") int personId) {
        bookDAO.assign(bookId, personId);
        return "redirect:/books/" + bookId;
    }

    // Освобождение книги
    @PostMapping("/{bookId}/release")
    public String releaseBook(@PathVariable("bookId") int bookId) {
        bookDAO.release(bookId);
        return "redirect:/books/" + bookId;
    }
}