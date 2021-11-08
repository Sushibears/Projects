package com.teksystems.bootcamp.commerce_data.controller.DBControllers;

import com.teksystems.bootcamp.commerce_data.exceptions.ResourceNotFoundException;
import com.teksystems.bootcamp.commerce_data.models.DBEntities.Managers;
import com.teksystems.bootcamp.commerce_data.repository.DBRepos.ManagersRepository;
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
public class ManagersController {
    @Autowired
    private ManagersRepository managerRepo;

    @Operation(summary = "Return the first 100 Managers, sorted by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all 100 Managers & returns them.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Managers.class))}),
            @ApiResponse(responseCode = "404", description = "Issue with the URL",
                    content = @Content)})
    @GetMapping("/managers")
    public List<Managers> getFirst100Managers() {
        return managerRepo.findAll(PageRequest.of(0, 100)).toList();
    }


    @Operation(summary = "Get all managers; but they're returned as pages with 10 managers per page. Change the {pagenum} variable in the URL to see the next page of managers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the first 10 managers & returned them.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid page number supplied.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Issue with the URL.",
                    content = @Content)})
    @GetMapping("/managers/page/{pagenum}")
    public Page<Managers> getManagers(@PathVariable(value = "pagenum") int pagenum) {
        return managerRepo.findAll(PageRequest.of(pagenum, 10));
    }

    @Operation(summary = "Save multiple managers. Must use JSON format.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "All managers were saved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Managers.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Issue with URL",
                    content = @Content)})
    @PostMapping("/managers")
    public void saveManagers(@RequestBody List<Managers> newManagers, HttpServletResponse response) {
        managerRepo.saveAll(newManagers);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Operation(summary = "Return a single manager based on it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Managers found & returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Managers.class))}),
            @ApiResponse(responseCode = "404", description = "Managers with this ID doesn't exist OR URL issue",
                    content = @Content)})
    @GetMapping("/manager/{id}")
    public Optional<Managers> getManagers(@PathVariable Integer id) {
        if (managerRepo.existsById(id)) {
            return managerRepo.findById(id);
        } else {
            throw new ResourceNotFoundException("Managers", "With this ID  " + id, id);
        }
    }

    @Operation(summary = "Save a new Managers with this ID. If the ID already exists the information will be updated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Managers with this ID was updated.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Managers.class))}),
            @ApiResponse(responseCode = "201", description = "Managers with this ID was created.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Managers.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "URL issue",
                    content = @Content)})
    @PutMapping("/manager/{newid}")
    public ResponseEntity<Managers> saveManagers(@RequestBody Managers newManagers, @PathVariable Integer newid) {
        if (managerRepo.existsById(newid)) {
            Managers oldManagers = managerRepo.getById(newid);
            oldManagers.setId(newid);
            oldManagers.setFirstName(newManagers.getFirstName());
            oldManagers.setLastName(newManagers.getLastName());
            oldManagers.setPrivilege(newManagers.getPrivilege());
            return new ResponseEntity<Managers>(managerRepo.save(oldManagers), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Managers>(managerRepo.save(newManagers), HttpStatus.CREATED);
    }

    @Operation(summary = "Deletes a manager with the supplied ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Managers found & deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Managers.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "A manager with this ID wasn't found/URL issue",
                    content = @Content)})
    @DeleteMapping("/manager/{id}")
    public void deleteManagers(@PathVariable Integer id, HttpServletResponse response) {
        if (managerRepo.existsById(id)) {
            managerRepo.deleteById(id);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            throw new ResourceNotFoundException("Managers", "With this ID  " + id, id);
        }

    }
}
