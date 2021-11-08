package com.teksystems.bootcamp.commerce_data.controller.DBControllers;

import com.teksystems.bootcamp.commerce_data.exceptions.ResourceNotFoundException;
import com.teksystems.bootcamp.commerce_data.models.DBEntities.Admins;
import com.teksystems.bootcamp.commerce_data.repository.DBRepos.AdminsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AdminsController {
    @Autowired
    private AdminsRepository adminRepo;

    @Operation(summary = "Return the first 100 Admins, sorted by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all 100 Admins & returns them.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Admins.class))}),
            @ApiResponse(responseCode = "404", description = "Issue with the URL",
                    content = @Content)})
    @GetMapping("/admins")
    public List<Admins> getFirst100Admins() {
        return adminRepo.findAll(PageRequest.of(0, 100)).toList();
    }


    @Operation(summary = "Get all admins; but they're returned as pages with 10 admins per page. Change the {pagenum} variable in the URL to see the next page of admins.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the first 10 admins & returned them.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid page number supplied.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Issue with the URL.",
                    content = @Content)})
    @GetMapping("/admins/page/{pagenum}")
    public Page<Admins> getAdmins(@PathVariable(value = "pagenum") int pagenum) {
        return adminRepo.findAll(PageRequest.of(pagenum, 10));
    }

    @Operation(summary = "Save multiple admins. Must use JSON format.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "All admins were saved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Admins.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Issue with URL",
                    content = @Content)})
    @PostMapping("/admins")
    public void saveAdmins(@RequestBody List<Admins> newAdmins, HttpServletResponse response) {
        adminRepo.saveAll(newAdmins);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Operation(summary = "Return a single admin based on it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admins found & returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Admins.class))}),
            @ApiResponse(responseCode = "404", description = "Admins with this ID doesn't exist OR URL issue",
                    content = @Content)})
    @GetMapping("/admin/{id}")
    public Optional<Admins> getAdmins(@PathVariable Integer id) {
        if (adminRepo.existsById(id)) {
            return adminRepo.findById(id);
        } else {
            throw new ResourceNotFoundException("Admins", "With this ID  " + id, id);
        }
    }

    @Operation(summary = "Save a new Admins with this ID. If the ID already exists the information will be updated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admins with this ID was updated.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Admins.class))}),
            @ApiResponse(responseCode = "201", description = "Admins with this ID was created.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Admins.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "URL issue",
                    content = @Content)})
    @PutMapping("/admin/{newid}")
    public ResponseEntity<Admins> saveAdmins(@RequestBody Admins newAdmins, @PathVariable Integer newid) {
        if (adminRepo.existsById(newid)) {
            Admins oldAdmins = adminRepo.getById(newid);
            oldAdmins.setId(newid);
            oldAdmins.setFirstName(newAdmins.getFirstName());
            oldAdmins.setLastName(newAdmins.getLastName());
            oldAdmins.setPrivilege(newAdmins.getPrivilege());
            return new ResponseEntity<Admins>(adminRepo.save(oldAdmins), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Admins>(adminRepo.save(newAdmins), HttpStatus.CREATED);
    }

    @Operation(summary = "Deletes a admin with the supplied ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Admins found & deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Admins.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "A admin with this ID wasn't found/URL issue",
                    content = @Content)})
    @DeleteMapping("/admin/{id}")
    public void deleteAdmins(@PathVariable Integer id, HttpServletResponse response) {
        if (adminRepo.existsById(id)) {
            adminRepo.deleteById(id);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            throw new ResourceNotFoundException("Admins", "With this ID  " + id, id);
        }

    }
}
