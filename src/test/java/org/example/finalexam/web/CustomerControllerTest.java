package org.example.finalexam.web;

import org.example.finalexam.entities.Customer;
import org.example.finalexam.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class CustomerControllerTest {

    Customer customer;
    Customer customer2;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    View mockView;

    @InjectMocks
    CustomerController customerController;

    @BeforeEach
    void setUp() throws ParseException {

        customer = new Customer();
        customer.setId(1L);
        String sDate1 = "2012/11/11";
        Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);
        customer.setDate(date1);
        customer.setSeat("1A");
        customer.setName("John");

        customer2 = new Customer();
        customer2.setId(1L);
        String sDate2 = "2023/11/11";
        Date date2 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate2);
        customer2.setDate(date2);
        customer2.setSeat("1B");
        customer2.setName("Julia");

        MockitoAnnotations.openMocks(this);

        mockMvc = standaloneSetup(customerController).setSingleView(mockView).build();
    }

    @Test
    public void findAll_ListView() throws Exception {
        List<Customer> list = new ArrayList<>();
        list.add(customer);
        list.add(customer2);

        when(customerRepository.findAll()).thenReturn(list);
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("listCustomers", list))
                .andExpect(view().name("customers"))
                .andExpect(model().attribute("listCustomers", hasSize(2)));

        verify(customerRepository,times(1)).findAll();
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void delete() {
        ArgumentCaptor<Long> idCapture = ArgumentCaptor.forClass(Long.class);
        doNothing().when(customerRepository).deleteById(idCapture.capture());
        customerRepository.deleteById(1L);
        assertEquals(1L, idCapture.getValue());
        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void add() {
        when(customerRepository.save(customer)).thenReturn(customer);
        customerRepository.save(customer);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void save() {
        when(customerRepository.save(customer)).thenReturn(customer);
        customerRepository.save(customer);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void editCustomers() throws Exception {
        Customer s2 = new Customer();
        s2.setId(2L);


        String sDate2 = "2024/11/11";
        Date date2 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate2);
        s2.setDate(date2);
        s2.setSeat("1B");
        s2.setName("John Mast");

        Long lid = 2L;

        when(customerRepository.findById(lid)).thenReturn(Optional.of(s2));

        mockMvc.perform(get("/editCustomers").param("id", String.valueOf(2L)))
                .andExpect(status().isOk())
                .andExpect(model().attribute("customer", s2))
                .andExpect(view().name("editCustomers"));

        verify(customerRepository,times(1)).findById(anyLong());
        verifyNoMoreInteractions(customerRepository);
    }
}