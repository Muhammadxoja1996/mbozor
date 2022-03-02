package uz.market.mbozor.service.controllerService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.market.mbozor.dto.*;
import uz.market.mbozor.dto.items.ItemDto;
import uz.market.mbozor.dto.users.UserDto;
import uz.market.mbozor.dto.users.UserItemsDto;
import uz.market.mbozor.entity.User;
import uz.market.mbozor.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
            userPageableDto.setPageable(new PageableDto(users.getTotalElements(),users.getTotalPages(), size, page));
            userPageableDto.setContent(users.stream().filter(Objects::nonNull).map(UserDto::new).collect(Collectors.toList()));
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

    public ResponseDto update(UserDto dto){
        try {
            User user = userRepository.findByUserName(dto.getUserName());
            user.update(dto);
            userRepository.save(user);
            return new ResponseDto(0, "SUCCESS", null, null);
        }catch ( Exception e){
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
        }
    }
    @Transactional
    public ResponseDto delete(String userName){
        try {
            userRepository.deleteByUserName(userName);
            return new ResponseDto(0, "SUCCESS", null, null);
        }catch ( Exception e){
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
        }
    }

}
