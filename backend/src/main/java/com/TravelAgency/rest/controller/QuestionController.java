package com.TravelAgency.rest.controller;

import com.TravelAgency.rest.model.database.Question;
import com.TravelAgency.rest.service.*;
import com.TravelAgency.security.sender.EmailSender;
import com.TravelAgency.security.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.TravelAgency.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RestController
@AllArgsConstructor
@RequestMapping("/api/mail")
public class QuestionController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EmailSender emailSender;
    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Question>> getAll() {
        var orders = questionService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/add")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<Question> add(@RequestBody Question request) {
        var question = questionService.addQuestion(request);

        try {
            emailSender.send(
                    request.getEmail(),
                    buildEmail());
        } catch (Exception e) {
            logger.error("Email sender dont work !");
        }
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    public ResponseEntity<Question> answerToQuestion(@RequestBody Question request) {
        request.setAnswered(true);
        request.setEmployee(
                userService.findUserByEmail(request.getEmployee().getEmail())
        );
        var question = questionService.answerToQuestion(request);
        try {
            emailSender.send(
                    request.getEmail(),
                    buildAnswer(request.getAnswerMessage()));
        } catch (Exception e) {
            logger.error("Email sender dont work !");
        }
        return new ResponseEntity<>(question, HttpStatus.OK);
    }


    private String buildEmail() {
        return "<div>\n" +
                "  <table role=\"presentation\" style=\"min-width:100%;!important\">\n" +
                "    <tr>\n" +
                "      <td>\n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"max-width:480pt\" align=\"center\">\n" +
                "          <tr>\n" +
                "            <td style=\"background-color:#1D70B8;font-size:28px;padding-left:30pt\">\n" +
                "                    <span style=\"color:#ffffff\">Confirm your email</span>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>\n" +
                "  <table role=\"presentation\" align=\"center\"\n" +
                "         style=\"border-collapse:collapse;max-width:400pt;width:100%!important\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "      <td style=\"padding-top:15pt;font-size:19px\">\n" +
                "        Hi " +
                "        <br/> Thank you for write a question.\n" +
                "        <br/> We will try respond as soon as possible \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "</div>\n";
    }

    private String buildAnswer(String answer) {
        return "<div>\n" +
                "  <table role=\"presentation\" style=\"min-width:100%;!important\">\n" +
                "    <tr>\n" +
                "      <td>\n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"max-width:480pt\" align=\"center\">\n" +
                "          <tr>\n" +
                "            <td style=\"background-color:#1D70B8;font-size:28px;padding-left:30pt\">\n" +
                "                    <span style=\"color:#ffffff\">Confirm your email</span>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>\n" +
                "  <table role=\"presentation\" align=\"center\"\n" +
                "         style=\"border-collapse:collapse;max-width:400pt;width:100%!important\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "      <td style=\"padding-top:15pt;font-size:19px\">\n" +
                "        Hi  \n" +
                "        "+ answer +
                "      </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "</div>\n";
    }

}
