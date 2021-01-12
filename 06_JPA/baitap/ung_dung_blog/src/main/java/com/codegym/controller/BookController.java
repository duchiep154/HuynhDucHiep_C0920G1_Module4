package com.codegym.controller;

import com.codegym.entity.Book;
import com.codegym.entity.Category;
import com.codegym.service.BookService;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"","/books"})
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String home(@PageableDefault(value = 3) Pageable pageable, @RequestParam Optional<String> keyword,  Model model) {
        String keywordOld = "";

        if (!keyword.isPresent()) {
            model.addAttribute("books", this.bookService.getAll(pageable));
        } else {
            keywordOld = keyword.get();
            model.addAttribute("books", bookService.findAllByAuthorContaining(pageable,keywordOld));
            model.addAttribute("category", this.categoryService.getAll());
            model.addAttribute("keywordOld", keywordOld);

        }
        return "books/list";
    }

    @GetMapping("/showcreate")
    public String showCreateNew(Model model) {
        model.addAttribute("booksNew", new Book());
        model.addAttribute("categoryList", this.categoryService.getAll());
        return "books/new";
    }

    @PostMapping("/savecreate")
    public String createNew(RedirectAttributes redirectAttributes,
                            @ModelAttribute("bookNew") Book book
    ) {
        this.bookService.create(book);
        redirectAttributes.addFlashAttribute("message", "Create Complete !");
        return "redirect:/books";

    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("book", this.bookService.findById(id));
        return "/books/view";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("edit", bookService.findById(id));
        model.addAttribute("categoryList", this.categoryService.getAll());
        return "books/edit";
    }

    @PostMapping("/update")
    public String update(RedirectAttributes redirectAttributes,
                         @ModelAttribute("edit") Book book
    ) {
        this.bookService.update(book);
        redirectAttributes.addFlashAttribute("message", "Update Complete !");
        return "redirect:/books";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam long deleteId, RedirectAttributes redirectAttributes) {
        this.bookService.delete(deleteId);
        redirectAttributes.addFlashAttribute("message", "Delete Book Complete !");
        return "redirect:/books";
    }

//    @GetMapping("/search")
//    public String search(@PageableDefault(value = 3) Pageable pageable, @RequestParam Optional<String> keyword,  Model model) {
//
//            String keywordOld = "";
//
//            if (!keyword.isPresent()) {
//                model.addAttribute("books", this.bookService.getAll(pageable));
//            } else {
//                keywordOld = keyword.get();
//                model.addAttribute("books", bookService.findAllByAuthorContaining(pageable,keywordOld));
//                model.addAttribute("category", this.categoryService.getAll());
//                model.addAttribute("keywordOld", keywordOld);
//
//            }
//            return "books/list";
//
//
//
//    }
}





