package com.teksystems.bootcamp.commerce_data.controller.DBControllers;

import com.teksystems.bootcamp.commerce_data.exceptions.ResourceNotFoundException;
import com.teksystems.bootcamp.commerce_data.models.DBEntities.Inventory;
import com.teksystems.bootcamp.commerce_data.repository.DBRepos.InventoryRepository;
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
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class InventoryController {
    @Autowired
    private InventoryRepository inventoryRepo;

    @Operation(summary = "Return the first 100 Inventory listings, sorted by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all 100 Inventory listings & returns them.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventory.class))}),
            @ApiResponse(responseCode = "404", description = "Issue with the URL",
                    content = @Content)})
    @GetMapping("/inventory")
    public List<Inventory> getFirst100Inventory() {
        return inventoryRepo.findAll(PageRequest.of(0, 100)).toList();
    }


    @Operation(summary = "Get all inventory listings; but they're returned as pages with 10 inventory rows per page. Change the {pagenum} variable in the URL to see the next page of inventorys.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the first 10 inventorys & returned them.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid page number supplied.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Issue with the URL.",
                    content = @Content)})
    @GetMapping("/inventory/page/{pagenum}")
    public Page<Inventory> getInventory(@PathVariable(value = "pagenum") int pagenum) {
        return inventoryRepo.findAll(PageRequest.of(pagenum, 10));
    }

    @Operation(summary = "Save multiple inventory rows. Must use JSON format.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "All inventory rows were saved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventory.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Issue with URL",
                    content = @Content)})
    @PostMapping("/inventory")
    public void saveInventory(@RequestBody List<Inventory> newInventory, HttpServletResponse response) {
        inventoryRepo.saveAll(newInventory);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Operation(summary = "Return a single inventory row based on it's item ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventory row found & returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventory.class))}),
            @ApiResponse(responseCode = "404", description = "Inventory with this ID doesn't exist OR URL issue",
                    content = @Content)})
    @GetMapping("/inventory/{id}")
    public Optional<Inventory> getInventory(@PathVariable Integer id) {
        if (inventoryRepo.existsById(id)) {
            return inventoryRepo.findById(id);
        } else {
            throw new ResourceNotFoundException("Inventory", "With this ID  " + id, id);
        }
    }

    @Operation(summary = "Save a new Inventory row with this ID. If an inventory row already exists the information will be updated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventory row with this ID was updated.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventory.class))}),
            @ApiResponse(responseCode = "201", description = "Inventory row with this ID was created.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventory.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "URL issue",
                    content = @Content)})
    @PutMapping("/inventory/{newid}")
    public ResponseEntity<Inventory> saveInventory(@RequestBody Inventory newInventory, @PathVariable Integer newid) {
        if (inventoryRepo.existsById(newid)) {
            Inventory oldInventory = inventoryRepo.getById(newid);
            oldInventory.setId(newid);
            oldInventory.setStockNum(newInventory.getStockNum());
            oldInventory.setLastUpdate(Instant.now());
            return new ResponseEntity<Inventory>(inventoryRepo.save(oldInventory), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Inventory>(inventoryRepo.save(newInventory), HttpStatus.CREATED);
    }

    @Operation(summary = "Deletes an inventory row with the supplied ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Inventory row found & deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventory.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "An inventory row with this ID wasn't found/URL issue",
                    content = @Content)})
    @DeleteMapping("/inventory/{id}")
    public void deleteInventory(@PathVariable Integer id, HttpServletResponse response) {
        if (inventoryRepo.existsById(id)) {
            inventoryRepo.deleteById(id);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            throw new ResourceNotFoundException("Inventory row", "With this ID  " + id, id);
        }

    }
}
