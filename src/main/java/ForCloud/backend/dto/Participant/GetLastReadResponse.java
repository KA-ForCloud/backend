package ForCloud.backend.dto.Participant;

import lombok.Data;

@Data
public class GetLastReadResponse {
    Long memberId;
    Long lastReadIndex;
}
