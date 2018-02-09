package com.riri.jbclibrary.controller;

import com.riri.jbclibrary.model.Book;
import com.riri.jbclibrary.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class BookController {

    @Autowired
    BookRepo bookRepo;

    @RequestMapping("/")
    public String libraryHome(Model model){
        model.addAttribute("books",bookRepo.findAll());
        return "index";
    }

    @RequestMapping("/index")
    public String indexHome(Model model){
        model.addAttribute("books",bookRepo.findAll());
        return "index";
    }

    @GetMapping("/addbook")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
            return "addbook";
    }

    @PostMapping("/postaddbook")
    public String postAddBook(@Valid @ModelAttribute("book") Book book,
                              BindingResult result){
        if(result.hasErrors()){
            return "addbook";
        }
        bookRepo.save(book);
        return "redirect:/addbook";
    }

    @GetMapping("/listbook")
    public String listBook(Model model){
        model.addAttribute("books", bookRepo.findAll());
        return "listbook";
    }


    @GetMapping("/borrowbook")
    public String borrowBook(Model model){
        model.addAttribute("availables", bookRepo.findByisAvailableTrue());

        return "borrowbook";
    }

    @RequestMapping(value="/borrowbook",method = RequestMethod.POST)
    //@PostMapping("/borrowbook")
    public String postBorrowBook(@RequestParam("id") Long id,Model model){
      Book toBeBorrowed = bookRepo.findOne(id);
      toBeBorrowed.setAvailable(false);
      bookRepo.save(toBeBorrowed);
      return "redirect:/";
    }


    @GetMapping("/returnbook")
    public String returnBook(Model model){
        model.addAttribute("notavailables", bookRepo.findByisAvailableFalse());
        return "returnbook";
    }

    //@PostMapping("/postreturnbook")
    @RequestMapping(value="/returnbook",method = RequestMethod.POST)
    public String postReturnBook(@RequestParam("id") Long id, @ModelAttribute("book") Book book,Model model){
        Book returned = bookRepo.findOne(id);
        returned.setAvailable(true);
        bookRepo.save(returned);
        return "redirect:/";
    }

}
