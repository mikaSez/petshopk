package info.mikasez.petshopk.services

import info.mikasez.petshopk.entities.Buyer
import info.mikasez.petshopk.entities.Pet
import info.mikasez.petshopk.repositories.BuyerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
@Transactional
class ShopService(@Autowired val buyerRepository: BuyerRepository) {


    fun buyAnAnimal(buyer: Buyer, pet: Pet) {
        pet.buyer = buyer
        buyer.pets += pet
        buyerRepository.save(buyer)
    }
}