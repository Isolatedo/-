package com.service;

import com.entity.Contact;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
/**
 * 联系人服务类接口
 */
public interface ContactService {
    List<Contact> queryAllContacts();
    List<Contact> queryContactByName(String cname);
    int addContact(Contact contact);
    int deleteContact(String cname);
    int updateContact(Contact contact);
}
