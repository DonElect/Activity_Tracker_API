package com.donatus.activity_tracker_api.rest;

import com.donatus.activity_tracker_api.dto.clientDTO.ClientLoginDTO;
import com.donatus.activity_tracker_api.dto.clientDTO.ClientRegistrationDTO;
import com.donatus.activity_tracker_api.dto.clientDTO.ClientResponseDTO;
import com.donatus.activity_tracker_api.services.ClientServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class LoginAndRegController {

    private final ClientServices services;

    @PostMapping("/signup")
    public ResponseEntity<ClientResponseDTO> registerClient(@RequestBody ClientRegistrationDTO clientDTO){
        return ResponseEntity.ok(services.registerClient(clientDTO));
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseDTO> viewClient(@PathVariable Long clientId){
        return ResponseEntity.ok(services.viewClientDetails(clientId));
    }

    @PostMapping("/login")
    public ResponseEntity<ClientResponseDTO> verifyClient(@RequestBody ClientLoginDTO clientLoginDTO){
        return ResponseEntity.ok(services.verifyClient(clientLoginDTO));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable Long clientId){
        services.deleteClient(clientId);

        return ResponseEntity.ok("Client with id: "+clientId+" deleted!");
    }

}
