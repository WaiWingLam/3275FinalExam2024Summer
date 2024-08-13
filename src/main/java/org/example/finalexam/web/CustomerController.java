package org.example.finalexam.web;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.example.finalexam.entities.Customer;
import org.example.finalexam.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@SessionAttributes({"a","e"})
@Controller
@AllArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    static int num=0;

    @GetMapping(path="/index")
    public String index(Model model) {

        model.addAttribute("customer", new Customer());

        Customer customer1 = customerRepository.findCustomerBySeat("1A");
        String customerName1 = (customer1 != null) ? customer1.getName() : "1A";
        model.addAttribute("seat1A", customerName1);

        Customer customer2 = customerRepository.findCustomerBySeat("1B");
        String customerName2 = (customer2 != null) ? customer2.getName() : "1B";
        model.addAttribute("seat1B", customerName2);

        Customer customer3 = customerRepository.findCustomerBySeat("1C");
        String customerName3 = (customer3 != null) ? customer3.getName() : "1C";
        model.addAttribute("seat1C", customerName3);

        Customer customer4 = customerRepository.findCustomerBySeat("1D");
        String customerName4 = (customer4 != null) ? customer4.getName() : "1D";
        model.addAttribute("seat1D", customerName4);

        Customer customer5 = customerRepository.findCustomerBySeat("1E");
        String customerName5 = (customer5 != null) ? customer5.getName() : "1E";
        model.addAttribute("seat1E", customerName5);

        Customer customer6 = customerRepository.findCustomerBySeat("2A");
        String customerName6 = (customer6 != null) ? customer6.getName() : "2A";
        model.addAttribute("seat2A", customerName6);

        Customer customer7 = customerRepository.findCustomerBySeat("2B");
        String customerName7 = (customer7 != null) ? customer7.getName() : "2B";
        model.addAttribute("seat2B", customerName7);

        Customer customer8 = customerRepository.findCustomerBySeat("2C");
        String customerName8 = (customer8 != null) ? customer8.getName() : "2C";
        model.addAttribute("seat2C", customerName8);

        Customer customer9 = customerRepository.findCustomerBySeat("2D");
        String customerName9 = (customer9 != null) ? customer9.getName() : "2D";
        model.addAttribute("seat2D", customerName9);

        Customer customer10 = customerRepository.findCustomerBySeat("2E");
        String customerName10 = (customer10 != null) ? customer10.getName() : "2E";
        model.addAttribute("seat2E", customerName10);

        Customer customer11 = customerRepository.findCustomerBySeat("3A");
        String customerName11 = (customer11 != null) ? customer11.getName() : "3A";
        model.addAttribute("seat3A", customerName11);

        Customer customer12 = customerRepository.findCustomerBySeat("3B");
        String customerName12 = (customer12 != null) ? customer12.getName() : "3B";
        model.addAttribute("seat3B", customerName12);

        Customer customer13 = customerRepository.findCustomerBySeat("3C");
        String customerName13 = (customer13 != null) ? customer13.getName() : "3C";
        model.addAttribute("seat3C", customerName13);

        Customer customer14 = customerRepository.findCustomerBySeat("3D");
        String customerName14 = (customer14 != null) ? customer14.getName() : "3D";
        model.addAttribute("seat3D", customerName14);

        Customer customer15 = customerRepository.findCustomerBySeat("3E");
        String customerName15 = (customer15 != null) ? customer15.getName() : "3E";
        model.addAttribute("seat3E", customerName15);

        Customer customer16 = customerRepository.findCustomerBySeat("4A");
        String customerName16 = (customer16 != null) ? customer16.getName() : "4A";
        model.addAttribute("seat4A", customerName16);

        Customer customer17 = customerRepository.findCustomerBySeat("4B");
        String customerName17 = (customer17 != null) ? customer17.getName() : "4B";
        model.addAttribute("seat4B", customerName17);

        Customer customer18 = customerRepository.findCustomerBySeat("4C");
        String customerName18 = (customer18 != null) ? customer18.getName() : "4C";
        model.addAttribute("seat4C", customerName18);

        Customer customer19 = customerRepository.findCustomerBySeat("4D");
        String customerName19 = (customer19 != null) ? customer19.getName() : "4D";
        model.addAttribute("seat4D", customerName19);

        Customer customer20 = customerRepository.findCustomerBySeat("4E");
        String customerName20 = (customer20 != null) ? customer20.getName() : "4E";
        model.addAttribute("seat4E", customerName20);

        int seat = (int) (20 - customerRepository.count());
        model.addAttribute("numSeat", seat);

        List<Customer> customers;
        customers = customerRepository.findAll();
        model.addAttribute("listCustomers",customers);

        return "customers";
    }

    @PostMapping("/add")
    public String add(Model model, Customer customer, BindingResult bindingResult, ModelMap mm, HttpSession session, RedirectAttributes redirectAttributes) {

        List<String> seats = new ArrayList<>();

        seats.add("1A");
        seats.add("1B");
        seats.add("1C");
        seats.add("1D");
        seats.add("1E");
        seats.add("2A");
        seats.add("2B");
        seats.add("2C");
        seats.add("2D");
        seats.add("2E");
        seats.add("3A");
        seats.add("3B");
        seats.add("3C");
        seats.add("3D");
        seats.add("3E");
        seats.add("4A");
        seats.add("4B");
        seats.add("4C");
        seats.add("4D");
        seats.add("4E");

        if(customer.getSeat().equals("")) {
            redirectAttributes.addFlashAttribute("err", "Seat or name cannot be blank.");
            mm.put("e", 0);
            mm.put("a", 0);
            return "redirect:index";
        }

        if(customer.getName().equals("")) {
            redirectAttributes.addFlashAttribute("err", "Seat or name cannot be blank.");
            mm.put("e", 0);
            mm.put("a", 0);
            return "redirect:index";
        }

        if(!seats.contains(customer.getSeat())) {
            redirectAttributes.addFlashAttribute("err", "Please follow the seat code format.");
            mm.put("e", 0);
            mm.put("a", 0);
            return "redirect:index";
        }

        if (customerRepository.countCustomerBySeat(customer.getSeat()) != 0) {
            redirectAttributes.addFlashAttribute("err", "The seat is already taken.");
            mm.put("e", 0);
            mm.put("a", 0);
            return "redirect:index";
        } else {

            customerRepository.save(customer);

            mm.put("e", 0);
            mm.put("a", 1);

            return "redirect:index";
        }
    }

    @GetMapping("/editCustomers")
    public String editCustomers(Model model, Long id, HttpSession session) {
        num = 2;
        session.setAttribute("info", 0);
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null) throw new RuntimeException("Customer does not exist");
        model.addAttribute("customer", customer);
        return "editCustomers";
    }

    @PostMapping("/save")
    public String save(Model model, Customer customer, BindingResult bindingResult, ModelMap mm, HttpSession session, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "customers";
        } else {
            customerRepository.save(customer);
            if (num == 2) {
                mm.put("e", 2);
                mm.put("a", 0);
            } else {
                mm.put("a", 1);
                mm.put("e", 0);
            }
            return "redirect:index";

        }
    }

    @GetMapping("/delete")
    public String delete(Long id, ModelMap mm) {

        customerRepository.deleteById(id);

        mm.put("a", 0);
        mm.put("e", 0);
        return "redirect:/index";
    }
}
