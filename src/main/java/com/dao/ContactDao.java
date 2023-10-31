package com.dao;

import com.entity.Contact;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ContactDao {
    int deleteByPrimaryKey(Integer cnumber);

    int insert(Contact record);

    int insertSelective(Contact record);

    Contact selectByPrimaryKey(Integer cnumber);

    int updateByPrimaryKeySelective(Contact record);

    int updateByPrimaryKey(Contact record);

    //查询全部联系人
    List<Contact> queryAll();
    //通过名称查询
    List<Contact> queryByName(String name);

    int deleteContactByName(String name);
}