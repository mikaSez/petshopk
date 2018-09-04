package info.mikasez.petshopk


import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PetshopkApplication

fun main(args: Array<String>) {
    runApplication<PetshopkApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}

