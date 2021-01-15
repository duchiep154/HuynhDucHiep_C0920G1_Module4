package com.codegym.controller;

import com.codegym.entity.User;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"","/user"})
public class UserController {
    @Autowired
    private UserService userService;

//    @GetMapping("/")
//    public String home(Model model, RedirectAttributes redirectAttributes){
//        List<User> userList = this.userService.findAll();
//        model.addAttribute("userList", userList);
//        redirectAttributes.addFlashAttribute("message","");
//        return "home";
//    }
    @GetMapping("/")
    public String home() {
        return "redirect:/user";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("userSave",new User());
        return "create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute(value = "userSave") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasFieldErrors()){
            model.addAttribute("userSave",new User());
            return "create";
        }

        userService.save(user);
        redirectAttributes.addFlashAttribute("message","Create Successfully");
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model){
        model.addAttribute("userEdit", userService.findById(id));
        return "edit";
    }

    @PostMapping("/update")
    public String update (@Valid @ModelAttribute(value = "userEdit") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasFieldErrors()){
            model.addAttribute("userEdit",new User());
            return "edit";
        }

        userService.update(user);
        redirectAttributes.addFlashAttribute("message","Update Successfully");
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String remove(@PathVariable int id, Model model){
        model.addAttribute("userDelete", userService.findById(id));
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(User user, RedirectAttributes redirectAttributes){
        userService.remove(user.getId());
        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
        return "redirect:/";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model){
        model.addAttribute("userView",userService.findById(id));
        return "detail_view";
    }

      @GetMapping
    public String viewHomePage(Model model,@Param("keyword") String keyword) {
        String keywordOld="";
        if (keyword==null){
            keyword="";
        }
        model.addAttribute("keyword",keyword);

        return viewPage(model, 1,keyword);


    }
    @RequestMapping("/page/{pageNum}")
    public String viewPage(Model model,
                           @PathVariable(name = "pageNum") int pageNum, @RequestParam(value = "") String keyword
    ) {

        Page<User> page = userService.findByfirtNameOrlastNameContaining(pageNum,keyword,keyword);


        List<User> listUsers = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("userList", listUsers);
        model.addAttribute("keyword", keyword);
        return "home";
    }

//    @GetMapping
//    public String home(@PageableDefault(value = 3) Pageable pageable, @RequestParam Optional<String> keyword, Model model) {
//        String keywordOld = "";
//
//        if (!keyword.isPresent()) {
//            model.addAttribute("user", this.userService.findAll(pageable));
//        } else {
//            keywordOld = keyword.get();
//            model.addAttribute("user", this.userService.findByfirtNameOrlastNameContaining(pageable,keyword.get(),keyword.get()));
//            model.addAttribute("keywordOld", keywordOld);
//
//        }
//        return "home";
//    }
}