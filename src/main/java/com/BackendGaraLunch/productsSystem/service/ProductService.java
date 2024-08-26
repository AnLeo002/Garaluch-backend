package com.BackendGaraLunch.productsSystem.service;

import com.BackendGaraLunch.productsSystem.service.dto.ProductDTO;
import com.BackendGaraLunch.productsSystem.service.dto.ProductDTOResponse;
import com.BackendGaraLunch.productsSystem.service.dto.ProductInvoiceDTOResponse;

import java.util.List;

public interface ProductService {
    ProductDTOResponse findProductById(Long id);
    ProductDTOResponse findProductByName(String name);
    List<ProductDTOResponse> findAllProducts();
    ProductDTOResponse createProduct(ProductDTO productDTO);
    ProductDTOResponse updateProduct(ProductDTO productDTO,Long id);
    void deleteProduct(Long id);
    void updateAmountProductInvoiceList(List<ProductInvoiceDTOResponse> productInvoiceDTOResponses);


}
