package com.zexovian.api.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zexovian.api.entity.Contact;
import com.zexovian.api.repository.ContactRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*") // 👈 यहाँ बदलाव कर दिया है, अब यह मोबाइल और लाइव साइट दोनों पर काम करेगा!
public class ContactController {

    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostMapping("/contact")
    public ResponseEntity<?> saveContactMessage(@RequestBody Contact contact) {
        try {
            Contact savedContact = contactRepository.save(contact);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Message sent successfully!",
                "id", savedContact.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                "success", false,
                "message", "Server Error: " + e.getMessage()
            ));
        }
    }
}