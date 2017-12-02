package com.loyaltyplant.movie_info.model.response;

import com.loyaltyplant.movie_info.TaskStatus;
import com.loyaltyplant.movie_info.task.RatingCalculateTask;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@NoArgsConstructor
@Setter @Getter
@XmlRootElement
@EqualsAndHashCode
@ToString
public class RatingCalculateResponse implements Serializable {
    private static final long serialVersionUID = 7051574042882683881L;

    private TaskStatus state;
    private Integer percent;
    private Double avgVote;
    private String createdDateTime;
    private String lastStateUpdateDateTime;
    private String calculatedDateTime;

    public RatingCalculateResponse(RatingCalculateTask calculateTask) {
        state = calculateTask.getTaskStatus();
        percent = calculateTask.getCompletedPercent();
        avgVote = calculateTask.getVoteAverage();
        createdDateTime = calculateTask.getCreatedDateTime().toString();
        lastStateUpdateDateTime = calculateTask.getLastStateUpdateDateTime().toString();
        calculatedDateTime = calculateTask.getCalculatedDateTime().toString();
    }


}
