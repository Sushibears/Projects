package com.teksystems.bootcamp.commerce_data.controller.DBControllers;

import com.teksystems.bootcamp.commerce_data.exceptions.ResourceNotFoundException;
import com.teksystems.bootcamp.commerce_data.models.DBEntities.Customers;
import com.teksystems.bootcamp.commerce_data.repository.DBRepos.CustomersRepository;
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
public class CustomersController {
    @Autowired
    private CustomersRepository customerRepo;

    @Operation(summary = "Return the first 100 Customers, sorted by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all 100 Customers & returns them.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customers.class))}),
            @ApiResponse(responseCode = "404", description = "Issue with the URL",
                    content = @Content)})
    @GetMapping("/customers")
    public List<Customers> getFirst100Customers() {
        return customerRepo.findAll(PageRequest.of(0, 100)).toList();
    }


    @Operation(summary = "Get all customers; but they're returned as pages with 10 customers per page. Change the {pagenum} variable in the URL to see the next page of customers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the first 10 customers & returned them.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid page number supplied.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Issue with the URL.",
                    content = @Content)})
    @GetMapping("/customers/page/{pagenum}")
    public Page<Customers> getCustomers(@PathVariable(value = "pagenum") int pagenum) {
        return customerRepo.findAll(PageRequest.of(pagenum, 10));
    }

    @Operation(summary = "Save multiple customers. Must use JSON format.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "All customers were saved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customers.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Issue with URL",
                    content = @Content) })
    @PostMapping("/customers")
    public void saveCustomers(@RequestBody List<Customers> newCustomers, HttpServletResponse response) {
        customerRepo.saveAll(newCustomers);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Operation(summary = "Return a single customer based on it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customers found & returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customers.class))}),
            @ApiResponse(responseCode = "404", description = "Customers with this ID doesn't exist OR URL issue",
                    content = @Content)})
    @GetMapping("/customer/{id}")
    public Optional<Customers> getCustomers(@PathVariable Integer id) {
        if (customerRepo.existsById(id)) {
            return customerRepo.findById(id);
        } else {
            throw new ResourceNotFoundException("Customers", "With this ID  " + id, id);
        }
    }

    @Operation(summary = "Save a new Customers with this ID. If the ID already exists the information will be updated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customers with this ID was updated.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customers.class))}),
            @ApiResponse(responseCode = "201", description = "Customers with this ID was created.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customers.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "URL issue",
                    content = @Content)})
    @PutMapping("/customer/{newid}")
    public ResponseEntity<Customers> saveCustomers(@RequestBody Customers newCustomers, @PathVariable Integer newid) {
        if (customerRepo.existsById(newid)) {
            Customers oldCustomers = customerRepo.getById(newid);
            oldCustomers.setId(newid);
            oldCustomers.setFirstName(newCustomers.getFirstName());
            oldCustomers.setLastName(newCustomers.getLastName());
            return new ResponseEntity<Customers>(customerRepo.save(oldCustomers), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Customers>(customerRepo.save(newCustomers), HttpStatus.CREATED);
    }

    @Operation(summary = "Deletes a customer with the supplied ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Customers found & deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customers.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "A customer with this ID wasn't found/URL issue",
                    content = @Content)})
    @DeleteMapping("/customer/{id}")
    public void deleteCustomers(@PathVariable Integer id, HttpServletResponse response) {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            throw new ResourceNotFoundException("Customers", "With this ID  " + id, id);
        }

    }
}
