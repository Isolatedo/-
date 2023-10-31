package com.controller;

import com.common.Result;
import com.entity.Contact;
import com.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = "*") //跨域访问
@ResponseBody
@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    ContactService contactService;
    @GetMapping("/allContacts")
    public Result<List<Contact>> getContacts() {
        List<Contact> contacts = contactService.queryAllContacts();
        return new Result<>(true,"查询成功",contacts);
    }

    @GetMapping("/selectiveContact/{ContactName}")
    public Result<List<Contact>> getContact(@PathVariable("ContactName") String ContactName) {
        List<Contact> contact = contactService.queryContactByName(ContactName);
        return new Result<>(true,"查询成功！",contact);
    }
    @PostMapping("/addContact")
    public Result<?> addContact(@Valid @RequestBody Contact contact, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            StringBuilder sb = new StringBuilder();
            for (ObjectError allError : allErrors) {
                sb.append(allError);
                sb.append("+");
            }
            return new Result<>(false,"添加失败，请检查输入",sb);
        }
        int res = contactService.addContact(contact);
        return new Result<>(true,"添加成功！");
    }
    @DeleteMapping("/deleteContact/{cname}")
    public Result<?> deleteContact(@PathVariable("cname") String cname) {
        int res = contactService.deleteContact(cname);
        if(res == 0) return new Result<>(false,"删除失败，请检查输入！");
        return new Result<>(true,"删除成功！");
    }

    @PostMapping("/updateContact")
    public Result<?> updateContact(@Valid @RequestBody Contact contact,BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            StringBuilder sb = new StringBuilder();
            for (ObjectError allError : allErrors) {
                sb.append(allError);
                sb.append("+");
            }
            return new Result<>(false,"更新失败，请检查输入",sb);
        }
        int res = contactService.updateContact(contact);
        return new Result<>(true,"修改成功！");
    }
}
