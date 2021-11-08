package com.teksystems.bootcamp.commerce_data.controller.DBControllers;

import com.teksystems.bootcamp.commerce_data.exceptions.ResourceNotFoundException;
import com.teksystems.bootcamp.commerce_data.models.DBEntities.Products;
import com.teksystems.bootcamp.commerce_data.repository.DBRepos.ProductsRepository;
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
public class ProductsController {
    @Autowired
    private ProductsRepository productRepo;

    @Operation(summary = "Return the first 100 Products, sorted by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all 100 Products & returns them.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Products.class))}),
            @ApiResponse(responseCode = "404", description = "Issue with the URL",
                    content = @Content)})
    @GetMapping("/products")
    public List<Products> getFirst100Products() {
        return productRepo.findAll(PageRequest.of(0, 100)).toList();
    }


    @Operation(summary = "Get all products; but they're returned as pages with 10 products per page. Change the {pagenum} variable in the URL to see the next page of products.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the first 10 products & returned them.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid page number supplied.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Issue with the URL.",
                    content = @Content)})
    @GetMapping("/products/page/{pagenum}")
    public Page<Products> getProducts(@PathVariable(value = "pagenum") int pagenum) {
        return productRepo.findAll(PageRequest.of(pagenum, 10));
    }

    @Operation(summary = "Save multiple products. Must use JSON format.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "All products were saved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Products.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Issue with URL",
                    content = @Content)})
    @PostMapping("/products")
    public void saveProducts(@RequestBody List<Products> newProducts, HttpServletResponse response) {
        productRepo.saveAll(newProducts);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Operation(summary = "Return a single product based on it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found & returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Products.class))}),
            @ApiResponse(responseCode = "404", description = "Products with this ID doesn't exist OR URL issue",
                    content = @Content)})
    @GetMapping("/product/{id}")
    public Optional<Products> getProducts(@PathVariable Integer id) {
        if (productRepo.existsById(id)) {
            return productRepo.findById(id);
        } else {
            throw new ResourceNotFoundException("Products", "With this ID  " + id, id);
        }
    }

    @Operation(summary = "Save a new Products with this ID. If the ID already exists the information will be updated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products with this ID was updated.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Products.class))}),
            @ApiResponse(responseCode = "201", description = "Products with this ID was created.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Products.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "URL issue",
                    content = @Content)})
    @PutMapping("/product/{newid}")
    public ResponseEntity<Products> saveProducts(@RequestBody Products newProducts, @PathVariable Integer newid) {
        if (productRepo.existsById(newid)) {
            Products oldProducts = productRepo.getById(newid);
            oldProducts.setId(newid);
            oldProducts.setTitle(newProducts.getTitle());
            oldProducts.setCategory(newProducts.getCategory());
            oldProducts.setPrice(newProducts.getPrice());
            oldProducts.setProductDescription(newProducts.getProductDescription());
            oldProducts.setQty(newProducts.getQty());
            oldProducts.setProductImg(newProducts.getProductImg());
            oldProducts.setAltText(newProducts.getAltText());
            return new ResponseEntity<Products>(productRepo.save(oldProducts), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Products>(productRepo.save(newProducts), HttpStatus.CREATED);
    }

    @Operation(summary = "Deletes a product with the supplied ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Products found & deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Products.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "A product with this ID wasn't found/URL issue",
                    content = @Content)})
    @DeleteMapping("/product/{id}")
    public void deleteProducts(@PathVariable Integer id, HttpServletResponse response) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            throw new ResourceNotFoundException("Products", "With this ID  " + id, id);
        }

    }
}
