package com.triple.kotprac

import com.triple.kotprac.place.domain.Place
import com.triple.kotprac.place.domain.repository.PlaceRepository
import com.triple.kotprac.user.domain.User
import com.triple.kotprac.user.domain.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class DataLoader(
        private val userRepository: UserRepository,
        private val placeRepository: PlaceRepository
) : CommandLineRunner {

    companion object{
        val PLACE_LIST= mutableListOf("북촌 한옥마을", "낙산공원", "올림픽공원", "서울숲", "뚝섬한강공원",
                "쌈지길", "청계광장", "반포대교달빛무지개분수", "세종마을음식문화거리", "월드컵공원",
                "롯데월드", "하늘공원", "창덕궁", "경복궁", "석촌호수", "남산타워", "대학로")
    }
    override fun run(vararg args: String?) {
        if (userRepository.findAll().isEmpty()) {
            val users = mutableListOf<User>()
            for (i in 1..10) {
                users.add(User("tester$i"))
            }
            userRepository.saveAll(users)
        }

        if(placeRepository.findAll().isEmpty()){
            val placeList = PLACE_LIST.stream().map { placeName -> Place(placeName) }.collect(Collectors.toList())
            placeRepository.saveAll(placeList)
        }
    }

}