package com.wpCorp.dsCommerce.Controller;

import com.wpCorp.dsCommerce.DTO.ProductDTO;
import com.wpCorp.dsCommerce.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService productServ;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getAll(Pageable page) {
        Page<ProductDTO> dto = productServ.findAllPaged(page);
        return ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<ProductDTO> insertProduct(@RequestBody @Valid ProductDTO dto) {
        dto = productServ.insertProduct(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> updateMode(@PathVariable Long id, @RequestBody @Valid ProductDTO dto) {
        dto = productServ.updateProduct(id, dto);
        return ResponseEntity.ok(dto);
    }


}
