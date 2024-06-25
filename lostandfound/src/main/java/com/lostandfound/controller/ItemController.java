package com.lostandfound.controller;

import com.lostandfound.model.Item;
import com.lostandfound.service.ItemService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class ItemController {

    @Autowired
    private ItemService itemService;
 // Mapping to show home page (index.html) with all items
    @GetMapping("/")
    public String home(Model model) {
        List<Item> lostItems = itemService.findAllLostItems();
        List<Item> foundItems = itemService.findAllFoundItems();
        model.addAttribute("lostItems", lostItems);
        model.addAttribute("foundItems", foundItems);
        return "index";
    }

    // Mapping to show the form for reporting lost item
    @GetMapping("/report-lost")
    public String showReportLostForm(Model model) {
        model.addAttribute("item", new Item());
        return "lost_form"; // assuming Thymeleaf template name
    }

    // Mapping to handle form submission for reporting lost item
    @PostMapping("/report-lost")
    public String reportLost(@Valid Item item,
                             @RequestParam("image") MultipartFile imageFile,
                             BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return "lost_form";
        }

        // Process image upload
        if (!imageFile.isEmpty()) {
            byte[] imageBytes = imageFile.getBytes();
            String imageUrl = "save_image_here"; // Implement image saving logic
            item.setImageUrl(imageUrl); // Set the image URL in the item entity
        }

        itemService.saveItem(item); // Save item to the database
        return "redirect:/"; // Redirect to home page or another appropriate page
    }

    // Mapping to show the form for reporting found item
    @GetMapping("/report-found")
    public String showReportFoundForm(Model model) {
        model.addAttribute("item", new Item());
        return "found_form"; // assuming Thymeleaf template name
    }

    // Mapping to handle form submission for reporting found item
    @PostMapping("/report-found")
    public String reportFound(@Valid Item item,
                              @RequestParam("image") MultipartFile imageFile,
                              BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return "found_form";
        }

        // Process image upload
        if (!imageFile.isEmpty()) {
            byte[] imageBytes = imageFile.getBytes();
            String imageUrl = "save_image_here"; // Implement image saving logic
            item.setImageUrl(imageUrl); // Set the image URL in the item entity
        }

        itemService.saveItem(item); // Save item to the database
        return "redirect:/"; // Redirect to home page or another appropriate page
    }
    @GetMapping("/search")
    public String searchItems(@RequestParam("query") String query, Model model) {
        List<Item> searchResults = itemService.searchItems(query);
        model.addAttribute("searchResults", searchResults);
        return "search_results";
    }
}
