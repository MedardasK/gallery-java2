package com.service;

import com.entity.Tag;

import java.util.List;

public interface ITagService {

    List<Tag> getAllTags();
    Tag getTagById(Long tagId);
    void deleteTag(Long tagId);
    Tag saveTag(String tag);

}
