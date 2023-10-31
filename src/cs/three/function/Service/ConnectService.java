package cs.three.function.Service;

import cs.three.show.entity.Contact;

import java.util.List;

public interface ConnectService {
    List<Contact> queryAll();
    List<Contact> queryByName(String name);
    boolean addConnect(Contact contact);
    boolean deleteConnectByName(String name);
    boolean deleteConnectById(Integer id);
    boolean updateConnect(String oldName,Contact contact);
}
