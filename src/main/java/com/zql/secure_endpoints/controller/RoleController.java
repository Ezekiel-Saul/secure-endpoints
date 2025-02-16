package com.zql.secure_endpoints.controller;


import com.zql.secure_endpoints.entity.Role;
import com.zql.secure_endpoints.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // Only admins can create roles
    public ResponseEntity<String> addRole(@RequestBody Role role) {
        if (roleRepository.findByName(role.getName()).isPresent()) {
            return ResponseEntity.badRequest().body("Role already exists!");
        }
        roleRepository.save(role);
        return ResponseEntity.ok("Role added successfully!");
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')") // Admins and Managers can list roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Only admins can update roles
    public ResponseEntity<String> updateRole(@PathVariable Long id, @RequestBody Role roleDetails) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        role.setName(roleDetails.getName());
        roleRepository.save(role);
        return ResponseEntity.ok("Role updated successfully!");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Only admins can delete roles
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        roleRepository.delete(role);
        return ResponseEntity.ok("Role deleted successfully!");
    }
}
