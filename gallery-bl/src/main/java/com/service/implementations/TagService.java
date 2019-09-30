package com.service.implementations;


import com.DAO.ITagRep;
import com.entity.Tag;
import com.service.ITagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService implements ITagService {

    private ITagRep tagRepository;

    public TagService(ITagRep tagRepository){this.tagRepository = tagRepository;}

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(Long tagId) {
        Optional<Tag> tagOptional = tagRepository.findById(tagId);
        if (tagOptional.isPresent()) {
            return tagOptional.get();
        }
        else {
            return null;
        }
    }

    public String deleteTag(Long tagId) {
        try {
            tagRepository.deleteById(tagId);
            return "Success";
        } catch (Exception exception) {
            return "Failed";
        }
    }

    public Tag saveTag(String name) {
        if (tagRepository.findByName(name.toLowerCase()) == null) {
            Tag tag = new Tag();
            tag.setName(name);
            return tagRepository.save(tag);
        } else {
            return null;
        }
    }

}
