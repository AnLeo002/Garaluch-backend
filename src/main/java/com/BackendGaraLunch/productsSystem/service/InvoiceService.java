package com.BackendGaraLunch.productsSystem.service;

import com.BackendGaraLunch.productsSystem.service.dto.InvoiceCompleteDTOResponse;
import com.BackendGaraLunch.productsSystem.service.dto.InvoiceDTO;
import com.BackendGaraLunch.productsSystem.service.dto.InvoiceDTOResponse;

import java.util.List;

public interface InvoiceService {
    InvoiceDTOResponse findInvoiceById(Long id);
    InvoiceDTOResponse createInvoice(InvoiceDTO invoiceDTO);
    InvoiceDTOResponse updateInvoice(InvoiceDTO invoiceDTO);
    List<InvoiceDTOResponse>findAllInvoices();
    void deleteInvoice(Long id);
    InvoiceDTOResponse updateAmount(InvoiceDTO invoiceDTO);
    List<InvoiceDTOResponse>findAllInvoicesByUsername(String username);
    InvoiceCompleteDTOResponse findInvoiceWithCompletePro(Long id);
}
