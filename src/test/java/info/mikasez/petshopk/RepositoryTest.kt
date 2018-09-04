package info.mikasez.petshopk


import info.mikasez.petshopk.entities.Buyer
import info.mikasez.petshopk.entities.Pet
import info.mikasez.petshopk.entities.Shop
import info.mikasez.petshopk.repositories.BuyerRepository
import info.mikasez.petshopk.repositories.PetRepository
import info.mikasez.petshopk.repositories.ShopRepository
import info.mikasez.petshopk.services.ShopService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension

/**
 * Bug with spring and JPA here... no `some name here` before 5.1
 *
 * */
@ExtendWith(SpringExtension::class)
@DataJpaTest
@Import(ShopService::class)  //to be removed with shop service moving to it's own test
class RepositoriesTests @Autowired constructor(val entityManager: TestEntityManager,
                                               val petRepository: PetRepository,
                                               val buyerRepository: BuyerRepository,
                                               val shopRepository: ShopRepository,
                                               val shoppingService: ShopService) {


    @Test
    fun when_findById_then_return_Shop() {
        val shop = Shop("3 rue de la Paix", "Paris", "0000000000")
        entityManager.persist(shop)
        entityManager.flush()

        val found = shopRepository.findById(shop.id!!)

        assertThat(found.get()).isEqualTo(shop)
    }

    @Test
    fun when_shop_has_pets_should_return_them_all() {
        val shop = Shop("3 rue de la Paix", "Paris", "0000000000")
        entityManager.persist(shop)

        val cat = Pet("Miaou", 200L, shop)
        val dog = Pet("Waff", 100L, shop)
        val rat = Pet("Hssss", 50L, shop)

        entityManager.persist(cat)
        entityManager.persist(dog)
        entityManager.persist(rat)
        entityManager.flush()

        val found = petRepository.findByPetShop(shop)

        val pets = found
        assertThat(pets).hasSize(3)
    }

    @Test
    fun when_buyer_has_pets_can_get_them_all() {
        val shop = Shop("3 rue de la Paix", "Paris", "0000000000")
        entityManager.persist(shop)

        val cat = Pet("Miaou", 200L, shop)
        val dog = Pet("Waff", 100L, shop)
        val rat = Pet("Hssss", 50L, shop)

        entityManager.persist(cat)
        entityManager.persist(dog)
        entityManager.persist(rat)

        val buyer = Buyer("Some", "One", "SomeWhere", "farAway")

        entityManager.persist(buyer)
        entityManager.flush()



        shoppingService.buyAnAnimal(buyer, cat)


        val buyerFound = buyerRepository.findById(buyer.id!!)
        val petFound = petRepository.findById(cat.id!!)

        assertThat(buyerFound.get().pets).contains(cat)
        print(petFound)
        assertThat(petFound.get().buyer).isEqualTo(buyer)
    }

}
