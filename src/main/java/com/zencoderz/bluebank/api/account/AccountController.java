package com.zencoderz.bluebank.api.account;

import com.zencoderz.bluebank.api.account.dto.AccountDTO;
import com.zencoderz.bluebank.api.account.dto.AccountFormCreateDTO;
import com.zencoderz.bluebank.api.account.dto.AccountFormUpdateDTO;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;

    @GetMapping
    public ResponseEntity<Set<AccountDTO>> getAccounts() {
        Set<AccountDTO> accountDTOS = this.accountService.getAccountsDTO();
        return ResponseEntity.ok(accountDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> findAccountById(@PathVariable("id") UUID id) {
        AccountDTO accountDTO = this.accountService.findAccountDTOById(id);
        return ResponseEntity.ok(accountDTO);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<AccountDTO> createAccount(@PathVariable("userId") UUID userId,
                                                    @RequestBody @Valid AccountFormCreateDTO accountFormCreateDTO) {
        AccountDTO accountDTO = this.accountService.createAccount(userId, accountFormCreateDTO);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + accountDTO.getId()).toUriString());
        return ResponseEntity.created(uri).body(accountDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable("id") UUID id,
                                                    @RequestBody @Valid AccountFormUpdateDTO accountFormUpdateDTO) {
        AccountDTO accountDTO = this.accountService.updateAccount(id, accountFormUpdateDTO);
        return ResponseEntity.ok(accountDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable UUID id){
        this.accountService.deleteAccount(id);
        return ResponseEntity.ok("Deleted");
    }

}
