package com.example.plantapp.service.plant;

import com.example.plantapp.commons.data.entity.PlantEntity;
import com.example.plantapp.commons.data.entity.PlantNoteEntity;
import com.example.plantapp.commons.data.entity.UserPlantEntity;
import com.example.plantapp.commons.data.mapper.plant.PlantShortMapper;
import com.example.plantapp.commons.data.request.SearchRequest;
import com.example.plantapp.commons.data.request.plant.PlantDrawChartRequest;
import com.example.plantapp.commons.data.response.plant.PlantDrawCharResponse;
import com.example.plantapp.db.mysql.config.SpecificationConfig;
import com.example.plantapp.db.mysql.repository.IPlantRepository;
import com.example.plantapp.db.mysql.repository.IUserPlantRepository;
import com.example.plantapp.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.plantapp.commons.data.constant.ErrorCodeConstant.ERROR_CODE_NOT_INFORMATION;
import static com.example.plantapp.utils.DateUtils.localDateTimeToLong;

@Service
public class PlantDrawChartService extends BaseService {
    @Autowired
    private IPlantRepository plantRepository;
    @Autowired
    private IUserPlantRepository userPlantRepository;
    @Autowired
    private SpecificationConfig specificationConfig;
    @Autowired
    private PlantShortMapper plantShortMapper;

    public ResponseEntity<String> drawChart(Integer id, PlantDrawChartRequest request){
        Optional<PlantEntity> optional = plantRepository.findById(id);
        if(!optional.isPresent()){
              return createResponseError(ERROR_CODE_NOT_INFORMATION,"PlantId không tồn tại");
        }
        SearchRequest searchRequest = request.getSearchRequest();
        Integer numColumn = request.getNumColumn();
        Specification<UserPlantEntity> searchSpe = specificationConfig.buildSearch(searchRequest, UserPlantEntity.class);
        Pageable pageable =specificationConfig.buildPageable(searchRequest,UserPlantEntity.class);
        List<UserPlantEntity> userPlants = userPlantRepository.findAll(searchSpe,pageable).toList();
        List<PlantNoteEntity> plantNotes = userPlants.stream()
                .flatMap(userPlant -> userPlant.getNotes().stream())
                .collect(Collectors.toList());

        List<Long> timeColumn = new ArrayList<>();
        Long distanceTime = (request.getEndTime() - request.getBeginTime())/numColumn;
        for(int i = 0 ; i < numColumn;i++ ){
            timeColumn.add(request.getBeginTime() + distanceTime * i);
        }
        plantNotes.stream().map(plantNoteEntity -> {
            long createdAt = localDateTimeToLong(plantNoteEntity.getCreatedAt());
            if (createdAt >= request.getEndTime()) {
                return plantNoteEntity.setColumnTime(null);
            }
            int index = binarySearchClosest(timeColumn, createdAt);
            if (index == -1) {
                return plantNoteEntity.setColumnTime(null);
            } else {
                return plantNoteEntity.setColumnTime(timeColumn.get(index));
            }
        }).filter(plantNoteEntity -> plantNoteEntity.getColumnTime() != null)
                .collect(Collectors.toList());

        List<PlantDrawCharResponse> responses = plantNotes.stream().collect(Collectors.groupingBy(PlantNoteEntity::getColumnTime,
                        Collectors.collectingAndThen(Collectors.toList(), (List<PlantNoteEntity> list) -> {
                            Double totalHeight = 0.0;
                            Integer totalQuantityFlower = 0;
                            for (int i = 0; i < list.size(); i++) {
                                totalHeight += list.get(i).getHeight();
                                totalQuantityFlower += list.get(i).getQuantityFlower();
                            }

                            DecimalFormat decimalFormat = new DecimalFormat("#.##");
                            DecimalFormat integerFormat = new DecimalFormat("#");

                            Double averHeight = totalHeight / list.size();
                            Integer averQuantityFlower = totalQuantityFlower / list.size();
                            String formattedAverHeight = decimalFormat.format(averHeight);
                            String formattedAverQuantityFlower = integerFormat.format(averQuantityFlower);
                            PlantDrawCharResponse response = new PlantDrawCharResponse()
                                    .setTime(plantNotes.get(0).getColumnTime())
                                    .setHeight(Double.parseDouble(formattedAverHeight))
                                    .setQuantityFlower(Integer.parseInt(formattedAverQuantityFlower));
                            return response;
                        }))).entrySet().stream().map(Map.Entry::getValue)
                .collect(Collectors.toList());
        return createResponseSuccess(gson.toJson(responses));
    }

    private static int binarySearchClosest(List<Long> list, long target) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (list.get(mid) == target) {
                return mid;
            } else if (list.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        try {
            long x = Math.abs(target - list.get(low));
            long y = Math.abs(list.get(high) - target);
            if(x >= y){
                return low;
            }else {
                return high;
            }
        }catch (Exception e){
            return high;
        }
    }

}
