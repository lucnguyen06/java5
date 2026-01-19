package poly.pcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.pcs.dao.CategoryDAO;
import poly.pcs.dao.ProductDAO;

@Controller
public class HomeController {

    @Autowired
    ProductDAO productDAO;

    @Autowired
    CategoryDAO categoryDAO;

    @RequestMapping("/")
    public String home(Model model) {

        // Sidebar chỉ hiện category có sản phẩm đang bán
        model.addAttribute("categories",
                categoryDAO.findCategoriesHavingProducts());

        // NEW
        model.addAttribute("newProducts",
                productDAO.findAll(PageRequest.of(0, 8)).getContent());

        // DISCOUNT
        model.addAttribute("discountProducts",
                productDAO.findDiscountProducts(PageRequest.of(0, 8)).getContent());

        // HOT
        model.addAttribute("hotProducts",
                productDAO.findHotProducts(PageRequest.of(0, 8)).getContent());

        return "home/index";
    }
}
