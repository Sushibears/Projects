package com.teksystems.bootcamp.commerce_data.controller.DBControllers;

import com.teksystems.bootcamp.commerce_data.exceptions.ResourceNotFoundException;
import com.teksystems.bootcamp.commerce_data.models.DBEntities.CustomerOrders;
import com.teksystems.bootcamp.commerce_data.repository.DBRepos.CustomerOrdersRepository;
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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.time.LocalTime.now;

@RestController
@RequestMapping("/api")
public class CustomerOrdersController {
    @Autowired
    private CustomerOrdersRepository customerorderRepo;

    @Operation(summary = "Return the first 100 CustomerOrder listings, sorted by ID. It returns orders from all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all 100 CustomerOrder listings & returns them.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerOrders.class))}),
            @ApiResponse(responseCode = "404", description = "Issue with the URL",
                    content = @Content)})
    @GetMapping("/customerorder")
    public List<CustomerOrders> getFirst100CustomerOrders() {
        return customerorderRepo.findAll(PageRequest.of(0, 100)).toList();
    }


    @Operation(summary = "Get all customerorder listings; but they're returned as pages with 10 customerorder rows per page. Change the {pagenum} variable in the URL to see the next page of customerorders. This searches through every single order all customers have made.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the first 10 customerorders & returned them.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid page number supplied.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Issue with the URL.",
                    content = @Content)})
    @GetMapping("/customerorder/page/{pagenum}")
    public Page<CustomerOrders> getCustomerOrder(@PathVariable(value = "pagenum") int pagenum) {
        return customerorderRepo.findAll(PageRequest.of(pagenum, 10));
    }

    @Operation(summary = "Save multiple customerorder rows. Must use JSON format.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "All customerorder rows were saved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerOrders.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Issue with URL",
                    content = @Content)})
    @PostMapping("/customerorder")
    public void saveCustomerOrder(@RequestBody List<CustomerOrders> newCustomerOrder, HttpServletResponse response) {
        customerorderRepo.saveAll(newCustomerOrder);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Operation(summary = "Return a single Customer Order based on it's item ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CustomerOrder row found & returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerOrders.class))}),
            @ApiResponse(responseCode = "404", description = "A Customer Order with this ID doesn't exist OR URL issue",
                    content = @Content)})
    @GetMapping("/customerorder/{id}")
    public Optional<CustomerOrders> getCustomerOrder(@PathVariable Integer id) {
        if (customerorderRepo.existsById(id)) {
            return customerorderRepo.findById(id);
        } else {
            throw new ResourceNotFoundException("Customer Order", "With this ID  " + id, id);
        }
    }

    @Operation(summary = "Save a new Customer Order with this ID. If a Customer Order already exists the information will be updated. Having the customer_id is important!!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CustomerOrder row with this ID was updated.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerOrders.class))}),
            @ApiResponse(responseCode = "201", description = "A Customer Order with this ID was created.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerOrders.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "URL issue",
                    content = @Content)})
    @PutMapping("/customerorder/{newid}")
    public ResponseEntity<CustomerOrders> saveCustomerOrder(@RequestBody CustomerOrders newCustomerOrder, @PathVariable Integer newid) {
        if (customerorderRepo.existsById(newid)) {
            CustomerOrders oldCustomerOrder = customerorderRepo.getById(newid);
            oldCustomerOrder.setId(newid);
            oldCustomerOrder.setCustomerId(newCustomerOrder.getCustomerId());
            oldCustomerOrder.setCreatedDate(LocalDate.now());
            oldCustomerOrder.setShippedDate(newCustomerOrder.getShippedDate());
            return new ResponseEntity<CustomerOrders>(customerorderRepo.save(oldCustomerOrder), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<CustomerOrders>(customerorderRepo.save(newCustomerOrder), HttpStatus.CREATED);
    }

    @Operation(summary = "Deletes an customerorder  with the supplied ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "CustomerOrder found & deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerOrders.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "An customerorder row with this ID wasn't found/URL issue",
                    content = @Content)})
    @DeleteMapping("/customerorder/{id}")
    public void deleteCustomerOrder(@PathVariable Integer id, HttpServletResponse response) {
        if (customerorderRepo.existsById(id)) {
            customerorderRepo.deleteById(id);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            throw new ResourceNotFoundException("CustomerOrder ", "With this ID  " + id, id);
        }

    }
}
