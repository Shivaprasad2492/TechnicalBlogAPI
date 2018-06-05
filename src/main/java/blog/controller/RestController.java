package blog.controller;

import blog.repository.UserRepository;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    UserRepository userRepo;

    @PostMapping("/api/register/")
    public String registerUser(@RequestParam("username") String uname, @RequestParam("password") String password, @RequestParam("fullname") String fullName) {

        String result = String.valueOf(userRepo.findUserExist(uname));
        String sha256hex = Hashing.sha256()
                .hashString(password, Charsets.US_ASCII)
                .toString();

        if ((result.equalsIgnoreCase("null"))) {
            userRepo.addUserCredentials(uname, fullName,sha256hex);
            return uname + ", you successfully registered";
        } else {
            return "user already exists";
        }

    }




}
