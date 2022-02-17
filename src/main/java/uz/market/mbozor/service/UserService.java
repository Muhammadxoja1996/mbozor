package uz.market.mbozor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import uz.market.mbozor.dto.ResponseDto;
import uz.market.mbozor.dto.UserDto;
import uz.market.mbozor.entity.User;
import uz.market.mbozor.repository.UserRepository;

import java.util.List;

/**
 * Author: Muhammadxo'ja
 * Date: 16.02.2022
 * Time: 21:51
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseDto getAll()  {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = null;
        try {
            userDtos = mapper.convertValue(users, List.class);
        } catch (Exception e){
            return new ResponseDto(1,"ERROR",e.getMessage(),null);
        }
        return new ResponseDto(0,"SUCCESS",null,userDtos);

    }

    public ResponseDto getOne(String userName){
        try {
            return new ResponseDto(0,
                    "SUCCESS",
                    null,
                    mapper.convertValue(userRepository.findByUserName(userName),UserDto.class));
        } catch (Exception e){
            return new ResponseDto(1,"ERROR",e.getMessage(),null);
        }
    }

    public ResponseDto userAdd(UserDto dto){
        try {
            userRepository.save(mapper.convertValue(dto,User.class));
        } catch (Exception e){
            return new ResponseDto(1,"ERROR",e.getMessage(),null);
        }
        return new ResponseDto(0,"SUCCESS",null,null);
    }

}
