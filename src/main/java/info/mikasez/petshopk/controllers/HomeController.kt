package info.mikasez.petshopk.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    fun shop(model: Model): String {
        model["title"] = "My PetShop"
        return "shop"
    }
}