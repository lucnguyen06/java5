package poly.pcs.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import poly.pcs.dao.ProductDAO;
import poly.pcs.entity.Product;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductDAO dao;

    // ================= LIST ALL =================
    @GetMapping("")
    public String list(Model model,
                       @RequestParam("p") Optional<Integer> p,
                       @RequestParam("cid") Optional<String> cid,
                       @RequestParam("bid") Optional<String> bid,
                       @RequestParam("keywords") Optional<String> kw) {

        Pageable pageable = PageRequest.of(p.orElse(0), 8);
        Page<Product> page;

        if (kw.isPresent()) {
            page = dao.findByNameContainingIgnoreCase(kw.get(), pageable);
            model.addAttribute("keywords", kw.get());
        }
        else if (cid.isPresent()) {
            page = dao.findByCategoryId(cid.get(), pageable);
        }
        else if (bid.isPresent()) {
            page = dao.findByBrandId(bid.get(), pageable);
        }
        else {
            page = dao.findAll(pageable);
        }

        model.addAttribute("items", page);
        return "product/list";
    }

    // ================= DISCOUNT =================
    @GetMapping("/discount")
    public String discount(Model model,
                           @RequestParam("p") Optional<Integer> p) {

        Pageable pageable = PageRequest.of(p.orElse(0), 8);
        model.addAttribute("items", dao.findDiscountProducts(pageable));
        return "product/list";
    }

    // ================= HOT =================
    @GetMapping("/hot")
    public String hot(Model model,
                      @RequestParam("p") Optional<Integer> p) {

        Pageable pageable = PageRequest.of(p.orElse(0), 8);
        model.addAttribute("items", dao.findHotProducts(pageable));
        return "product/list";
    }

    // ================= DETAIL =================
    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") Integer id) {

        Optional<Product> opt = dao.findById(id);
        if(opt.isEmpty()){
            return "redirect:/product";
        }

        model.addAttribute("item", opt.get());
        return "product/detail";
    }
}
