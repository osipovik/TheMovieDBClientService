package com.loyaltyplant.movie_info.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RatingCalculateResponse implements Serializable {
    private static final long serialVersionUID = 7051574042882683881L;

    private TaskStatus state;
    private Integer percent;
    private Double avgVote;
    private String createdDateTime;
    private String lastStateUpdateDateTime;

    public RatingCalculateResponse(RatingCalculateTask calculateTask) {
        state = calculateTask.getTaskStatus();
        percent = calculateTask.getCompletedPercent();
        avgVote = calculateTask.getVoteAverage();
        createdDateTime = calculateTask.getCreatedDateTime().toString("yyyy-MM-dd HH:mm:ss");
        lastStateUpdateDateTime = calculateTask.getLastStateUpdateDateTime().toString("yyyy-MM-dd HH:mm:ss");
    }


}
