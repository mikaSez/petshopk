package info.mikasez.petshopk.repositories

import info.mikasez.petshopk.entities.Buyer
import info.mikasez.petshopk.entities.Pet
import info.mikasez.petshopk.entities.Shop
import org.springframework.data.repository.CrudRepository

interface ShopRepository : CrudRepository<Shop, Long>

interface PetRepository : CrudRepository<Pet, Long> {
    fun findByPetShop(shop: Shop): List<Pet>
}

interface BuyerRepository : CrudRepository<Buyer, Long>