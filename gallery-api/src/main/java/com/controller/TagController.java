package com.controller;

import com.entity.Tag;
import com.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tags")
public class TagController {

    @Autowired
    private ITagService tagService;

    // Get All Tags
    @GetMapping()
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    // Create a new Tag
    @PostMapping("/create/{tag}")
    public Tag saveTag(@RequestParam(value = "tag") String tag) {
        System.out.println("test");
        return tagService.saveTag(tag);
    }

    // Get a Single Tag
    @GetMapping("/tag/{id}")
    public Tag getTagById(@PathVariable(value = "id") Long tagId) {
        return tagService.getTagById(tagId);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteTag(@PathVariable("id") Long id){
        tagService.deleteTag(id);
    }


}