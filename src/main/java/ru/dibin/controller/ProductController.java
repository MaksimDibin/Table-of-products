package ru.dibin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.dibin.exception.NotFoundException;
import ru.dibin.service.ProductRepr;
import ru.dibin.service.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listPage(Model model) {
        logger.info("Product page update");

        model.addAttribute("products", productService.findAll());
        return "product";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable(value = "id") Long id, Model model) {
        logger.info("Edit product with id {}", id);
        model.addAttribute("product", productService.findById(id)
                .orElseThrow(NotFoundException::new));
        return "product_form";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new ProductRepr());
        return "product_form";
    }

    @PostMapping("/update")
    public String updateProduct(@Valid @ModelAttribute("product") ProductRepr product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productService.save(product);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        logger.info("Delete product with id {}", id);
        productService.deleteById(id);
        return "redirect:/product";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler() {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
