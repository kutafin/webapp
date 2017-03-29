package ru.UShApp2.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.UShApp2.dao.ShUrlDao;
import ru.UShApp2.model.ShUrl;

import java.util.List;
@Service
@Transactional
public class ShUrlServiceImpl implements ShUrlService {

    @Autowired
    @Qualifier("ShUrlDao")
        private ShUrlDao shUrlDao;

        @Override
        public void createUrl(ShUrl shUrl) {
        this.shUrlDao.createUrl(shUrl);

    }

    @Override
        public void updateUrl(ShUrl shUrl) {
        this.shUrlDao.updateUrl(shUrl);

    }

    @Override
        public void removeUrl(int idUrl) {
        this.shUrlDao.removeUrl(idUrl);

    }

    @Override
    public ShUrl getUrlById(int idUrl) {

            return this.shUrlDao.findUrlById(idUrl);
    }

    @Override
    public ShUrl getUrlByName(int idTag) {
        return null;
    }


    @Override
        public List<ShUrl> getUrls() {
        List<ShUrl> shUrls = shUrlDao.listUrl();
        return shUrls;
    }

    @Override
        public List<ShUrl> getUrlByTagname(String tagname) {
        List<ShUrl> shUrls = shUrlDao.findByTagName(tagname);

        return shUrls;
    }

    @Override
        public List<ShUrl> getUrlByUsername(String username) {
        List<ShUrl> shUrls = shUrlDao.findByUsername(username);

        return shUrls;
    }



}
