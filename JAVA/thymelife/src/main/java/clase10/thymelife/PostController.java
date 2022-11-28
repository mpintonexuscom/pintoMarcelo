package clase10.thymelife;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PostController {
    
    @PostMapping("/request")
    public ResponseEntity postController(@RequestBody LoginForm loginForm){
        // TODO
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
