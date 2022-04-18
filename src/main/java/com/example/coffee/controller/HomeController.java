package com.example.coffee.controller;

import com.example.coffee.model.Counts;
import com.example.coffee.model.LevelOfIngredients;
import com.example.coffee.model.OrderMenu;
import com.example.coffee.model.Orders;
import com.example.coffee.service.CountsService;
import com.example.coffee.service.LevelOfIngredientsService;
import com.example.coffee.service.OrdermenuService;
import com.example.coffee.service.OrdersService;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {
    private final OrdermenuService ordermenuService;
    private final LevelOfIngredientsService levelOfIngredientsService;
    private final OrdersService ordersService;
    private final CountsService countsService;

    public HomeController(OrdermenuService ordermenuService,
                          LevelOfIngredientsService levelOfIngredientsService,
                          OrdersService ordersService,
                          CountsService countsService) {
        this.ordermenuService = ordermenuService;
        this.levelOfIngredientsService = levelOfIngredientsService;
        this.ordersService = ordersService;
        this.countsService = countsService;
    }

    @GetMapping("/home")
    public String homePage(Model model){
        List<LevelOfIngredients> levelOfIngredients = levelOfIngredientsService.findAll();
        Counts counts = countsService.findById((long)1);
        List<OrderMenu> orderMenuList = ordermenuService.findAll();
        List<Orders> ordersList = ordersService.findAll();
        model.addAttribute("levelOfIngredients", levelOfIngredients);
        model.addAttribute("orderMenuList", orderMenuList);
        model.addAttribute("counts", counts);
        return "HomePage";
    }

    @PostMapping("createNewOrder/{idOrderMenu}")
    public String orderSelection(@PathVariable("idOrderMenu")  Long idOrderMenu, Model model){
        OrderMenu orderMenu = ordermenuService.findById(idOrderMenu);
        LevelOfIngredients levelOfIngredients = levelOfIngredientsService.findById((long)1);
        Counts counts = countsService.findById((long) 1);
//        Проверка наличия ингредиентов (4 if-a )
        if(levelOfIngredients.getLevelOfCoffee() - orderMenu.getQuantityOfCoffee() <= 0 ){
            OrderMenu orderMenuList = ordermenuService.findById(idOrderMenu);
            model.addAttribute("levelOfIngredients", levelOfIngredients);
            model.addAttribute("orderMenuList", orderMenuList);
            model.addAttribute("counts", counts);
            model.addAttribute("AddCoffee", "responseForLevelCoffee");
            return "HomePage";
        }
        if(levelOfIngredients.getLevelOfWater() - orderMenu.getQuantityOfWater() <= 0){
            OrderMenu orderMenuList = ordermenuService.findById(idOrderMenu);
            model.addAttribute("levelOfIngredients", levelOfIngredients);
            model.addAttribute("orderMenuList", orderMenuList);
            model.addAttribute("counts", counts);
            model.addAttribute("AddWater", "responseForLevelWater");
            return "HomePage";
        }
        if(levelOfIngredients.getLevelOfMilk() - orderMenu.getQuantityOfMilk() <= 0){
            OrderMenu orderMenuList = ordermenuService.findById(idOrderMenu);
            model.addAttribute("levelOfIngredients", levelOfIngredients);
            model.addAttribute("orderMenuList", orderMenuList);
            model.addAttribute("counts", counts);
            model.addAttribute("AddMilk", "responseForLevelMilk");
            return "HomePage";
        }
        if(levelOfIngredients.getLevelOfCream() - orderMenu.getQuantityOfCream() <= 0){
            OrderMenu orderMenuList = ordermenuService.findById(idOrderMenu);
            model.addAttribute("levelOfIngredients", levelOfIngredients);
            model.addAttribute("orderMenuList", orderMenuList);
            model.addAttribute("counts", counts);
            model.addAttribute("AddCream", "responseForLevelCream");
            return "HomePage";
        }

        Orders newOrder  = new Orders();
        newOrder.setNameOfOrder(orderMenu.getTypeOfOrder());
        newOrder.setOrderMenu(orderMenu);
        ordersService.saveOrders(newOrder);
        counts.setCountOfOrder(counts.getCountOfOrder() + 1);
        countsService.saveCounts(counts);

        levelOfIngredients.setLevelOfWater(levelOfIngredients.getLevelOfWater() - orderMenu.getQuantityOfWater());
        levelOfIngredients.setLevelOfCoffee(levelOfIngredients.getLevelOfCoffee() - orderMenu.getQuantityOfCoffee());
        levelOfIngredients.setLevelOfMilk(levelOfIngredients.getLevelOfMilk() - orderMenu.getQuantityOfMilk());
        levelOfIngredients.setLevelOfCream(levelOfIngredients.getLevelOfCream() - orderMenu.getQuantityOfCream());
        levelOfIngredientsService.saveLevelOfIngredients(levelOfIngredients);
        if(counts.getCountOfOrder()%100==0) {
            List<OrderMenu> orderMenuList = ordermenuService.findAll();
            model.addAttribute("filterReplacementRequired", "filterReplacementRequired");
            model.addAttribute("levelOfIngredients", levelOfIngredients);
            model.addAttribute("orderMenuList", orderMenuList);
            return "HomePage";
        }
        return "redirect:/home";
    }

    @GetMapping("/updateOrderMenu/{idOrderMenu}")
    public String UpdateCoffeeForm(@PathVariable("idOrderMenu") long idOrderMenu, Model model){
        OrderMenu orderMenu = ordermenuService.findById(idOrderMenu);
        model.addAttribute("orderMenu", orderMenu);
        return "UpdateNewCoffee";
    }

    @PostMapping("/updateOrderMenu/{idOrderMenu}")
    public String UpdateCoffee(@PathVariable("idOrderMenu") long idOrderMenu, @Valid OrderMenu orderMenu,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "UpdateNewCoffee";
        }
        List<String> orderMenuList = ordermenuService.findTypeOfOrder();
        OrderMenu existingOrderMenu = ordermenuService.findById(idOrderMenu);
        if (orderMenu.getTypeOfOrder().equalsIgnoreCase(existingOrderMenu.getTypeOfOrder())){
            ordermenuService.saveOrderMenu(orderMenu);
            return "redirect:/home";
        }else
            if(orderMenuList.stream().anyMatch(orderMenu.getTypeOfOrder()::equals)) {
            bindingResult.rejectValue("typeOfOrder", "error.typeOfOrder", "Такой тип кофе уже существует");
            return "UpdateNewCoffee";
        }
        ordermenuService.saveOrderMenu(orderMenu);
        return"redirect:/home";
    }


    @PostMapping("/rinseWithWater")
    public String rinseWithWater(Model model){
        LevelOfIngredients levelOfIngredients = levelOfIngredientsService.findById((long) 1);
        List<OrderMenu> orderMenuList = ordermenuService.findAll();
        Counts counts = countsService.findById((long)1);
        if(levelOfIngredients.getLevelOfWater() < 250){
            model.addAttribute("levelOfIngredients", levelOfIngredients);
            model.addAttribute("orderMenuList", orderMenuList);
            model.addAttribute("counts", counts);
            model.addAttribute("response", "notEnoughLevelOfWater");
            return"HomePage";
        }
        Orders orders = new Orders();
        orders.setNameOfOrder("Промывка водой");
        orders.setOrderMenu(null);
        levelOfIngredients.setLevelOfWater(levelOfIngredients.getLevelOfWater() - 250);
        levelOfIngredientsService.saveLevelOfIngredients(levelOfIngredients);
        ordersService.saveOrders(orders);
        return "redirect:/home";
    }

    @PostMapping("FillTheWater")
    public String FillTheWater(Model model){
    LevelOfIngredients levelOfIngredients = levelOfIngredientsService.findById((long) 1);
    List<OrderMenu> orderMenuList = ordermenuService.findAll();
    Counts counts = countsService.findById((long)1);
    if(levelOfIngredients.getLevelOfWater() == 1000){
        model.addAttribute("levelOfIngredients", levelOfIngredients);
        model.addAttribute("orderMenuList", orderMenuList);
        model.addAttribute("counts", counts);
        model.addAttribute("RefuelingIsNotRequired", "responseForLevelOfWater");
        return "HomePage";
    }
    levelOfIngredients.setLevelOfWater(1000);
    counts.setCountOfWater(counts.getCountOfWater()+1);
    countsService.saveCounts(counts);
    levelOfIngredientsService.saveLevelOfIngredients(levelOfIngredients);
    return"redirect:/home";
    }

    @PostMapping("/FillTheCoffee")
    public String fillTheCoffee(Model model) {
        LevelOfIngredients levelofingredients = levelOfIngredientsService.findById((long) 1);
        List<OrderMenu> orderMenuList = ordermenuService.findAll();
        Counts counts = countsService.findById((long) 1);
        if (levelofingredients.getLevelOfCoffee() == 1000) {
            model.addAttribute("levelOfIngredients", levelofingredients);
            model.addAttribute("orderMenuList", orderMenuList);
            model.addAttribute("counts", counts);
            model.addAttribute("RefuelingIsNotRequired", "responseForLevelOfCoffee");
            return "HomePage";
        }
        levelofingredients.setLevelOfCoffee(1000);
        counts.setCountOfCoffee(counts.getCountOfCoffee() + 1);
        countsService.saveCounts(counts);
        levelOfIngredientsService.saveLevelOfIngredients(levelofingredients);
        return "redirect:/home";
    }

    @PostMapping("/FillTheMilk")
    public String fillTheMilk(Model model) {
        LevelOfIngredients levelofingredients = levelOfIngredientsService.findById((long) 1);
        List<OrderMenu> orderMenuList = ordermenuService.findAll();
        Counts counts = countsService.findById((long) 1);
        if (levelofingredients.getLevelOfMilk() == 1000) {
            model.addAttribute("levelOfIngredients", levelofingredients);
            model.addAttribute("orderMenuList", orderMenuList);
            model.addAttribute("counts", counts);
            model.addAttribute("RefuelingIsNotRequired", "responseForLevelOfMilk");
            return "HomePage";
        }
        levelofingredients.setLevelOfMilk(1000);
        counts.setCountOfMilk(counts.getCountOfMilk() + 1);
        countsService.saveCounts(counts);
        levelOfIngredientsService.saveLevelOfIngredients(levelofingredients);
        return "redirect:/home";
    }

    @PostMapping("/FillTheCream")
    public String fillTheCream(Model model) {
        LevelOfIngredients levelofingredients = levelOfIngredientsService.findById((long) 1);
        List<OrderMenu> orderMenuList = ordermenuService.findAll();
        Counts counts = countsService.findById((long) 1);
        if (levelofingredients.getLevelOfCream() == 1000) {
            model.addAttribute("levelOfIngredients", levelofingredients);
            model.addAttribute("orderMenuList", orderMenuList);
            model.addAttribute("counts", counts);
            model.addAttribute("RefuelingIsNotRequired", "responseForLevelOfCream");
            return "HomePage";
        }
        levelofingredients.setLevelOfCream(1000);
        counts.setCountOfCream(counts.getCountOfCream() + 1);
        countsService.saveCounts(counts);
        levelOfIngredientsService.saveLevelOfIngredients(levelofingredients);
        return "redirect:/home";
    }

    @GetMapping("/createNewCoffee")
    public String createCoffeeForm(OrderMenu orderMenu){
        return "CreateNewCoffee";
    }

    @PostMapping("/createNewOrderMenu")
    public String createCoffee(@Valid OrderMenu newOrderMenu, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "CreateNewCoffee";
        }
        List<String> orderMenuList = ordermenuService.findTypeOfOrder();
        if(orderMenuList.stream().anyMatch(newOrderMenu.getTypeOfOrder()::equals)){
                bindingResult.rejectValue("typeOfOrder", "error.typeOfOrder", "Такой тип кофе уже существует");
                return "CreateNewCoffee";
            }

        newOrderMenu.setQuantityOfWater(newOrderMenu.getQuantityOfCoffee());
        ordermenuService.saveOrderMenu(newOrderMenu);
        return "redirect:/home";
    }

    @DeleteMapping("/coffee-delete/{id}")
    public String deleteCoffee(@PathVariable("id") long id) {
        ordermenuService.deleteById(id);
        return "redirect:/home";
    }

}
