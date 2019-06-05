package qust.travelassistant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import qust.travelassistant.dao.LoginDao;
import qust.travelassistant.entity.Login;
import qust.travelassistant.exception.PasswordNotSameException;
import qust.travelassistant.exception.UserDonotExistException;
import qust.travelassistant.exception.UserExistedException;
import qust.travelassistant.exception.WrongPasswordException;
import qust.travelassistant.service.LoginService;

import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    private String slat = "17nxe(@omg&$!#293";

    @Autowired
    private LoginDao loginDao;

    @Override
    public void signUp(String userName, String password, String confirmPassword) throws UserExistedException, PasswordNotSameException {
        Login login = getByUserName(userName);
        if (login != null) {
            throw new UserExistedException("用户已存在！");
        }
        if (!password.equals(confirmPassword)) {
            throw new PasswordNotSameException("两次密码不一致");
        }
        loginDao.insertLogin(userName, getMd5(password));
    }

    @Override
    public void signIn(String userName, String password) throws WrongPasswordException, UserDonotExistException {
        Login login = getByUserName(userName);
        if (login == null) {
            throw new UserDonotExistException("用户不存在！");
        }
        if (!login.getPassword().equals(getMd5(password))) {
            throw new WrongPasswordException("密码错误！");
        }
    }

    @Override
    public void updateLogin(String userName, Date lastTime) {
        loginDao.updateLogin(userName, lastTime);
    }

    private Login getByUserName(String userName) {
        return loginDao.queryByUserName(userName);
    }

    private String getMd5(String password) {
        String base = password + "/" + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}
