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
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {

    // final वेरिएबल बेस्ट प्रैक्टिस है
    private final ContactRepository contactRepository;

    // कंस्ट्रक्टर इंजेक्शन (Constructor Injection)
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