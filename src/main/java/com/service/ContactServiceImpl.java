package com.service;

import com.dao.ContactDao;
import com.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    ContactDao contactDao;
    @Override
    public List<Contact> queryAllContacts() {
        return contactDao.queryAll();
    }

    @Override
    public List<Contact> queryContactByName(String cname) {
        return contactDao.queryByName(cname);
    }

    @Override
    public int addContact(Contact contact) {
        return contactDao.insertSelective(contact);
    }

    @Override
    public int deleteContact(String cname) {
        return contactDao.deleteContactByName(cname);
    }

    @Override
    public int updateContact(Contact contact) {
        return contactDao.updateByPrimaryKeySelective(contact);
    }
}
