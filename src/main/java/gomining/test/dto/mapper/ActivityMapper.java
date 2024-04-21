package gomining.test.dto.mapper;

import org.modelmapper.ModelMapper;

import gomining.test.dto.ActivityCreateDto;
import gomining.test.dto.ActivityUpdateDto;
import gomining.test.entity.Activity;

public class ActivityMapper {
    
    public static Activity toActivity(ActivityCreateDto createDto){
        return new ModelMapper().map(createDto, Activity.class);
    }
    public static Activity updateToActivity(ActivityUpdateDto updateDto){
        return new ModelMapper().map(updateDto, Activity.class);
    }
}
