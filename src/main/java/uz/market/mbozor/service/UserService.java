package uz.market.mbozor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.market.mbozor.dto.*;
import uz.market.mbozor.entity.User;
import uz.market.mbozor.repository.UserRepository;

import java.util.List;
import java.util.Objects;

/**
 * Author: Muhammadxo'ja
 * Date: 16.02.2022
 * Time: 21:51
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ItemService itemService;
    private final ObjectMapper mapper = new ObjectMapper();

    public UserService(UserRepository userRepository, ItemService itemService) {
        this.userRepository = userRepository;
        this.itemService = itemService;
    }

    public ResponseDto getAll(Integer page, Integer size) {
        if (size > 20){
            size = 20;
        }
        Page<User> users = userRepository.findAll(PageRequest.of(page, size));
        ContentPageableDto userPageableDto = new ContentPageableDto();
        try {
            userPageableDto.setPageable(new PageableDto(users.getTotalElements(),
                    users.getTotalPages(), size, page));
            userPageableDto.setContent(mapper.convertValue(users.getContent(), List.class));
        } catch (Exception e) {
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
        }
        return new ResponseDto(0, "SUCCESS", null, userPageableDto);

    }

    public ResponseDto getOne(String userName) {
        try {
            UserItemsDto userItemsDto = new UserItemsDto(userRepository.findByUserName(userName));
            List<ItemDto> itemDto = itemService.getByUserName(userItemsDto.getUserName());
            if (itemDto != null) {
                userItemsDto.setItems(itemDto);
            }
            return new ResponseDto(0,
                    "SUCCESS",
                    null,
                    userItemsDto);
        } catch (Exception e) {
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
        }
    }

    public ResponseDto userAdd(UserDto dto) {
        try {
            userRepository.save(mapper.convertValue(dto, User.class));
        } catch (Exception e) {
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
        }
        return new ResponseDto(0, "SUCCESS", null, null);
    }


    public ResponseDto deleteStudent(Long userId) {
        try{
            userRepository.deleteById(userId);
        } catch (Exception e) {
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
    }
        return new ResponseDto(0, "SUCCESS", null, null);
    }


    public void updateUser(Long userId, String userName, String phoneNumber, Boolean userStatus, Long tgId, String tgNumber, String comment) {
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalStateException(
                "user with id " + userId + "does not exist"));

        if (userName != null && userName.length() > 0 && !Objects.equals(user.getUserName() , userName)) {
            user.setUserName(userName);
        }

        if (phoneNumber != null && phoneNumber.length() > 0 && !Objects.equals(user.getPhoneNumber() , phoneNumber)) {
            user.setPhoneNumber(phoneNumber);
        }

        if (userStatus != null && !Objects.equals(user.getUserStatus() , userStatus)) {
            user.setUserStatus(userStatus);
        }

        if (tgId != null  && !Objects.equals(user.getTgId() , tgId)) {
            user.setTgId(tgId);
        }

        if (tgNumber != null && tgNumber.length() > 0 && !Objects.equals(user.getTgNumber() , tgNumber)) {
            user.setTgNumber(tgNumber);
        }

        if (!Objects.equals(user.getComment() , comment)) {
            user.setComment(comment);
        }
    }
}
