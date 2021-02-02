package com.codegym.controller;

import com.codegym.entity.*;
import com.codegym.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;



    @GetMapping()
    public String home(Model model) {
        model.addAttribute("questionList", questionService.findAll());
        return "customer/home";


    }

    @GetMapping("/create")
    public String create(Model model) {
        List<QuestionType> typeCustomerList = this.questionService.findAllQuestionType();
        model.addAttribute("questionTypeList", typeCustomerList);

        List<User> userList = this.questionService.findAllUser();
        model.addAttribute("userList", userList);

        List<UserFeedback> userFeedbackList = this.questionService.findAllUserFeedback();
        model.addAttribute("userFeedbackList", userFeedbackList);

        List<Status> statusList = this.questionService.findAllStatus();
        model.addAttribute("statusList", statusList);

        model.addAttribute("newQuestion", new Question());
        return "customer/create";

    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute(value = "newQuestion") Question question, BindingResult bindingResult, Model model,
                       RedirectAttributes redirect) {
        boolean check = false;
        List<Question> customerList = this.questionService.findAll();


        if (bindingResult.hasFieldErrors() || check) {
            model.addAttribute("questionList", this.questionService.findAll());
            List<QuestionType> typeCustomerList = this.questionService.findAllQuestionType();
            model.addAttribute("questionTypeList", typeCustomerList);

            List<User> userList = this.questionService.findAllUser();
            model.addAttribute("userList", userList);

            List<UserFeedback> userFeedbackList = this.questionService.findAllUserFeedback();
            model.addAttribute("userFeedbackList", userFeedbackList);

            List<Status> statusList = this.questionService.findAllStatus();
            model.addAttribute("statusList", statusList);

            return "customer/create";
        }


        questionService.save(question);
        redirect.addFlashAttribute("message", "Create Successfully");
        return "redirect:/question";
    }


    @GetMapping("/{id}/view")
    public String view(@PathVariable Integer id, Model model) {

        List<QuestionType> typeCustomerList = this.questionService.findAllQuestionType();
        model.addAttribute("questionTypeList", typeCustomerList);

        List<User> userList = this.questionService.findAllUser();
        model.addAttribute("userList", userList);

        List<UserFeedback> userFeedbackList = this.questionService.findAllUserFeedback();
        model.addAttribute("userFeedbackList", userFeedbackList);

        List<Status> statusList = this.questionService.findAllStatus();
        model.addAttribute("statusList", statusList);

        model.addAttribute("view", questionService.findById(id));
        return "customer/view";
    }


    @PostMapping("/delete")
    public String deleteProduct(@RequestParam Integer deleteId, RedirectAttributes redirectAttributes) {
        this.questionService.remove(deleteId);
        redirectAttributes.addFlashAttribute("message", "Delete  Complete !");
        return "redirect:/question";
    }

}
