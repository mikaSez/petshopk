package info.mikasez.petshopk.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Buyer(
        val firstname: String,
        val lastname: String,
        val adress: String,
        val city: String,
        @OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY) @ElementCollection val pets: MutableList<Pet> = mutableListOf(),
        @Id @GeneratedValue val id: Long? = null
) {
    override fun toString(): String = "$firstname $lastname $city has ${pets.size} pets"
}


@Entity
data class Shop(
        val address: String,
        val city: String,
        val phoneNumber: String,
        @Id @GeneratedValue val id: Long? = null
)

@Entity
data class Pet(
        val name: String,
        val price: Long,
        @ManyToOne(fetch = FetchType.LAZY) val petShop: Shop,
        @OneToOne(fetch = FetchType.LAZY) var buyer: Buyer? = null,
        @Id @GeneratedValue val id: Long? = null
)

@Entity
data class Sell(
        @OneToOne val buyer: Buyer,
        @OneToOne val shop: Shop,
        @OneToOne val pet: Pet,
        val price: Long,
        @Id @GeneratedValue val id: Long? = null,
        val date: LocalDateTime = LocalDateTime.now()
)


