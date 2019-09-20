package com.service.implementations;



import com.DAO.ITagRep;
import com.entity.Tag;
import com.exceptions.MyFileNotFoundException;
import com.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService implements ITagService {

    @Autowired
    private ITagRep tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(Long tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new MyFileNotFoundException("Tag not found with id " + tagId));
    }

    public void deleteTag(Long tagId) {
        tagRepository.deleteById(tagId);
    }

    public Tag saveTag(String name) {
        Tag tag = new Tag();
        tag.setName(name);

        return tagRepository.save(tag);
    }

}
