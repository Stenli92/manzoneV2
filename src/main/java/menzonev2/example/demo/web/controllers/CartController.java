package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.errors.EmptyCartException;
import menzonev2.example.demo.services.AuthService;
import menzonev2.example.demo.services.EventService;
import menzonev2.example.demo.services.OfferService;
import menzonev2.example.demo.web.models.CartModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller()
@RequestMapping("/cart")
public class CartController {

    private final EventService eventService;
    private final OfferService offerService;
    private final HttpServletRequest request;
    private final AuthService authService;
    private final ModelMapper mapper;

    @Autowired
    public CartController(EventService eventService, OfferService offerService, HttpServletRequest request, AuthService authService, ModelMapper mapper) {
        this.eventService = eventService;
        this.offerService = offerService;
        this.request = request;
        this.authService = authService;
        this.mapper = mapper;
    }

    @GetMapping("/index")
    public String index() {

        HttpSession session = request.getSession(true);

        if (session.getAttribute("cart") == null){

            throw new EmptyCartException("Your cart is currently empty");
        }
        return "order/cart-view.html";
    }


    @GetMapping("/buy-ticket/{id}")
    public String buyTicket(@PathVariable("id") Long id) {

        HttpSession session = request.getSession(true);


        CartModel cartModel = this.mapper.map(this.eventService.getByID(id) , CartModel.class);
        cartModel.setId(id);
        if (session.getAttribute("cart") == null) {
            List<CartModel> cart = new ArrayList<CartModel>();
            cartModel.setQuantity(1);
            cart.add(cartModel);
            session.setAttribute("cart", cart);

        } else {
            List<CartModel> cart = (List<CartModel>) session.getAttribute("cart");

            cart.add(cartModel);
            cartModel.setQuantity(1);
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart/index";
    }

    @GetMapping("/buy-offer/{id}")
    public String buyOffer(@PathVariable("id") Long id) {

        HttpSession session = request.getSession(true);


        CartModel cartModel = this.mapper.map(this.offerService.getById(id) , CartModel.class);
        cartModel.setId(id);
        if (session.getAttribute("cart") == null) {
            List<CartModel> cart = new ArrayList<CartModel>();
            cartModel.setQuantity(1);
            cart.add(cartModel);
            session.setAttribute("cart", cart);
        } else {
            List<CartModel> cart = (List<CartModel>) session.getAttribute("cart");

            cart.add(cartModel);
            cartModel.setQuantity(1);
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart/index";
    }

    @GetMapping("remove/{id}")
    public String remove(@PathVariable("id") Long id, HttpSession session) {


        List<CartModel> cart = (List<CartModel>) session.getAttribute("cart");

        int index = this.exists(id, cart);
        cart.remove(index);

        session.setAttribute("cart", cart);
        return "redirect:/cart/index";
    }

    @GetMapping("/buy")
    public String buy(Model model){

        HttpSession session = request.getSession(true);

        List<CartModel> cart = (List<CartModel>) session.getAttribute("cart");

        User user = (User) session.getAttribute("user");

        int cartTotal = 0;

        for (CartModel cartModel : cart) {

            cartTotal += cartModel.getPrice();
        }

        if (user.getBalance() < cartTotal){

            model.addAttribute("Error" , "You don't have enough money!");

            return "redirect:/cart/index";

        }

        int balance = user.getBalance() - cartTotal ;

        user.setBalance(balance);
        this.authService.updateUserBalance(user.getUsername() , balance);
        ((List<CartModel>) session.getAttribute("cart")).clear();

        return "redirect:/home";

    }


    private int exists(Long id, List<CartModel> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }


    @ExceptionHandler(EmptyCartException.class)
    public ModelAndView exceptionHandler(EmptyCartException ex){

        ModelAndView mav = new ModelAndView("/errors/empty-cart.html");

        mav.addObject("message" , ex.getMessage());

        return mav;


    }

}
