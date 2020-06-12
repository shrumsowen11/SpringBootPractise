package com.banepali;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SamplePractiseController {
@GetMapping("dogs/{did}")
	public Dog findDog(@PathVariable int did) {
		Dog dog = new Dog();
		dog.setName("Rocky");
		dog.setColor("brown");
		dog.setDid(did);
		return dog;
}
}
