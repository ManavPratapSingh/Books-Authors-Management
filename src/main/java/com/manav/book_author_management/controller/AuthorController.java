package com.manav.book_author_management.controller;

import com.manav.book_author_management.entity.Author;
import com.manav.book_author_management.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "authors/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        return "authors/form";
    }

    @PostMapping
    public String createAuthor(@Valid @ModelAttribute("author") Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "authors/form";
        }
        authorService.createAuthor(author);
        return "redirect:/authors";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.getAuthorById(id));
        return "authors/form";
    }

    @PostMapping("/update")
    public String updateAuthor(@Valid @ModelAttribute("author") Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "authors/form";
        }
        authorService.updateAuthor(author);
        return "redirect:/authors";
    }
}
