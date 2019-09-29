package com.controller;

import com.entity.Tag;
import com.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tags")
public class TagController {

    @Autowired
    private ITagService tagService;

    @GetMapping()
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/create")
    public ResponseEntity<?> saveTag(@RequestBody String name) {
        if (name != null && !name.equals("")) {
            return ResponseEntity.ok(tagService.saveTag(name));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            return ResponseEntity.ok(tagService.getTagById(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable("id") Long id){
        if (id > 0) {
            return ResponseEntity.ok(tagService.deleteTag(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}