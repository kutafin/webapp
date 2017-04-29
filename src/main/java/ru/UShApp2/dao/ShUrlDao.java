package ru.UShApp2.dao;

import ru.UShApp2.model.ShUrl;

import java.util.List;

public interface ShUrlDao {
    void createUrl(ShUrl shUrl);

    void updateUrl(ShUrl shUrl);

    void removeUrl(int idUrl);

    ShUrl findUrlById(int idUrl);

    List<ShUrl> listUrl();

    List<ShUrl> findByTagName(String tagname);

    List<ShUrl> findByUsername(String username);

}
