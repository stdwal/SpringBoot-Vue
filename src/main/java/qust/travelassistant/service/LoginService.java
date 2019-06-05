package qust.travelassistant.service;

import qust.travelassistant.exception.PasswordNotSameException;
import qust.travelassistant.exception.UserDonotExistException;
import qust.travelassistant.exception.UserExistedException;
import qust.travelassistant.exception.WrongPasswordException;

import java.util.Date;

public interface LoginService {

    void signUp(String userName, String password, String confirmPassword) throws UserExistedException, PasswordNotSameException;

    void signIn(String userName, String password) throws WrongPasswordException, UserDonotExistException;

    void updateLogin(String userName, Date lastTime);
}
