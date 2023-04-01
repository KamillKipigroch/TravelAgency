package com.TravelAgency.security.user.service;


import com.TravelAgency.security.sender.EmailSender;
import com.TravelAgency.security.user.model.*;
import com.TravelAgency.security.user.registration.EmailValidator;
import com.TravelAgency.security.user.registration.token.ConfirmationToken;
import com.TravelAgency.security.user.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static String EMAIL_NOT_VALID = "Email not valid !";
    private EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private UserService userService;
    private final EmailSender emailSender;
    private static final int GENERATED_PASSWORD_LENGTH = 7;


    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail)
            throw new IllegalStateException(EMAIL_NOT_VALID);

        String token = userService.signUpUser(
                new User(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.User),
                false
        );

        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
        try {
            emailSender.send(
                    request.getEmail(),
                    buildEmail(request.getFirstName(), link), "Confirm your email");
        } catch (Exception e) {
            logger.error("Email sender dont work !");
        }
        return token;
    }

    public User registerUser(RegisterUserRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail)
            throw new IllegalStateException(EMAIL_NOT_VALID);
        String password = renderPassword();

        UserRole role = switch (request.getUserRole()) {
            case "Admin" -> UserRole.Admin;
            case "Employee" -> UserRole.Employee;
            default -> UserRole.User;
        };
        userService.signUpUser(
                new User(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        password,
                        role),
                true);

        try {
            emailSender.send(
                    request.getEmail(),
                    buildEmailToUser(request.getFirstName(), password), "Password generated in TravelAgency");
        } catch (Exception e) {
            logger.error("Email sender dont work !");
        }


        return userService.loginUser(new LoginUser(request.getEmail(),password));
    }

    private String renderPassword() {
        UUID randomUUID = UUID.randomUUID();

        var password = randomUUID.toString().replaceAll("_", "");

        return password.substring(0, GENERATED_PASSWORD_LENGTH);
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }

    private String buildEmail(String name, String link) {
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
                "        Hi " + name + "\n" +
                "        <br/> Thank you for registering in TravelAgency.\n" +
                "        <br/> Please click on the link to activate your account:\n" +
                "        <p></p>\n" +
                "        <blockquote\n" +
                "          style=\"background-color: #f2f1ef;padding:12pt;font-size:19px\">\n" +
                "          <a href=\"" + link + "\">Activate your account in TravelAgency !</a>\n" +
                "        </blockquote>\n" +
                "        Link will expire in 15 minutes. <p>See you soon</p>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "</div>\n";
    }

    private String buildEmailToUser(String name, String password) {
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
                "        Hi " + name + "\n" +
                "        <br/> Thanks for join to Travel Agency !\n" +
                "        <br/> This is your password:\n" +
                "        <p></p>\n" +
                "        <blockquote\n" +
                "          style=\"background-color: #f2f1ef;padding:12pt;font-size:19px\">\n" +
                "          <h3>" + password + "</h3>" + "\n" +
                "        </blockquote>\n" +
                "        <p>See you soon</p>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "</div>\n";
    }
}
