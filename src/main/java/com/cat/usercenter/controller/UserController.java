package com.cat.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cat.usercenter.common.BaseResponse;
import com.cat.usercenter.common.ErrorCode;
import com.cat.usercenter.common.ResultUtils;
import com.cat.usercenter.exception.BusinessException;
import com.cat.usercenter.model.User;
import com.cat.usercenter.model.domain.request.UserRegisterRequest;
import com.cat.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import static com.cat.usercenter.contant.UserConstant.ADMIN_ROLE;
import static com.cat.usercenter.contant.UserConstant.USER_LOGIN_STATE;

/**
 * @author LCY
 * @date Created in 2022/7/4 18:38
 *
 * RestController适用于编写restful风格的api，返回值默认为日json类型
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if (userRegisterRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR,"用户注册请求为空");
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode =userRegisterRequest.getPlanetCode();
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword,planetCode)){
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR,"注册数据为空");
        }
        long result = userService.userRegister(userAccount, userPassword,checkPassword,planetCode);
      return ResultUtils.success(result);

    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserRegisterRequest userLoginRequest, HttpServletRequest request){

        if (userLoginRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR,"登录请求为空");
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();

        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR,"登录数据为空");
        }
        User user = userService.UserLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);

    }
    @PostMapping("/logout")
    public BaseResponse<Integer> userLoginout( HttpServletRequest request){

        if (request == null){
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR,"注销请求为空");
        }
        Integer result = userService.userLogout(request);
        return ResultUtils.success(result);

    }
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser=(User) userObj;
        if (currentUser==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"当前用户不存在");
        }
        long userId=currentUser.getId();
        //TODO 校验用户是否合法
        User user =userService.getById(userId);
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username,HttpServletRequest request){

        if (!isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTO,"权限不足");
        }
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)){
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        /**
         * 吧userlist转化成数据流，遍历里面的每一个元素，然后把每个密码设置为空，在返回list
         */
        List<User> list = userList.stream().map(user -> {
            user.setUserPassword(null);
            return userService.getSafetyUser(user);
        }).collect(Collectors.toList());
        return ResultUtils.success(list);

    }

    @PostMapping ("/delete")
    public BaseResponse<Boolean> deleteUsers(@RequestBody long id,HttpServletRequest request){
        //鉴权仅管理员查询
        if (!isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTO,"权限不足");
        }
        if (id<=0){
            throw new BusinessException(ErrorCode.NO_AUTO,"普通用户无权限");
        }

        boolean b = userService.removeById(id);
        return ResultUtils.success(b);

    }


    private boolean isAdmin(HttpServletRequest request){

        //判断是否是管理员
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user=(User) userObj;
        return user != null && user.getUserRole() ==ADMIN_ROLE;

    }

}
