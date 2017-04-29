package ru.UShApp2.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.UShApp2.dao.TagDao;
import ru.UShApp2.model.Tag;

import java.util.List;

@Service
@Transactional
public class TagServiceImpl implements TagService {


    private TagDao tagDao;

    @Autowired
    public TagServiceImpl() {
    }

    @Override
    public List<Tag> getTags() {
        List<Tag> tags = tagDao.listTag();
        return tags;
    }

    @Override
    public Tag getTagByID(Integer idTag) {
        Tag tag = tagDao.findById(idTag);
        return tag;

    }

    @Override
    public void save(Tag tag) {
        this.tagDao.save(tag);

    }

    @Override
    public Tag getTagByName(String tagname) {
        Tag tag = tagDao.findByName(tagname);
        return tag;
    }
}
